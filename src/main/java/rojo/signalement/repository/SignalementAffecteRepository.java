/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import rojo.signalement.model.SignalementAffecte;

/**
 *
 * @author user
 */
@Repository
public interface SignalementAffecteRepository extends JpaRepository<SignalementAffecte,Integer>{
//    @Transactional
//    @Modifying
//    @org.springframework.data.jpa.repository.Query(value="Select * from SignalementAffecte s where s.id_signalement= :idSignalement",nativeQuery = true)
//    SignalementAffecte getSpecifiqueSignalement(int idSignalement);
//    
//    @Transactional
//    @Modifying
//    @org.springframework.data.jpa.repository.Query(value="Select * from SignalementAffecte s where s.id_region= :idSignalement",nativeQuery = true)
//    SignalementAffecte getRegionSignale(int idSignalement);
//    
    @Transactional
//    @Modifying
    @org.springframework.data.jpa.repository.Query(value="Select * from Signalement_affecte s where s.id_region= :idRegion",nativeQuery = true)
    List<SignalementAffecte> getSignalementByIdRegion(int idRegion);
}
