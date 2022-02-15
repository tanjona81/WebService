/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rojo.signalement.model.Signalement;
import rojo.signalement.model.StatParTypeParRegion;

/**
 *
 * @author user
 */
@Repository
public interface StatParTypeParRegionRepository extends JpaRepository<StatParTypeParRegion,Integer>{
    @Transactional
    @Modifying
    @Query(value="Select * from Stat_par_type_par_region s where s.id_region= :id",nativeQuery = true)
    List<StatParTypeParRegion> getStat(@Param("id") int id);
//    public List<StatParTypeParRegion> getStat(@Param("id") int id);
}
