/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.webService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rojo.signalement.model.Signalement;
import rojo.signalement.model.TypeSignalement;
import rojo.signalement.model.Utilisateur;
import rojo.signalement.repository.SignalementRepository;
import rojo.signalement.repository.TypeSignalementRepository;
import rojo.signalement.service.SignalementService;
import rojo.signalement.repository.UtilisateurRepository;

@RestController
@RequestMapping("/Signalement")
public class SignalementController {
    
//    @Autowired
//    SignalementRepository service;
    @Autowired
    UtilisateurRepository userRepo;
    @Autowired
    SignalementRepository signalementRepo;
    @Autowired
    TypeSignalementRepository typeSignalementRepo;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List<Signalement> list() {
        return signalementRepo.findAll();
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Signalement> get(@PathVariable Integer id) {
        try {
            Optional<Signalement> signalement = signalementRepo.findById(id);
            return new ResponseEntity<Signalement>(signalement.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Signalement>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<Signalement> add(@RequestBody Signalement signalement) {
        try {
            signalementRepo.save(signalement);
            return new ResponseEntity<Signalement>(signalement, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Signalement>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/Demande")
    public List<Signalement> getDemande() {
        return signalementRepo.getListe();
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Signalement signalement, @PathVariable("id") Integer id) {
        try {
            Optional<Signalement> existSignalement = signalementRepo.findById(id);
            existSignalement.get().setIdUser(signalement.getIdUser());
            existSignalement.get().setNomUser(signalement.getNomUser());
            existSignalement.get().setPrenomUser(signalement.getPrenomUser());
            existSignalement.get().setIdTypeSignalement(signalement.getIdTypeSignalement());
            existSignalement.get().setTypeSignalement(signalement.getTypeSignalement());
            existSignalement.get().setPositionx(signalement.getPositionx());
            existSignalement.get().setPositiony(signalement.getPositiony());
            existSignalement.get().setDateSignalement(signalement.getDateSignalement());
            existSignalement.get().setDescription(signalement.getDescription());
            existSignalement.get().setEtat(signalement.getEtat());
            existSignalement.get().setPhoto(signalement.getPhoto());
            signalementRepo.save(existSignalement.get());
            return new ResponseEntity<>(existSignalement.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        signalementRepo.deleteById(id);
    }
    
    public Signalement getTrustedSignalement(Signalement signalement, int idUser) {
        try {
            Optional<Utilisateur> user = userRepo.findById(idUser);
            Optional<TypeSignalement> typeSignalement = typeSignalementRepo.findById(signalement.getIdTypeSignalement());
            if (user != null && typeSignalement != null) {
                signalement.setNomUser(user.get().getNom());
                signalement.setPrenomUser(user.get().getPrenom());
                signalement.setTypeSignalement(typeSignalement.get().getNomTypeSignalement());
                signalement.setEtat("Demande");
                return signalement;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean testDoublureSignalement(int idSignalement) {
        try {
            Optional<Signalement> signalement = signalementRepo.findById(idSignalement);
            if (signalement.get() == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
//    @GetMapping("/ListeSignalement/User/{idUser}")
//    public List<Signalement> getListe(@PathVariable Integer idUser){  
//        return signalementRepo.signalementUtilisateur(idUser);
//    }
//    
//    @GetMapping("/ListeSignalement/Region/{idUser}")
//    public List<Signalement> getListeregion(@PathVariable Integer idUser){
//        return signalementRepo.signalementRegion(idUser);
//    }
//    
//    @GetMapping("/recherche")
//    public List<Signalement> getListeregion(@RequestParam Integer id,@RequestParam Integer idtype,@RequestParam Date datedebut,@RequestParam Date datefin){
//        return signalementRepo.recherche(id,idtype,datedebut,datefin);
//    }
}
