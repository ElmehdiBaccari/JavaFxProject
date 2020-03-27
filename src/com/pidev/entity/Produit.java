/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;


import connexionbd.TypeEnum;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Duroy Mehdi
 */
public class Produit {
    
     int id_produit;
    String nom_produit;
    TypeEnum type_produit;
    Double prix_produit;
    String image_produit;
    int quantite;
     ImageView imgview;
     


    public Produit() {
    }
    

    public Produit(int id_produit, String nom_produit, TypeEnum type_produit, Double prix_produit, String image_produit, int quantite) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.type_produit = type_produit;
        this.prix_produit = prix_produit;
        this.image_produit = image_produit;
        this.quantite = quantite;
       
    }
    
    
    

    public Produit(String nom_produit, TypeEnum type_produit, Double prix_produit, String image_produit, int quantite) {
        this.nom_produit = nom_produit;
        this.type_produit = type_produit;
        this.prix_produit = prix_produit;
        this.image_produit = image_produit;
        this.quantite = quantite;
        
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public TypeEnum  getType_produit() {
        return type_produit;
    }

    public Double getPrix_produit() {
        return prix_produit;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public int getQuantite() {
        return quantite;
    }

   

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setType_produit(TypeEnum type_produit) {
        this.type_produit = type_produit;
    }

    public void setPrix_produit(Double prix_produit) {
        this.prix_produit = prix_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    

   public ImageView getImgview() {
        return imgview;
    }

    public void setImgview(ImageView imgview) {
        this.imgview = imgview;
        imgview.setFitHeight(150);
imgview.setFitWidth(250);
imgview.setPreserveRatio(false);
    }
    
    
    
    
     public static List<Produit> generateImageViews(List<Produit> images) {
        List<Produit> liste = new ArrayList<Produit>();

        for (Produit image : images) {
            File f = new File("D:/wamp/www/image/" + image.getImage_produit());
            image.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(image);
        }
        return liste;
    }
     
     
     public static ArrayList<Produit> generateImageViews(ArrayList<Produit> images) {
        ArrayList<Produit> liste = new ArrayList<Produit>();

        for (Produit image : images) {
            File f = new File("D:/wamp/www/image/" + image.getImage_produit());
            image.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(image);
        }
        return liste;
    }
     public static Produit generateImageViews(Produit image) {
        
         Produit a;
        
            File f = new File("D:/wamp/www/image/" + image.getImage_produit());
            image.setImgview(new ImageView(new Image(f.toURI().toString())));
            a=image;
        
        return a;
    }

    public Produit(String nom_produit, String image_produit, int quantite) {
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
        this.quantite = quantite;
    }

    public Produit(String nom_produit, Double prix_produit, String image_produit, int quantite) {
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.image_produit = image_produit;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", type_produit=" + type_produit + ", prix_produit=" + prix_produit + ", image_produit=" + image_produit + ", quantite=" + quantite + ", imgview=" + imgview + '}';
    }
    
    
    
  
    
}
