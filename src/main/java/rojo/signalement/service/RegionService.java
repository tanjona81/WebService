/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rojo.signalement.model.Region;
import rojo.signalement.repository.RegionRepository;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository repo;

    public List<Region> listAllRegion() {
        return repo.findAll();
    }

    public void saveRegion(Region region) {
        repo.save(region);
    }

    public Region getRegion(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteRegion(Integer id) {
        repo.deleteById(id);
    }
}
