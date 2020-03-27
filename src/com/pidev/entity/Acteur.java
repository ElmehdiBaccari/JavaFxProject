/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mimi
 */
public class Acteur {
    private int id ; 
    private String nom ; 
    private String prenom ; 
    private Date date_naissance;
    private String nationalite;
    

    public Acteur() {
    }
 public Acteur(int id,String nom, String prenom, Date date_naissance , String nationalite) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance=date_naissance;
        this.nationalite = nationalite;
    }
    public Acteur(String nom, String prenom, Date date_naissance  , String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance=date_naissance;
        this.nationalite = nationalite;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }


    public String getNationalite() {
        return nationalite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

  

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Acteur other = (Acteur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acteur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", nationalite=" + nationalite + '}';
    }

   
    
    
    
}
