/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.webService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;
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
import rojo.signalement.model.Signalement;
import rojo.signalement.model.Test;
import rojo.signalement.model.Token;
import rojo.signalement.model.Utilisateur;
import rojo.signalement.repository.TokenRepository;
import rojo.signalement.repository.UtilisateurRepository;
import rojo.signalement.service.SignalementService;

@RestController
@RequestMapping("/Utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository userRepo;
    @Autowired
    TokenRepository tokenRepo;
    @Autowired
    TokenController tokenController;
    @Autowired
    SignalementController signalementController;
    @Autowired
    SignalementService serviceSignalement;
    

    @GetMapping("/Test")
    public Test test() {
        return new Test(1,"some text");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List getUsers() {
        return userRepo.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public Utilisateur getUserById(@PathVariable("id") int id) {
        Utilisateur user = userRepo.findById(id).get();
        return user;
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("")
//    public ResponseEntity<Utilisateur> createAdminRegion(@RequestBody Utilisateur user) {
//        Utilisateur admin = userRepo.save(user);
//        return new ResponseEntity<>(admin, HttpStatus.CREATED);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateAdminRegion(@PathVariable("id") int id, @RequestBody Utilisateur userBody) {
        Optional<Utilisateur> user = userRepo.findById(id);
        user.get().setId(userBody.getId());
        user.get().setEmail(userBody.getEmail());
        user.get().setNom(userBody.getNom());
        user.get().setPrenom(userBody.getPrenom());
        user.get().setDateDeNaissance(userBody.getDateDeNaissance());
        user.get().setSexe(userBody.getSexe());
        user.get().setMdp(userBody.getMdp());
        Utilisateur updated = userRepo.save(user.get());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userRepo.deleteById(id);

        return new ResponseEntity<>("User deleted with success", HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/login")
//    public ResponseEntity<Token> login(@RequestBody Utilisateur user) {
//        try {
//            String email = user.getEmail();
//            String mdp = user.getMdp();
//            Utilisateur m = userRepo.login(email, mdp);
//            Date date = new Date();
//            long timeMilli = date.getTime();
//            String timeMilliString = String.valueOf(timeMilli);
//            String tokenBase = email + mdp + timeMilliString;
////            MessageDigest digest=MessageDigest.getInstance("SHA-256");
////            MessageDigest digest2=MessageDigest.getInstance("SHA-1");
////            byte[] encode=digest.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
////            byte[] encode2=digest2.digest(tokenBase.getBytes(StandardCharsets.UTF_16));
//            String tokenString = org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenBase);
//            Token token = new Token(tokenString, m.getId(), "User");
//            tokenRepo.save(token);
//            return new ResponseEntity<>(token, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<Token>(HttpStatus.NOT_FOUND);
//        }
//    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/Signup")
    public ResponseEntity<Utilisateur> inscription(@RequestBody Utilisateur utilisateur) {
        utilisateur.setId(0);
        if (this.testDetails(utilisateur) == true) {
//            hashing mdp
            utilisateur.setMdp(org.apache.commons.codec.digest.DigestUtils.sha256Hex(utilisateur.getMdp()));
            Utilisateur user = userRepo.save(utilisateur);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/Login")
    public ResponseEntity login(@RequestParam String email, @RequestParam String mdp) {
        try {
//            hashing mdp
            mdp = org.apache.commons.codec.digest.DigestUtils.sha256Hex(mdp);
            Utilisateur user = userRepo.login(email, mdp);
            if (user != null) {
                Token token = new Token(tokenController.generateToken(email, mdp), user.getId(), "User");
                tokenRepo.save(token);
                return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Tsy mety", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/Signalement")
    public ResponseEntity signaler(@RequestBody Signalement signalement,
            @RequestHeader String token) {
        Token tok=tokenRepo.getTokenSpecifier(token, "User");
        int idUser=tok.getIdUser();
        if (tokenController.testTokenUser(token, idUser)) {
            try {
                if (signalementController.testDoublureSignalement(signalement.getIdSignalement()) == false) {
                    signalement.setIdUser(idUser);
                    signalement = signalementController.getTrustedSignalement(signalement, idUser);
                    if (signalement != null) {
                        serviceSignalement.saveSignalement(signalement);
                        return new ResponseEntity<>(signalement, HttpStatus.CREATED);
                    } else {
                        return new ResponseEntity<>("FORBIDDEN",HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>("TOKEN",HttpStatus.FORBIDDEN);
        }
    }
    public boolean testDetails(Utilisateur utilisateur) {
        if (this.testDoublureEmail(utilisateur.getEmail()) == false) {
            return this.testEmail(utilisateur.getEmail()) && this.testMdp(utilisateur.getMdp())
                    && this.testString(utilisateur.getNom()) && this.testString(utilisateur.getPrenom())
                    && this.testString(utilisateur.getSexe()) && this.testString(utilisateur.getDateDeNaissance().toString());
        } else {
            return false;
        }
    }

    public boolean testDoublureEmail(String email) {
        try {
            return userRepo.searchEmail(email) != null;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean testString(String string) {
        if (string.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean testEmail(String email) {
        String[] split = email.split("@");
        try {
            if (Pattern.matches("^[-a-zA-Z0-9_.]+", split[0])) {
                return Pattern.matches("^[-a-zA-Z.]+", split[1]);
            } else {
                System.out.println(split[0]);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean testMdp(String mdp) {
        return !Pattern.matches(".*[àâäéèêëïîôöùûüÿç].*", mdp.trim()) && mdp.trim().length() >= 8;
    }
}
