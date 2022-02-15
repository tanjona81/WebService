/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rojo.signalement.model.TypeSignalement;
import rojo.signalement.repository.TypeSignalementRepository;

@Service
@Transactional
public class TypeSignalementService {

    @Autowired
    private TypeSignalementRepository typeRepo;

    public List<TypeSignalement> listAllTypeSignalement() {
        return typeRepo.findAll();
    }

    public void saveTypeSignalement(TypeSignalement type) {
        typeRepo.save(type);
    }

    public TypeSignalement getTypeSignalement(Integer id) {
        return typeRepo.findById(id).get();
    }

    public void deleteTypeSignalement(Integer id) {
        typeRepo.deleteById(id);
    }
}
