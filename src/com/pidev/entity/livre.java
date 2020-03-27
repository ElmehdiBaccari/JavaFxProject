/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;


import java.time.LocalDate;
import java.util.Date;
import javafx.collections.ObservableList;
/**
 *
 * @author thaer
 */
public class livre {

    
    
    private int id_livre;
    private String titre_livre;
    private LocalDate date_edition;
    private double prix_livre;
    private int qte_livre;
    public categorie_enum categorie_livre;
    public String img ;
     public enum categorie_enum {
    economie,medecine,art,sport,scientifique,informatique
     }
    
     
    public livre() {
    }

    public livre(int id_livre, String titre_livre, LocalDate date_edition, double prix_livre, int qte_livre, categorie_enum categorie_livre, String img) {
        this.id_livre = id_livre;
        this.titre_livre = titre_livre;
        this.date_edition = date_edition;
        this.prix_livre = prix_livre;
        this.qte_livre = qte_livre;
        this.categorie_livre = categorie_livre;
        this.img = img;
    }

    

 
    public livre(String titre_livre, LocalDate date_edition, double prix_livre, int qte_livre,categorie_enum categorie_livre) {
        this.titre_livre = titre_livre;
        this.date_edition = date_edition;
        this.prix_livre = prix_livre;
        this.qte_livre = qte_livre;
        this.categorie_livre=categorie_livre;
    }

    public livre(int id_livre, String titre_livre, LocalDate date_edition, double prix_livre, int qte_livre,categorie_enum categorie_livre) {
        this.id_livre = id_livre;
        this.titre_livre = titre_livre;
        this.date_edition = date_edition;
        this.prix_livre = prix_livre;
        this.qte_livre = qte_livre;
        this.categorie_livre=categorie_livre;
    }

    public livre(String titre_livre, LocalDate date_edition, double prix_livre, int qte_livre, categorie_enum categorie_livre, String img) {
        this.titre_livre = titre_livre;
        this.date_edition = date_edition;
        this.prix_livre = prix_livre;
        this.qte_livre = qte_livre;
        this.categorie_livre = categorie_livre;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

   

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public String getTitre_livre() {
        return titre_livre;
    }

    public void setTitre_livre(String titre_livre) {
        this.titre_livre = titre_livre;
    }

    public LocalDate getDate_edition() {
        return date_edition;
    }

    public void setDate_edition(LocalDate date_edition) {
        this.date_edition = date_edition;
    }

  

    public double getPrix_livre() {
        return prix_livre;
    }

    public void setPrix_livre(double prix_livre) {
        this.prix_livre = prix_livre;
    }

    public int getQte_livre() {
        return qte_livre;
    }

    public void setQte_livre(int qte_livre) {
        this.qte_livre = qte_livre;
    }

   public categorie_enum getCategorie_livre() {
        return categorie_livre;
    }

    public void setCategorie_livre(categorie_enum categorie_livre) {
        this.categorie_livre = categorie_livre;
    }

    @Override
    public String toString() {
        return "livre{" + "id_livre=" + id_livre + ", titre_livre=" + titre_livre + ", date_edition=" + date_edition + ", prix_livre=" + prix_livre + ", qte_livre=" + qte_livre + '}';
    }
    
    
    
}
