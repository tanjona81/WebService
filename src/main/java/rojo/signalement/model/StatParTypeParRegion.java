/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Stat_par_type_par_region")
public class StatParTypeParRegion implements Serializable{
    @Id
    @Column(name = "id_type_signalement")
    private int idTypeSignalement;
    @Column(name = "id_region")
    private int idRegion;
    @Column(name = "nbr_signalement")
    private double nbrSignalement;
    @Column(name = "nom_region")
    private String nomRegion;
    @Column(name = "nom_type_signalement")
    private String nomTypeSignalement;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getIdTypeSignalement() {
        return idTypeSignalement;
    }

    public void setIdTypeSignalement(int idTypeSignalement) {
        this.idTypeSignalement = idTypeSignalement;
    }

    public double getNbrSignalement() {
        return nbrSignalement;
    }

    public void setNbrSignalement(double nbrSignalement) {
        this.nbrSignalement = nbrSignalement;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public String getNomTypeSignalement() {
        return nomTypeSignalement;
    }

    public void setNomTypeSignalement(String nomTypeSignalement) {
        this.nomTypeSignalement = nomTypeSignalement;
    }

    public StatParTypeParRegion() {
    }

    public StatParTypeParRegion(int idRegion, int idTypeSignalement, double nbrSignalement, String nomRegion, String nomTypeSignalement) {
        this.idRegion = idRegion;
        this.idTypeSignalement = idTypeSignalement;
        this.nbrSignalement = nbrSignalement;
        this.nomRegion = nomRegion;
        this.nomTypeSignalement = nomTypeSignalement;
    }
}
