/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rojo.signalement.model.TypeSignalement;

@Repository
public interface TypeSignalementRepository extends JpaRepository<TypeSignalement, Integer> {
    
}
