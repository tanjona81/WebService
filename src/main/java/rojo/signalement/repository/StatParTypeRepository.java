/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rojo.signalement.model.StatParType;

/**
 *
 * @author user
 */
@Repository
public interface StatParTypeRepository extends JpaRepository<StatParType,Integer>{
    
}
