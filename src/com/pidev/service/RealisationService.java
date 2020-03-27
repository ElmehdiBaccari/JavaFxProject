/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import Entities.Acteur;
import com.pidev.entity.Realisation;
import connexionbd.ConnexionBD;
import connexionbd.GenreEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mimi
 */
public class RealisationService {
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    DateFormat dateF= new SimpleDateFormat("yyyy-MM-dd HH-mm");
    
    public RealisationService() {
    
    connection=ConnexionBD.getInstance().getCnx();
    }
      public void insert(Realisation r) {
        String requete =" insert into realisation (idacteur,idfilm) value('"+r.getIdacteur()+"','"+r.getIdfilm()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(RealisationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void delete(Realisation r) {
        try {
            String requete = " delete from realisation where idRealisation='"+r.getId()+"'" ;
            pst = connection.prepareStatement(requete);
              ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
        public void update(Realisation r){
              try {
            String requete = " update realisation set idacteur=? , idfilm=? , date_realisation=?  where idacteur='"+r.getIdacteur()+"' and idacteur='"+r.getIdfilm()+"'"  ;
            pst = connection.prepareStatement(requete);
            pst.setInt(1,r.getIdacteur());
            pst.setInt(2,r.getIdfilm());
            pst.setString(3,dateF.format(r.getDate_realisation()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
        
        public List<Realisation> getAll() {
        List<Realisation> list = new ArrayList<>();
        String requete = " select r.idRealisation, f.nom,f.genre,a.nom,a.prenom,a.nationalite from film f join realisation r on f.id=r.idfilm join acteur a on r.idacteur=a.id" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new Realisation(res.getInt(1),res.getString(2), GenreEnum.valueOf(res.getString(3)), res.getString(4), res.getString(5), res.getString(6)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

        public List<String> getAllNameActeur() {
        List<String> list = new ArrayList<>();
        String requete = " SELECT nom from acteur" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(res.getString(1));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
        public List<String> getAllNameFilm() {
        List<String> list = new ArrayList<>();
        String requete = " select nom from film" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(res.getString(1));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
        public int getIdByNameActeur(String nom)
        {
        int id=0;
        String requete="select id from acteur where nom='"+nom+"'";
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                id=res.getInt(1);
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return id;    
        }
        public int getIdByNameFilm(String nom)
        {
        int id=0;
        String requete="select id from film where nom='"+nom+"'";
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                id=res.getInt(1);
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return id;    
        }
        
}
