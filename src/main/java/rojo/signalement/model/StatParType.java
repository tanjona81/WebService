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
@Table(name = "Stat_par_type")
public class StatParType {
    @Id
    private int idTypeSignalement;
    @Column(name = "nbr_signalement")
    private int nbrSignalement;
    @Column(name = "nom_type_signalement")
    private String nomTypeSignalement;

    public int getIdTypeSignalement() {
        return idTypeSignalement;
    }

    public void setIdTypeSignalement(int idTypeSignalement) {
        this.idTypeSignalement = idTypeSignalement;
    }

    public int getNbrSignalement() {
        return nbrSignalement;
    }

    public void setNbrSignalement(int nbrSignalement) {
        this.nbrSignalement = nbrSignalement;
    }

    public String getNomTypeSignalement() {
        return nomTypeSignalement;
    }

    public void setNomTypeSignalement(String nomTypeSignalement) {
        this.nomTypeSignalement = nomTypeSignalement;
    }

    public StatParType() {
    }

    public StatParType(int idTypeSignalement, int nbrSignalement, String nomTypeSignalement) {
        this.idTypeSignalement = idTypeSignalement;
        this.nbrSignalement = nbrSignalement;
        this.nomTypeSignalement = nomTypeSignalement;
    }
    
}
