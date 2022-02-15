/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rojo.signalement.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
//    @Query("{email:?0,mdp:?1}")
//    Utilisateur login(String email, String mdp);
    
//    @Transactional
//    @Modifying
//    @org.springframework.data.jpa.repository.Query(value="Select * from utilisateur s where s.email= :email and s.mdp= :mdp",nativeQuery = true)
//    Utilisateur login(String email,String mdp);
    @Transactional
//    @Modifying
    @Query(value = "Select * from utilisateur s where s.email= :email and s.mdp= :mdp", nativeQuery = true)
    Utilisateur login(String email, String mdp);

    @Query(value = "SELECT * FROM utilisateur u WHERE u.email= :email", nativeQuery = true)
    Utilisateur searchEmail(String email);
}
