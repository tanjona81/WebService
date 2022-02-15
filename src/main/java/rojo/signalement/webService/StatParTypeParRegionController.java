/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.webService;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import rojo.signalement.model.StatParType;
import rojo.signalement.model.StatParTypeParRegion;
import rojo.signalement.repository.StatParTypeParRegionRepository;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/Statistique")
public class StatParTypeParRegionController {
    @Autowired
    StatParTypeParRegionRepository repo;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List getAdminRegion() {
        return repo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public StatParTypeParRegion getAdminRegionById(@PathVariable("id") int id) {
        StatParTypeParRegion adm = repo.findById(id).get();
        return adm;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<StatParTypeParRegion> createAdminRegion(@RequestBody StatParTypeParRegion adminRegion) {
        StatParTypeParRegion admin = repo.save(adminRegion);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<StatParTypeParRegion> updateAdminRegion(@PathVariable("id") int id, @RequestBody StatParTypeParRegion m) {
        Optional<StatParTypeParRegion> adminRegion = repo.findById(id);
        adminRegion.get().setIdRegion(m.getIdRegion());
        adminRegion.get().setNomRegion(m.getNomRegion());
        adminRegion.get().setIdTypeSignalement(m.getIdTypeSignalement());
        adminRegion.get().setNbrSignalement(m.getNbrSignalement());
        adminRegion.get().setNomTypeSignalement(m.getNomTypeSignalement());
        StatParTypeParRegion updated = repo.save(adminRegion.get());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminRegion(@PathVariable("id") int id) {
        repo.deleteById(id);
        return new ResponseEntity<>("Admin region deleted with success", HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/TypeSignalement/Region/{id}")
    public List getStatTypeRegion(@PathVariable("id") int id) {
        return repo.getStat(id);
    }
}
