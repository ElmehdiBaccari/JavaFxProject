/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author thaer
 */
public class emprunt {
        private int id_emprunt;
	private int id_user ;
	private int id_livre ;  
        private String nom;
        private String prenom;
        private String titre_livre;
	private LocalDate d_emprunt ;
	private LocalDate d_retour ;
        private String mail;

    public emprunt() {
    }

    public emprunt(int id_emprunt, int id_user, int id_livre, String nom, String prenom, String titre_livre, LocalDate d_emprunt, LocalDate d_retour, String mail) {
        this.id_emprunt = id_emprunt;
        this.id_user = id_user;
        this.id_livre = id_livre;
        this.nom = nom;
        this.prenom = prenom;
        this.titre_livre = titre_livre;
        this.d_emprunt = d_emprunt;
        this.d_retour = d_retour;
        this.mail = mail;
    }

    public emprunt(String nom, String prenom, String titre_livre, LocalDate d_emprunt, LocalDate d_retour, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.titre_livre = titre_livre;
        this.d_emprunt = d_emprunt;
        this.d_retour = d_retour;
        this.mail = mail;
    }

    public int getId_emprunt() {
        return id_emprunt;
    }

    public void setId_emprunt(int id_emprunt) {
        this.id_emprunt = id_emprunt;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTitre_livre() {
        return titre_livre;
    }

    public void setTitre_livre(String titre_livre) {
        this.titre_livre = titre_livre;
    }

    public LocalDate getD_emprunt() {
        return d_emprunt;
    }

    public void setD_emprunt(LocalDate d_emprunt) {
        this.d_emprunt = d_emprunt;
    }

    public LocalDate getD_retour() {
        return d_retour;
    }

    public void setD_retour(LocalDate d_retour) {
        this.d_retour = d_retour;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final emprunt other = (emprunt) obj;
        if (this.id_emprunt != other.id_emprunt) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_livre != other.id_livre) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.titre_livre, other.titre_livre)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.d_emprunt, other.d_emprunt)) {
            return false;
        }
        if (!Objects.equals(this.d_retour, other.d_retour)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "emprunt{" + "id_emprunt=" + id_emprunt + ", id_user=" + id_user + ", id_livre=" + id_livre + ", nom=" + nom + ", prenom=" + prenom + ", titre_livre=" + titre_livre + ", d_emprunt=" + d_emprunt + ", d_retour=" + d_retour + ", mail=" + mail + '}';
    }


        
}