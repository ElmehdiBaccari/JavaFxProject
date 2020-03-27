/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import Entities.Acteur;
import connexionbd.ConnexionBD;

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
public class ActeurService implements Iservice<Acteur>{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    DateFormat dateF= new SimpleDateFormat("yyyy-MM-dd HH-mm");
    
    public ActeurService() {
    
    connection=ConnexionBD.getInstance().getCnx();
    }
    @Override
      public void insert(Acteur a) {
        String requete ="insert into acteur (nom,prenom,date_naissance,nationalite) value('"+a.getNom()+"','"+a.getPrenom()+"','"+dateF.format(a.getDate_naissance())+"','"+a.getNationalite()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @Override
    public void delete(int id) {
        try {
            String requete = " delete from acteur where id='"+id+"'" ;
            pst = connection.prepareStatement(requete);
              ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void update(Acteur a){
              try {
            String requete = " update acteur set nom=? , prenom=? , date_naissance=? ,nationalite=?  where id='"+a.getId()+"'"  ;
            pst = connection.prepareStatement(requete);
            pst.setString(1,a.getNom());
            pst.setString(2,a.getPrenom());
            pst.setString(3,dateF.format(a.getDate_naissance()));
            pst.setString(4,a.getNationalite());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    @Override
    public List<Acteur> getAll() {
        List<Acteur> list = new ArrayList<>();
        String requete = " select * from acteur" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new Acteur(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getString(5)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
      public Acteur getById(int id) {
          Acteur a = null;
         String requete = " select* from acteur  where id='"+id+"'" ;
        try {
           
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Acteur(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }

   

    @Override
    public Acteur getById(Acteur f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}