/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import rojo.signalement.model.Token;
/**
 *
 * @author ASUSROG
 */
public interface TokenRepository extends JpaRepository<Token, String> {
    
    @Transactional
//    @Modifying
    @org.springframework.data.jpa.repository.Query(value="Select * from token s where s.token= :token and s.type_user= :type",nativeQuery = true)
    Token getTokenSpecifier(String token,String type);
}
