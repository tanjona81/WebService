/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rojo.signalement.model.AdminRegion;
import rojo.signalement.model.Signalement;

@Repository
public interface AdminRegionRepository extends JpaRepository<AdminRegion, Integer> {

//    @Query("{nom:?0,mdp:?1}")
//    AdminRegion login(String nom, String mdp);
    
    @Transactional
//    @Modifying
    @org.springframework.data.jpa.repository.Query(value="select * from admin_region where nom= :nom and mdp= :mdp",nativeQuery = true)
    AdminRegion login(String nom, String mdp);
}
