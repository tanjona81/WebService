/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rojo.signalement.model.Signalement;
import rojo.signalement.repository.SignalementRepository;

@Service
@Transactional
public class SignalementService {

    @Autowired
    private SignalementRepository repo;

    public List<Signalement> listAllSignalement() {
        return repo.findAll();
    }

    public void saveSignalement(Signalement signalement) {
        repo.save(signalement);
    }

    public Signalement getSignalement(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteSignalement(Integer id) {
        repo.deleteById(id);
    }
}
