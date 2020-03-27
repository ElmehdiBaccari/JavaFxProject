/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;

/**
 *
 * @author Damdoub
 */
public class Salle {
    
    int id ;
    String nom ;
    String type ;
    int nbrplace;
    String statut ;
    String photo ;
    String description;
    String disponibilite ;

    public Salle() {
    }

    public Salle(int id, String nom, String type, int nbrplace, String photo, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.nbrplace = nbrplace;
        this.photo = photo;
        this.description = description;
    }

    public Salle(String nom, String type, int nbrplace, String photo, String description) {
        this.nom = nom;
        this.type = type;
        this.nbrplace = nbrplace;
        this.photo = photo;
        this.description = description;
    }

    public Salle(int id, String nom, String type, int nbrplace, String statut, String photo, String description, String disponibilite) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.nbrplace = nbrplace;
        this.statut = statut;
        this.photo = photo;
        this.description = description;
        this.disponibilite = disponibilite;
    }

    public Salle(int id, String nom, String type, int nbrplace, String statut, String photo, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.nbrplace = nbrplace;
        this.statut = statut;
        this.photo = photo;
        this.description = description;
    }

    public Salle(String nom, String type, int nbrplace, String statut, String photo, String description) {
        this.nom = nom;
        this.type = type;
        this.nbrplace = nbrplace;
        this.statut = statut;
        this.photo = photo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", nbrplace=" + nbrplace + ", statut=" + statut + ", photo=" + photo + ", description=" + description + '}';
    }
    
    
}
