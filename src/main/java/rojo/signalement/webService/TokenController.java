/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.webService;

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
import org.springframework.web.bind.annotation.RestController;
import rojo.signalement.model.Token;
import rojo.signalement.repository.TokenRepository;

@RestController
@RequestMapping("/Token")
public class TokenController {

    @Autowired
    TokenRepository tokenRepo;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("")
    public List getTokens() {
        return tokenRepo.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/{id}")
//    public Token getTokenById(@PathVariable("id") String token) {
//        Token result = tokenRepo.findById(token).get();
//        return result;
//    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("")
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        Token tokenRes = tokenRepo.save(token);
        return new ResponseEntity<>(tokenRes, HttpStatus.CREATED);
    }
    
    public int checkToken(String token,String type){
        int rep=0;
        Token tok= tokenRepo.getTokenSpecifier(token, type);
        return rep;
    }

    public boolean testToken(String token, int idUser, String typeUser) {
        try {
            Optional<Token> tokenn = tokenRepo.findById(token);
            if (tokenn.get().getIdUser() == idUser && tokenn.get().getTypeUser().equals(typeUser)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public String generateToken(String email, String mdp) {
        Date date = new Date();
        long dateLong = date.getTime();
        String tokenString = email + mdp + dateLong;
        String token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(tokenString);
        return token;
    }

    public boolean testTokenUser(String token, int idUser) {
        return this.testToken(token, idUser, "User");
    }

    public boolean testTokenAdminRegion(String token, int idAdminRegion) {
        return this.testToken(token, idAdminRegion, "AdminRegion");
    }

    public boolean testTokenMasterAdmin(String token, int idMasterAdmin) {
        return this.testToken(token, idMasterAdmin, "MasterAdmin");
    }
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PutMapping("/{id}")
//    public ResponseEntity<Token> updateToken(@PathVariable("id") String id, @RequestBody Token tokenBody) {
//        Optional<Token> token = tokenRepo.findById(id);
//        token.get().setToken(tokenBody.getToken());
//        token.get().setIdUser(tokenBody.getIdUser());
//        token.get().setTypeUser(tokenBody.getTypeUser());
//        Token updated = tokenRepo.save(token.get());
//        return new ResponseEntity<>(updated, HttpStatus.OK);
//    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteToken(@PathVariable("id") String id) {
//        tokenRepo.deleteById(id);
//        return new ResponseEntity<>("Token deleted with success", HttpStatus.OK);
//    }
}
