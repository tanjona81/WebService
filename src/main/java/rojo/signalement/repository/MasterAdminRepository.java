/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import rojo.signalement.model.MasterAdmin;

@Repository
public interface MasterAdminRepository extends JpaRepository<MasterAdmin,Integer>{
//    @Query("{nom:?0,mdp:?1}")
//    MasterAdmin login(String nom,String mdp);
    
    @Transactional
//    @Modifying
    @org.springframework.data.jpa.repository.Query(value="Select * from master_admin s where s.nom= :nom and s.mdp= :mdp",nativeQuery = true)
    MasterAdmin login(String nom,String mdp);
}
