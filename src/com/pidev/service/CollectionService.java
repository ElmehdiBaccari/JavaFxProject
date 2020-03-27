/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;


import com.pidev.entity.Collection;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Duroy Mehdi
 */
public class CollectionService {
    
    Connection cnx = ConnexionBD.getInstance().getCnx();
       private PreparedStatement pst ;
       private Statement ste;
    private ResultSet res ;
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public void ajouterCollection(Collection c)
    {
        String query = "INSERT INTO collection ( nom_collection,date_collection,image, description) VALUES (?,?,?,?)";
         try {
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, c.getNom_collection());
            statement.setString(2, dateFormat.format(c.getDate_collection()));
             statement.setString(3, c.getImage());
            statement.setString(4, c.getDescription());
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MuseeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public List<Collection> getAll() {
        List<Collection> list = new ArrayList<>();
        String requete = " select * from collection" ;
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new Collection(res.getInt(1),res.getString(2),res.getDate(3),res.getString(4),res.getString(5)));
            }

            }
        catch (SQLException ex) {
            Logger.getLogger(CollectionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
     
     /*
     
      public ObservableList<Collection> getCollections() {
       ObservableList<Collection> list = FXCollections.observableArrayList();
        String requete = " select * from collection" ;
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
               list.add(new Collection(res.getInt(1),res.getString(2),res.getDate(3),res.getString(4),res.getString(5)));
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(Collection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
     
*/
     
     

    
    
    
    
    
    
public void delete(int id ) {
        try {
            String requete = " delete from collection where num_collection='"+id+"'" ;
            pst = cnx.prepareStatement(requete);
              ste=cnx.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(MuseeService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
  public void update(Collection c,int id ) throws SQLException{
      System.out.println(c.getImage());
      System.out.println(c.getDescription()+"khariya kbira");
      if (c.getImage()!=null)
      {
        String req="UPDATE collection SET nom_collection=?,image=?,description=? WHERE num_collection="+id;
       
        pst=cnx.prepareStatement(req);
          pst.setString(1,c.getNom_collection());
        
         pst.setString(2,c.getImage());
         pst.setString(3,c.getDescription());
        pst.executeUpdate();
      }
      else
      {
      String req="UPDATE collection SET nom_collection=?,description=?WHERE num_collection="+id;
       
        pst=cnx.prepareStatement(req);
          pst.setString(1,c.getNom_collection());
        pst.setString(2,c.getDescription());
        pst.executeUpdate();
      
      }
        System.out.println(pst.execute());
         System.out.println("Modification  avec succes");
    }
    
}
