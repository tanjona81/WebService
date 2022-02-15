/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rojo.signalement.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "signalement")
public class Signalement implements Serializable {

    @Id
    private int idSignalement;
    @Field(value = "id_user")
    private int idUser;
    @Field(value = "nom_user")
    private String nomUser;
    @Field(value = "prenom_user")
    private String prenomUser;
    @Field(value = "id_type_signalement")
    private int idTypeSignalement;
    @Field(value = "type_signalement")
    private String typeSignalement;
    @Field(value = "positionx")
    private double positionx;
    @Field(value = "positiony")
    private double positiony;
    @Field(value = "date_signalement")
    private Date dateSignalement;
    @Field(value = "description")
    private String description;
    @Field(value = "etat")
    private String etat;
    @Field(value = "photo")
    private String photo;

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

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public int getIdTypeSignalement() {
        return idTypeSignalement;
    }

    public void setIdTypeSignalement(int idTypeSignalement) {
        this.idTypeSignalement = idTypeSignalement;
    }

    public String getTypeSignalement() {
        return typeSignalement;
    }

    public void setTypeSignalement(String typeSignalement) {
        this.typeSignalement = typeSignalement;
    }

    public double getPositionx() {
        return positionx;
    }

    public void setPositionx(double positionx) {
        this.positionx = positionx;
    }

    public double getPositiony() {
        return positiony;
    }

    public void setPositiony(double positiony) {
        this.positiony = positiony;
    }

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Signalement(int idSignalement, int idUser, String nomUser, String prenomUser, int idTypeSignalement, String typeSignalement, double positionx, double positiony, Date dateSignalement, String description, String etat, String photo) {
        this.idSignalement = idSignalement;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.idTypeSignalement = idTypeSignalement;
        this.typeSignalement = typeSignalement;
        this.positionx = positionx;
        this.positiony = positiony;
        this.dateSignalement = dateSignalement;
        this.description = description;
        this.etat = etat;
        this.photo = photo;
    }

}
