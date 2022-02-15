/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.webService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rojo.signalement.model.AdminRegion;
import rojo.signalement.model.Token;
import rojo.signalement.repository.AdminRegionRepository;
import rojo.signalement.repository.TokenRepository;

@RestController
@RequestMapping("AdminRegion")
public class AdminRegionController {

    @Autowired
    AdminRegionRepository adminRepo;
    @Autowired
    TokenRepository tokenRepo;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List getAdminRegion() {
        return adminRepo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public AdminRegion getAdminRegionById(@PathVariable("id") int id) {
        AdminRegion adm = adminRepo.findById(id).get();
        return adm;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("")
    public ResponseEntity<AdminRegion> createAdminRegion(@RequestBody AdminRegion adminRegion) {
        AdminRegion admin = adminRepo.save(adminRegion);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<AdminRegion> updateAdminRegion(@PathVariable("id") int id, @RequestBody AdminRegion m) {
        Optional<AdminRegion> adminRegion = adminRepo.findById(id);
        adminRegion.get().setNom(m.getNom());
        adminRegion.get().setMdp(m.getMdp());
        adminRegion.get().setIdRegion(m.getIdRegion());
        AdminRegion updated = adminRepo.save(adminRegion.get());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminRegion(@PathVariable("id") int id) {
        adminRepo.deleteById(id);
        return new ResponseEntity<>("Admin region deleted with success", HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/login")
//    public ResponseEntity<Token> login(@RequestBody AdminRegion adminRegion) {
//        try {
//            String nom = adminRegion.getNom();
//            String mdp = adminRegion.getMdp();
//            AdminRegion m = adminRepo.login(nom, mdp);
//            Date date = new Date();
//            long timeMilli = date.getTime();
//            String timeMilliString = String.valueOf(timeMilli);
//            String tokenBase = nom + mdp + timeMilliString;
//            MessageDigest digest=MessageDigest.getInstance("SHA-256");
//            MessageDigest digest2=MessageDigest.getInstance("SHA-1");
//            byte[] encode=digest.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
//            byte[] encode2=digest2.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
////            String tokenString = org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenBase);
//            Token token = new Token(encode.toString()+encode2.toString(), m.getId(), "AdminRegion");
//            tokenRepo.save(token);
//            return new ResponseEntity<>(token, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Token>(HttpStatus.NOT_FOUND);
//        }
//    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestParam("nom") String nom, @RequestParam("mdp") String mdp) {
//        try {
//            String nom = adminRegion.getNom();
//            String mdp = adminRegion.getMdp();
            AdminRegion m = adminRepo.login(nom, mdp);
            Date date = new Date();
            long timeMilli = date.getTime();
            String timeMilliString = String.valueOf(timeMilli);
            String tokenBase = nom + mdp + timeMilliString;
//            MessageDigest digest=MessageDigest.getInstance("SHA-256");
//            MessageDigest digest2=MessageDigest.getInstance("SHA-1");
//            byte[] encode=digest.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
//            byte[] encode2=digest2.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
            String tokenString = org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenBase);
            Token token = new Token(tokenString, m.getId(), "AdminRegion");
            tokenRepo.save(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Token>(HttpStatus.NOT_FOUND);
//        }
    }
}
