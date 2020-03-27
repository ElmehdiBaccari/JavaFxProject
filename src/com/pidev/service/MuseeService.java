/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.entity.Musee;
import connexionbd.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Duroy Mehdi
 */
public class MuseeService {

    Connection cnx = ConnexionBD.getInstance().getCnx();
    private PreparedStatement pst;
        private Statement ste;
    private ResultSet res ;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void ajouterMusee(Musee m) {
        String query = "INSERT INTO musee (nom,arrive,prenom,email,telephone,personne,fee) VALUES (?,?,?,?,?,?,?)";
        try {
          
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, m.getNom());
            statement.setString(2, dateFormat.format(m.getArrive()));
            statement.setString(3, m.getPrenom());
            statement.setString(4, m.getEmail());
            statement.setInt(5, m.getTelephone());
            statement.setInt(6, m.getPersonne());
            statement.setFloat(7, m.getFee());
            statement.executeUpdate();
         

        } catch (SQLException ex) {
            Logger.getLogger(MuseeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
 
  
    public List<Musee> getAll() {
        List<Musee> list = new ArrayList<>();
          
        String requete = " select * from musee" ;
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new Musee(res.getInt(1),res.getString(2),res.getDate(3),res.getString(4),res.getString(5),res.getInt(6),res.getInt(7),res.getFloat(8)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(MuseeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
    

    
    
    public List<Musee> getUserVisit(String s) {
        List<Musee> list = new ArrayList<>();
        String requete = " select * from musee where email='"+s+"'" ;
        
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new Musee(res.getInt(1),res.getString(2),res.getDate(3),res.getString(4),res.getString(5),res.getInt(6),res.getInt(7),res.getFloat(8)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(MuseeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
    

public void delete(int id ) {
        try {
            String requete = " delete from musee where id='"+id+"'" ;
            pst = cnx.prepareStatement(requete);
              ste=cnx.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(MuseeService.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    public void update(Musee a,int id ) throws SQLException{
        String req="UPDATE musee SET nom=?,prenom=?,email=?,telephone=?,personne=?,fee=? WHERE id="+id;
       
        pst=cnx.prepareStatement(req);
        pst.setString(1,a.getNom());
        pst.setString(2,a.getPrenom());
        pst.setString(3,a.getEmail());
        pst.setInt(4,a.getTelephone());
        pst.setInt(5,a.getPersonne());
        pst.setFloat(6,a.getFee());
        pst.executeUpdate();
        System.out.println(pst.execute());
         System.out.println("Modification  avec succes");
    }
    
    
    
    
      
    public ObservableList<Musee> getUsers() {
       ObservableList<Musee> list = FXCollections.observableArrayList();
        String requete = " select * from Musee" ;
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
               list.add(new Musee(res.getInt(1),res.getString(2),res.getDate(3),res.getString(4),res.getString(5),res.getInt(6),res.getInt(7),res.getFloat(8)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(Musee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

 
    

}
