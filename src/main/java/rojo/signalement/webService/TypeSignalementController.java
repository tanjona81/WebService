/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rojo.signalement.model.TypeSignalement;
import rojo.signalement.service.TypeSignalementService;

@RestController
@RequestMapping("/TypeSignalement")
public class TypeSignalementController {

    @Autowired
    TypeSignalementService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List<TypeSignalement> list() {
        return service.listAllTypeSignalement();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<TypeSignalement> get(@PathVariable Integer id) {
        try {
            TypeSignalement type = service.getTypeSignalement(id);
            return new ResponseEntity<TypeSignalement>(type, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<TypeSignalement>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<TypeSignalement> add(@RequestBody TypeSignalement type) {
        try {
            service.saveTypeSignalement(type);
            return new ResponseEntity<TypeSignalement>(type, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<TypeSignalement>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TypeSignalement type, @PathVariable Integer id) {
        try {
            TypeSignalement existType = service.getTypeSignalement(id);
            existType.setIdTypeSignalement(type.getIdTypeSignalement());
            existType.setNomTypeSignalement(type.getNomTypeSignalement());
            service.saveTypeSignalement(existType);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteTypeSignalement(id);
    }
}
