/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import rojo.signalement.model.Signalement;

/**
 *
 * @author user
 */
@Repository
public interface SignalementRepository extends MongoRepository<Signalement,Integer>{
    @Query("{etat:'Demande'}")
    public List<Signalement> getListe();
}
