/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.webService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import rojo.signalement.model.MasterAdmin;
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
import rojo.signalement.model.Token;
import rojo.signalement.repository.MasterAdminRepository;
import rojo.signalement.repository.TokenRepository;

/**
 *
 * @author ASUSROG
 */
@RestController
@RequestMapping("/MasterAdmin")
public class MasterAdminController {

//    BCryptPasswordEncoder crypt;
    
    @Autowired
    MasterAdminRepository masterRepo;
    @Autowired
    TokenRepository tokenRepo;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List getMasterAdmin() {
        return masterRepo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public MasterAdmin getMasterAdminById(@PathVariable("id") int id) {
        MasterAdmin masterAdmin = masterRepo.findById(id).get();
        return masterAdmin;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<MasterAdmin> createMasterAdmin(@RequestBody MasterAdmin masterAdmin) {
        MasterAdmin master = masterRepo.save(masterAdmin);
        return new ResponseEntity<>(master, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<MasterAdmin> updateMasterAdmin(@PathVariable("id") int id, @RequestBody MasterAdmin m) {
        Optional<MasterAdmin> masterAdmin = masterRepo.findById(id);
        masterAdmin.get().setNom(m.getNom());
        masterAdmin.get().setMdp(m.getMdp());
        MasterAdmin updated = masterRepo.save(masterAdmin.get());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMasterAdmin(@PathVariable("id") int id) {
        masterRepo.deleteById(id);
        return new ResponseEntity<>("Master Admin deleted with success", HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody MasterAdmin masterAdmin) {
//        try {
//            String nom = masterAdmin.getNom();
//            String mdp = masterAdmin.getMdp();
//            MasterAdmin m = masterRepo.login(nom, mdp);
//            Date date = new Date();
//            long timeMilli = date.getTime();
//            String timeMilliString = String.valueOf(timeMilli);
//            String tokenBase = nom + mdp + timeMilliString;
//            MessageDigest digest=MessageDigest.getInstance("SHA-256");
//            MessageDigest digest2=MessageDigest.getInstance("SHA-1");
//            byte[] encode=digest.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
//            byte[] encode2=digest2.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
////            String tokenString = org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenBase);
//
//            Token token = new Token(encode.toString()+encode2.toString(), m.getId(), "MasterAdmin");
//            tokenRepo.save(token);
//            return new ResponseEntity<>(token, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MasterAdmin masterAdmin) {
        try {
            String nom = masterAdmin.getNom();
            String mdp = masterAdmin.getMdp();
            MasterAdmin m = masterRepo.login(nom, mdp);
            Date date = new Date();
            long timeMilli = date.getTime();
            String timeMilliString = String.valueOf(timeMilli);
            String tokenBase = nom + mdp + timeMilliString;
//            MessageDigest digest=MessageDigest.getInstance("SHA-256");
//            MessageDigest digest2=MessageDigest.getInstance("SHA-1");
//            byte[] encode=digest.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
//            byte[] encode2=digest2.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
            String tokenString = org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenBase);

            Token token = new Token(tokenString, m.getId(), "MasterAdmin");
            tokenRepo.save(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
