/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.webService;

import java.util.List;
import java.util.NoSuchElementException;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rojo.signalement.model.AdminRegion;
import rojo.signalement.model.SignalementAffecte;
import rojo.signalement.model.Token;
import rojo.signalement.repository.AdminRegionRepository;
import rojo.signalement.repository.SignalementAffecteRepository;
import rojo.signalement.repository.TokenRepository;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/SignalementAffecte")
public class SignalementAffecteController {
    @Autowired
    SignalementAffecteRepository service;
    @Autowired
    TokenRepository tokenRepo;
    @Autowired
    AdminRegionRepository adminRegionRepo;

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("")
//    public List<SignalementAffecte> list() {
//        return service.findAll();
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public ResponseEntity<List<SignalementAffecte>> get(@RequestHeader("token") String token) {
        try {
            Token tok=tokenRepo.getTokenSpecifier(token, "AdminRegion");
            AdminRegion region = adminRegionRepo.findById(tok.getIdUser()).get();
            List<SignalementAffecte> signalement=service.getSignalementByIdRegion(region.getIdRegion());
            return new ResponseEntity<List<SignalementAffecte>>(signalement, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<SignalementAffecte>>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<SignalementAffecte> add(@RequestBody SignalementAffecte region) {
        try {
            service.save(region);
            return new ResponseEntity<SignalementAffecte>(region, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<SignalementAffecte>(HttpStatus.BAD_REQUEST);
        }
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@RequestBody SignalementAffecte region, @PathVariable Integer id) {
//        try {
//            SignalementAffecte existRegion = service.getById(id);
//            existRegion.setIdSignalement(region.getIdSignalement());
//            existRegion.setIdRegion(region.getIdRegion());
//
//            service.save(existRegion);
//            return new ResponseEntity<>(existRegion, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id) {
//        service.deleteById(id);
//    }
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/getSignalement/{id}")
//    public SignalementAffecte getSignalement(@PathVariable Integer id) {
//        return service.getSpecifiqueSignalement(id);
//    }
    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/getSignalement/{id}")
//    public SignalementAffecte getSignalement(@PathVariable Integer id) {
//        return service.getSpecifiqueSignalement(id);
//    }
//    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/getSignalement/{id}")
//    public SignalementAffecte getSignalement(@PathVariable Integer id) {
//        return service.getSpecifiqueSignalement(id);
//    }
}
