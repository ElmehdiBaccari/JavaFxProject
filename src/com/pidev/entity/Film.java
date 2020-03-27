/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;


import connexionbd.GenreEnum;
import java.util.Date;

/**
 *
 * @author Mimi
 */
public class Film {
    private int id ;
    private String nom;
    private GenreEnum genre;
    private double prix ; 
    private String description ;
    private String image ;
    private Date date_debut;
    private Date date_fin;

    public Film(int id, String nom, GenreEnum genre, double prix, String description, String image, Date date_debut, Date date_fin) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Film(String nom, GenreEnum genre, double prix, String description, String image, Date date_debut, Date date_fin) {
        this.nom = nom;
        this.genre = genre;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Film() {}

    public Film(int id, String nom, GenreEnum genre, double prix, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

     public Film(int id,String nom, GenreEnum genre, double prix, String description) {
        this.id=id;
        this.nom = nom;
        this.genre = genre;
        this.prix = prix;
        this.description = description;
       
    }

    public Film(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
     

   
       public Film(String nom, GenreEnum genre, double prix, String description) {
        this.nom = nom;
        this.genre = genre;
        this.prix = prix;
        this.description = description;
    }
    public Film(String nom, GenreEnum genre, double prix, String description, String image) {
        this.nom = nom;
        this.genre = genre;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public double getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
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
        final Film other = (Film) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", nom=" + nom + ", genre=" + genre + ", prix=" + prix + ", description=" + description + ", image=" + image + '}';
    }
    
    
    
}
