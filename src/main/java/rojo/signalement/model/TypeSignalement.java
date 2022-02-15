/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_signalement")
public class TypeSignalement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "nom_type_signalement")
    String nomTypeSignalement;

    public int getIdTypeSignalement() {
        return id;
    }

    public void setIdTypeSignalement(int idTypeSignalement) {
        this.id = idTypeSignalement;
    }

    public String getNomTypeSignalement() {
        return nomTypeSignalement;
    }

    public void setNomTypeSignalement(String nomTypeSignalement) {
        this.nomTypeSignalement = nomTypeSignalement;
    }

    public TypeSignalement() {
    }

    public TypeSignalement(int idTypeSignalement, String nomTypeSignalement) {
        this.id = idTypeSignalement;
        this.nomTypeSignalement = nomTypeSignalement;
    }

}
