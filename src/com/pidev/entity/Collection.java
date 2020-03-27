/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Duroy Mehdi
 */
public class Collection {
    int  num_collection;
    String nom_collection;
    Date date_collection;
    String description ;
    String image;
    ImageView imgview;

    

    public Collection() {
    }

    public Collection( String nom_collection, Date date_collection, String description) {
        this.nom_collection = nom_collection;
        this.date_collection = date_collection;
        this.description = description;
    }

    public int getNum_collection() {
        return num_collection;
    }

    
    public String getNom_collection() {
        return nom_collection;
    }

    public Date getDate_collection() {
        return date_collection;
    }

    public String getDescription() {
        return description;
    }

    public void setNum_collection(int num_collection) {
        this.num_collection = num_collection;
    }

    public void setNom_collection(String nom_collection) {
        this.nom_collection = nom_collection;
    }

    public void setDate_collection(Date date_collection) {
        this.date_collection = date_collection;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection(String nom_collection, Date date_collection,String image,String description) {
        this.nom_collection = nom_collection;
        this.date_collection = date_collection;
        this.description = description;
        this.image = image;
    }

    public Collection(int num_collection, String nom_collection, String description, String image, ImageView imgview) {
        this.num_collection = num_collection;
        this.nom_collection = nom_collection;
        this.description = description;
        this.image = image;
        this.imgview = imgview;
    }
    
    
    
    
    
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    
    
    //////////
    
    
    
   
    
     public static List<Collection> generateImageViews(List<Collection> images) {
        List<Collection> liste = new ArrayList<Collection>();

        for (Collection image : images) {
            File f = new File("D:/wamp/www/image/"+ image.getImage());
            image.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(image);
        }
        return liste;
    }
     
     
     public static ArrayList<Collection> generateImageViews(ArrayList<Collection> images) {
        ArrayList<Collection> liste = new ArrayList<Collection>();

        for (Collection image : images) {
            File f = new File("D:/wamp/www/image/"+ image.getImage());
            image.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(image);
        }
        return liste;
    }
     public static Collection generateImageViews(Collection image) {
        
         Collection a;
        
            File f = new File("D:/wamp/www/image/" + image.getImage());
            image.setImgview(new ImageView(new Image(f.toURI().toString())));
            a=image;
        
        return a;
    }



    public Collection(int num_collection, String nom_collection, Date date_collection,  String image ,String description) {
        this.num_collection = num_collection;
        this.nom_collection = nom_collection;
        this.date_collection = date_collection;
        this.description = description;
        this.image = image;
    }

    public Collection(String nom_collection, Date date_collection, String description, String image, ImageView imgview) {
        this.nom_collection = nom_collection;
        this.date_collection = date_collection;
        this.description = description;
        this.image = image;
        this.imgview = imgview;
    }

    public Collection(String nom_collection, String description, String image) {
        this.nom_collection = nom_collection;
        this.description = description;
        this.image = image;
    }

    public Collection(String nom_collection, String description) {
        this.nom_collection = nom_collection;
        this.description = description;
    }

    
    
    
    @Override
    public String toString() {
        return "Collection{" + "num_collection=" + num_collection + ", nom_collection=" + nom_collection + ", date_collection=" + date_collection + ", description=" + description + ", image=" + image + ", imgview=" + imgview + '}';
    }
    
    
     
    
    
    
    
    
}
