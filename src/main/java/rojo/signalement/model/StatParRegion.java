/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Stat_par_region")
public class StatParRegion {
    @Id
    private int idRegion;
    @Column(name = "nbr_signalement")
    private int nbrSignalement;
    @Column(name = "nom_region")
    private String nomRegion;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getNbrSignalement() {
        return nbrSignalement;
    }

    public void setNbrSignalement(int nbrSignalement) {
        this.nbrSignalement = nbrSignalement;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public StatParRegion() {
    }

    public StatParRegion(int idRegion, int nbrSignalement, String nomRegion) {
        this.idRegion = idRegion;
        this.nbrSignalement = nbrSignalement;
        this.nomRegion = nomRegion;
    }
}
