/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;


import java.time.LocalDate;
import java.util.Date;



/**
 *
 * @author Damdoub
 */

public class Utilisateur  {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private String nom;
   
    private String prenom;
   
    private String adressemail;
  
    private LocalDate datedenaissance;
  
    private String photo;
   
    private String motdepasse;
   
    private String statut;
    
    private int role;
    
    private int numero ;

    
    
    
    
    
    public Utilisateur(String nom, String prenom, String adressemail, LocalDate datedenaissance, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.motdepasse = motdepasse;
    }

    public Utilisateur(Integer id, String nom, String prenom, String adressemail, LocalDate datedenaissance, String photo, String motdepasse, String statut, int role, int numero) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.statut = statut;
        this.role = role;
        this.numero = numero;
    }

    public Utilisateur(Integer id, String nom, String prenom, String adressemail, LocalDate datedenaissance, String photo, String motdepasse, String statut, int numero) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.statut = statut;
        this.numero = numero;
    }
    

    public Utilisateur() {
    }

    public Utilisateur(Integer id) {
        this.id = id;
    }

    public Utilisateur(String nom, String prenom, String adressemail, LocalDate datedenaissance, String photo, String motdepasse, String statut, int role, int numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.statut = statut;
        this.role = role;
        this.numero = numero;
    }

    public Utilisateur(String nom, String prenom, String adressemail, LocalDate datedenaissance, String photo, String motdepasse, int numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.numero = numero;
    }

    
    
    
    public Utilisateur(Integer id, String nom, String prenom, String adressemail, LocalDate datedenaissance, String photo, String motdepasse, String statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.statut = statut;
    }

   

    public Integer getId() {
        return id;
    }

    public Utilisateur(String nom, String prenom, String adressemail, String photo, String motdepasse, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.statut = statut;
        this.datedenaissance=null ;
    }

    public Utilisateur(String nom, String prenom, String adressemail, String photo, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.photo = photo;
        this.motdepasse = motdepasse;
    }

    public Utilisateur(String nom, String prenom, String adressemail, LocalDate datedenaissance, String photo, String motdepasse, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.datedenaissance = datedenaissance;
        this.photo = photo;
        this.motdepasse = motdepasse;
        this.statut = statut;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
    }

    public LocalDate getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(LocalDate datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pidev.entity.Utilisateur[ id=" + id + " nom=" + nom+" prenom =" + prenom + " mail=" + adressemail+" date =" + datedenaissance + " numero=" + numero+"photo =" + photo + " statut=" + statut+" numero =" + role +"]";
    }
    
}
