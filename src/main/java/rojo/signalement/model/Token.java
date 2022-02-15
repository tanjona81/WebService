/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rojo.signalement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Token")
public class Token {

    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "type_user")
    private String typeUser;

    public Token() {
    }

    public Token(String token, int idUser, String typeUser) {
        this.token = token;
        this.idUser = idUser;
        this.typeUser = typeUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
