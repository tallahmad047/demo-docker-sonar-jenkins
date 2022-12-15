package org.sid.demodockersonarjenkins.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    public  Users(String nom,String prenom,String email,String password){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.password=password;
    }
    private String nom;
    private  String prenom;
    private  String email;
    private  String password;
}
