/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;

import java.sql.PreparedStatement;
import java.time.LocalDate;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author Duroy Mehdi
 */
public class Musee {
    
    int id;
    String nom;
    Date arrive;
    String prenom;
    String email;
    int  telephone;
    int personne;
    Float fee;

    public Musee() {
       
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getArrive() {
        return arrive;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }

    public int getPersonne() {
        return personne;
    }

    public Float getFee() {
        return fee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Musee(int id, String nom, Date arrive, String prenom, String email, int telephone, int personne, Float fee) {
        this.id = id;
        this.nom = nom;
        this.arrive = arrive;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.personne = personne;
        this.fee = fee;
    }

    
    
    public Musee(String nom, Date arrive, String prenom, String email, int telephone, int personne, Float fee) {
        this.nom = nom;
        this.arrive = arrive;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.personne = personne;
        this.fee = fee;
    }

    public Musee( String nom,String prenom, String email, int telephone, int personne, Float fee) {
       this.nom=nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.personne = personne;
        this.fee = fee;
        
    }
    
                 

    
    
    
    @Override
    public String toString() {
        return "Musee{" + "nom=" + nom + ", arrive=" + arrive + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone + ", personne=" + personne + ", fee=" + fee + '}';
    }

    
    
    
    
    
    
    
    
    
}
