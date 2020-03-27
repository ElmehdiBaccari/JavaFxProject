/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;


import com.pidev.entity.emprunt;
import connexionbd.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author thaer
 */
public class empruntService implements Iemprunt<emprunt> {
    
    Connection cnx = ConnexionBD.getInstance().getCnx();
    private PreparedStatement pst ;
    private Statement ste;
    private ResultSet res ;
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date_edition = new java.util.Date();
  
    
     public List<emprunt> getAllEmprunt() {
         
        List<emprunt> list = new ArrayList<>();
        String requete = " SELECT e.id_emprunt,e.id_user, e.id_livre, p.nom, p.prenom, l.titre_livre, e.d_emprunt, e.d_retour ,p.adressemail" +
" FROM emprunt_livre AS e INNER JOIN utilisateur AS p, livre AS l WHERE e.id_livre =l.id_livre AND p.id = e.id_user" ;
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new emprunt(res.getInt(1),res.getInt(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6),res.getDate(7).toLocalDate(),res.getDate(8).toLocalDate(),res.getString(9)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(empruntService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

    @Override
    public void ajouterEmprunt(emprunt e) {
      
        
        
         String query = "INSERT INTO emprunt_livre (id_livre,id_user,d_emprunt,d_retour) VALUE ('"+e.getId_livre()+"','"+e.getId_user()+"','"+e.getD_emprunt()+"','"+e.getD_retour()+"')"; 
         try {
                 
             ste=cnx.createStatement();
             ste.executeUpdate(query);
           System.out.println("successfully added");
            
        } catch (SQLException ex) {
           // Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
            
            Alert alert= new Alert(Alert.AlertType.WARNING);
             alert.setTitle("WARNING");
             alert.setHeaderText(null);
             alert.setContentText("Saisir la date svp");
             alert.showAndWait();
            
        }
    }

   
     public void delete(emprunt emp) {
        String query = " delete from emprunt_livre where id_emprunt='"+emp.getId_emprunt()+"'";
        try {
              ste=cnx.createStatement();
           ste.executeUpdate(query);
        } catch (SQLException ex) {
            //Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("Ce livre est deja emprunter");
        }
        
    }

  
   
    
     
}
