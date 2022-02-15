/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "signalement_affecte")
public class SignalementAffecte {
    @Id
    private int idSignalement;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_region")
    private int idRegion;
    @Column(name = "id_type_signalement")
    private int idTypeSignalement;
    @Column(name = "date_signalement")
    private Date dateSignalement;
    @Column(name = "etat")
    private String etat;

    public int getIdSignalement() {
        return idSignalement;
    }

    public void setIdSignalement(int idSignalement) {
        this.idSignalement = idSignalement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

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

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public SignalementAffecte() {
    }

    public SignalementAffecte(int idSignalement, int idUser, int idRegion, int idTypeSignalement, Date dateSignalement, String etat) {
        this.idSignalement = idSignalement;
        this.idUser = idUser;
        this.idRegion = idRegion;
        this.idTypeSignalement = idTypeSignalement;
        this.dateSignalement = dateSignalement;
        this.etat = etat;
    }
}
