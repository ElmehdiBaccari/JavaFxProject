/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.entity.Utilisateur;
import com.pidev.service.IserviceUser.IService;
import connexionbd.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Damdoub
 */
public class Uservice implements IService<Utilisateur> {

     private Connection connection;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public Uservice() {
        connection=ConnexionBD.getInstance().getCnx();
    }
    
    
    
    @Override
    public void insert(Utilisateur t) {
        String requete ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"','"+t.getDatedenaissance()+"','"+t.getPhoto()+"','"+t.getMotdepasse()+"','"+t.getNumero()+"')";                              
        String requete2 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"',null,'"+t.getPhoto()+"','"+t.getMotdepasse()+"','"+t.getNumero()+"')";       
        String requete3 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"','"+t.getDatedenaissance()+"','D:/wamp/www/image/default.png','"+t.getMotdepasse()+"','"+t.getNumero()+"')";
        String requete4 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"','"+t.getDatedenaissance()+"','"+t.getPhoto()+"','"+t.getMotdepasse()+"',null)";
        String requete5 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"',null,'D:/wamp/www/image/default.png','"+t.getMotdepasse()+"','"+t.getNumero()+"')";
        String requete6 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"',null,'"+t.getPhoto()+"','"+t.getMotdepasse()+"',null)";
        String requete7 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"','"+t.getDatedenaissance()+"','D:/wamp/www/image/default.png','"+t.getMotdepasse()+"',null)";
        String requete8 ="insert into utilisateur(nom,prenom,adressemail,datedenaissance,photo,motdepasse,numero) value('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAdressemail()+"',null,'D:/wamp/www/image/default.png','"+t.getMotdepasse()+"',null)";
        
        try {
            ste=connection.createStatement();
            if(t.getDatedenaissance()==null &&  t.getPhoto()!=null && t.getNumero()!=0)
            ste.executeUpdate(requete2);
            else if(t.getDatedenaissance()!=null &&  t.getPhoto()==null && t.getNumero()!=0)
            ste.executeUpdate(requete3);
            else if(t.getDatedenaissance()!=null &&  t.getPhoto()!=null && t.getNumero()==0)
            ste.executeUpdate(requete4);
            else if(t.getDatedenaissance()==null &&  t.getPhoto()==null && t.getNumero()!=0)
            ste.executeUpdate(requete5);
            else if(t.getDatedenaissance()==null &&  t.getPhoto()!=null && t.getNumero()==0)
            ste.executeUpdate(requete6);
            else if( t.getDatedenaissance()!=null &&  t.getPhoto()==null && t.getNumero()==0)
            ste.executeUpdate(requete7);
            else if(t.getDatedenaissance()==null &&  t.getPhoto()==null && t.getNumero()==0)
            ste.executeUpdate(requete8);
            else
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(Uservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Utilisateur t) {
          try {
            String requete = " delete from utilisateur where id='"+t.getId()+"'" ;
            ste=connection.createStatement();
           ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(Uservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Utilisateur t) {
         try {
            String requete = " update utilisateur set nom='"+t.getNom()+"' , prenom='"+t.getPrenom()+"' , adressemail='"+t.getAdressemail()+"' , photo='"+t.getPhoto()+"' , motdepasse='"+t.getMotdepasse()+"' , numero='"+t.getNumero()+"' where id='"+t.getId()+"'" ;
            ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(Uservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Utilisateur> getAll() {
          ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        String requete = " select * from utilisateur" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            { 
                if (res.getDate(5)==null)
                list.add(new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), null, res.getString(6), res.getString(7), res.getString(8),res.getInt(10)));
                else
                list.add(new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5).toLocalDate(), res.getString(6), res.getString(7), res.getString(8),res.getInt(10)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

    @Override
    public Utilisateur getById(Utilisateur t) {
        String requete = " select* from utilisateur where id='"+t.getId()+"'";
        try {
           
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {t = new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5).toLocalDate(), res.getString(6), res.getString(7), res.getString(8));}
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t ;
    }

    @Override
    public Utilisateur searchByMail(Utilisateur t) {
         String requete = " select* from utilisateur where adressemail='"+t.getAdressemail()+"'";
         
         try {
           
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {   if(res.getDate(5)==null)
                t = new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), null, res.getString(6), res.getString(7), res.getString(8), res.getInt(9),res.getInt(10));
                else
                t = new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5).toLocalDate(), res.getString(6), res.getString(7), res.getString(8), res.getInt(9),res.getInt(10));
            }
            else
                t=null;
           // System.out.println(t+"search by mail");
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t ;
    }

    @Override
    public ObservableList<Utilisateur> getUsers() {
       ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        String requete = " select * from utilisateur where role=1" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                
                if (res.getDate(5)==null)
                list.add(new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), null, res.getString(6), res.getString(7), res.getString(8),res.getInt(10)));
                else
                list.add(new Utilisateur(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5).toLocalDate(), res.getString(6), res.getString(7), res.getString(8),res.getInt(10)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }




}
    

