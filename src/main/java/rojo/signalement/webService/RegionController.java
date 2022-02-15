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
import rojo.signalement.model.Region;
import rojo.signalement.service.RegionService;

@RestController
@RequestMapping("/Region")
public class RegionController {

    @Autowired
    RegionService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List<Region> list() {
        return service.listAllRegion();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Region> get(@PathVariable Integer id) {
        try {
            Region region = service.getRegion(id);
            return new ResponseEntity<Region>(region, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Region>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<Region> add(@RequestBody Region region) {
        try {
            service.saveRegion(region);
            return new ResponseEntity<Region>(region, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Region>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Region region, @PathVariable Integer id) {
        try {
            Region existRegion = service.getRegion(id);
            existRegion.setId(region.getId());
            existRegion.setNomRegion(region.getNomRegion());

            service.saveRegion(existRegion);
            return new ResponseEntity<>(existRegion, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteRegion(id);
    }
}
