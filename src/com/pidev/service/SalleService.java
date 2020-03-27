/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import ViewController.ContainerController;
import com.pidev.entity.Salle;
import com.pidev.entity.Utilisateur;
import com.pidev.service.ISalleService.ISalle;
import connexionbd.ConnexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Damdoub
 */
public class SalleService implements ISalle<Salle>{

    
    
    
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    public SalleService() {
        
          connection=ConnexionBD.getInstance().getCnx();
        
        
    }

    @Override
    public void insert(Salle t) {
        try {
            String requete ="insert into salle(nom,type,nbrplace,photo,description) value('"+t.getNom()+"','"+t.getType()+"','"+t.getNbrplace()+"','"+t.getPhoto()+"','"+t.getDescription()+"')";
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Salle t) {
       
        try {
            String requete = " delete from salle where id='"+t.getId()+"'" ;
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(Salle t) {
        try {
            String requete = " update salle set nom='"+t.getNom()+"' , Type='"+t.getType()+"' , nbrplace='"+t.getNbrplace()+"' , statut='"+t.getStatut()+"' , photo='"+t.getPhoto()+"' , description='"+t.getDescription()+"' where id='"+t.getId()+"'" ;
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Salle searchByName(Salle t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Salle> getAll() {
           ObservableList<Salle> list = FXCollections.observableArrayList();
        try {
            String requete = " select * from salle" ;
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                
                list.add(new Salle(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list ;
    }

    @Override
    public ObservableList<Salle> getSalles() {
         ObservableList<Salle> list = FXCollections.observableArrayList();
        try {
            String requete = " select * from salle" ;
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                
                list.add(new Salle(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list ;
    }

    @Override
    public Salle getById(Salle t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnchorPane> getSalleContainer(String s) {
         String requete = "SELECT * from salle where type ='"+s+"'";
         String requete2 = "SELECT * from salle ";
            List<AnchorPane> buttonlist = new ArrayList<>();
        try {
            //our Collection to hold newly created Buttons
            ste = connection.createStatement();
            if(s==null)
            res=ste.executeQuery(requete2);
            else
             res=ste.executeQuery(requete);    
            while (res.next()) { 
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ViewController/Container.fxml"));
                AnchorPane an = fxmlLoader.load();
                ContainerController Controller =  fxmlLoader.getController();
                Salle sa =new Salle(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(6),res.getString(7));
                Controller.setAttributes(sa);
                buttonlist.add(an);
                
            }   } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return buttonlist ;

    
    }

    @Override
    public void Demander(Salle t, Utilisateur u,LocalDate l,String besoin) {
      
        try {
            String requete ="insert into Demande(iduser,idsalle,datedemande,besoin) value('"+u.getId()+"','"+t.getId()+"','"+l+"','"+besoin+"')";
            ste=connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
}

    
    
    