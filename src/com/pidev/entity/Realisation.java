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
public class Realisation {
    private int id;
    private int idfilm;
    private int idacteur;
    private String nomFilm;
    private GenreEnum genreFilm;
    private String descriptionFilm;
    private String nomActeur;
    private String prenomActeur;
    private String nationaliteActeur;
    private Date date_realisation;

    public Realisation() {
    }

    public Realisation(int idfilm, int idacteur) {
        this.idfilm = idfilm;
        this.idacteur = idacteur;
    }

    public Realisation(String nomFilm, GenreEnum genreFilm, String nomActeur, String prenomActeur, String nationaliteActeur) {
        this.nomFilm = nomFilm;
        this.genreFilm = genreFilm;
        this.nomActeur = nomActeur;
        this.prenomActeur = prenomActeur;
        this.nationaliteActeur = nationaliteActeur;
        
        
    }

    public Realisation(int id, int idfilm, int idacteur, String nomFilm, GenreEnum genreFilm, String descriptionFilm, String nomActeur, String prenomActeur, String nationaliteActeur) {
        this.id = id;
        this.idfilm = idfilm;
        this.idacteur = idacteur;
        this.nomFilm = nomFilm;
        this.genreFilm = genreFilm;
        this.descriptionFilm = descriptionFilm;
        this.nomActeur = nomActeur;
        this.prenomActeur = prenomActeur;
        this.nationaliteActeur = nationaliteActeur;
        
    }

    public Realisation(int id, String nomFilm, GenreEnum genreFilm, String nomActeur, String prenomActeur, String nationaliteActeur) {
        this.id = id;
        this.nomFilm = nomFilm;
        this.genreFilm = genreFilm;
        this.nomActeur = nomActeur;
        this.prenomActeur = prenomActeur;
        this.nationaliteActeur = nationaliteActeur;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNomFilm() {
        return nomFilm;
    }

    public void setNomFilm(String nomFilm) {
        this.nomFilm = nomFilm;
    }

    public GenreEnum getGenreFilm() {
        return genreFilm;
    }

    public void setGenreFilm(GenreEnum genreFilm) {
        this.genreFilm = genreFilm;
    }

    public String getDescriptionFilm() {
        return descriptionFilm;
    }

    public void setDescriptionFilm(String descriptionFilm) {
        this.descriptionFilm = descriptionFilm;
    }

    public String getNomActeur() {
        return nomActeur;
    }

    public void setNomActeur(String nomActeur) {
        this.nomActeur = nomActeur;
    }

    public String getPrenomActeur() {
        return prenomActeur;
    }

    public void setPrenomActeur(String prenomActeur) {
        this.prenomActeur = prenomActeur;
    }

    public String getNationaliteActeur() {
        return nationaliteActeur;
    }

    public void setNationaliteActeur(String nationaliteActeur) {
        this.nationaliteActeur = nationaliteActeur;
    }

  

    public int getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public int getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(int idacteur) {
        this.idacteur = idacteur;
    }

    public Date getDate_realisation() {
        return date_realisation;
    }

    public void setDate_realisation(Date date_realisation) {
        this.date_realisation = date_realisation;
    }

    @Override
    public String toString() {
        return "Realisation{" + "idfilm=" + idfilm + ", idacteur=" + idacteur + ", date_realisation=" + date_realisation + '}';
    }


    
    
    
}
