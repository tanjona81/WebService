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
import rojo.signalement.model.StatParRegion;
import rojo.signalement.repository.StatParRegionRepository;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/Statistique/Region")
public class StatParRegionController {
    @Autowired
    StatParRegionRepository repo;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List getAdminRegion() {
        return repo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public StatParRegion getAdminRegionById(@PathVariable("id") int id) {
        StatParRegion adm = repo.findById(id).get();
        return adm;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<StatParRegion> createAdminRegion(@RequestBody StatParRegion adminRegion) {
        StatParRegion admin = repo.save(adminRegion);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<StatParRegion> updateAdminRegion(@PathVariable("id") int id, @RequestBody StatParRegion m) {
        Optional<StatParRegion> adminRegion = repo.findById(id);
        adminRegion.get().setIdRegion(m.getIdRegion());
        adminRegion.get().setNbrSignalement(m.getNbrSignalement());
        adminRegion.get().setNomRegion(m.getNomRegion());
        StatParRegion updated = repo.save(adminRegion.get());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminRegion(@PathVariable("id") int id) {
        repo.deleteById(id);
        return new ResponseEntity<>("Admin region deleted with success", HttpStatus.OK);
    }
    
}
