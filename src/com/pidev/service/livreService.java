/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;



import Bibliotheque.LivreContainerController;
import ViewController.ContainerController;
import com.pidev.entity.Salle;
import com.pidev.entity.livre;
import com.pidev.entity.livre.categorie_enum;
import connexionbd.ConnexionBD;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author thaer
 */
public class livreService implements IServicelivre<livre> {

 
    
    Connection cnx = ConnexionBD.getInstance().getCnx();
   private  static livreService instance;
    private PreparedStatement pst ;
    private Statement ste;
    private ResultSet res ;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date_edition = new java.util.Date();
    
     public static livreService getInstance(){
        if(instance==null) 
            instance=new livreService();
        return instance;
    }
    
    
    public void  ajouterlivre(livre l)
    {
         
 
        String query = "INSERT INTO livre ( titre_livre,date_edition, prix_livre,qte_livre,categorie_livre,image) VALUE ('"+l.getTitre_livre()+"','"+l.getDate_edition()+"','"+l.getPrix_livre()+"','"+l.getQte_livre()+"','"+l.getCategorie_livre().toString()+"','"+l.getImg()+"')";
        String query2 = "INSERT INTO livre ( titre_livre,date_edition, prix_livre,qte_livre,categorie_livre,image) VALUE ('"+l.getTitre_livre()+"','"+l.getDate_edition()+"','"+l.getPrix_livre()+"','"+l.getQte_livre()+"','"+l.getCategorie_livre().toString()+"','D:/wamp/www/image/defaultlivre.jpg')";
         try {
                 
             ste=cnx.createStatement();
             if(l.getImg()==null)
             ste.executeUpdate(query2);
             else
                 ste.executeUpdate(query);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   
    
    
    public void delete(livre l) {
        String query = " delete from livre where id_livre='"+l.getId_livre()+"'" ;
        try {
              ste=cnx.createStatement();
           ste.executeUpdate(query);
        } catch (SQLException ex) {
            //Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
            
             Alert alert= new Alert(Alert.AlertType.WARNING);
             alert.setTitle("WAZUUUUUUUUP");
             alert.setHeaderText(null);
             alert.setContentText("Ce livre est deja emprunter ");
             alert.showAndWait();
        }
        
    }
    
    
    public void update(livre l) {
    
        try {
            String requete = " update livre set titre_livre='"+l.getTitre_livre()+"',date_edition='"+l.getDate_edition()+"',prix_livre='"+l.getPrix_livre()+"',qte_livre='"+l.getQte_livre()+"',categorie_livre='"+l.getCategorie_livre()+"',image='"+l.getImg()+"'   where id_livre='"+l.getId_livre()+"'" ;
            ste=cnx.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public List<livre> getAll() {
        
        List<livre> list = new ArrayList<>();
        ObservableList<livre> obs=FXCollections.observableArrayList(list);
        
        String requete = " select * from livre" ;
        try {
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                
                list.add(new livre(res.getInt(1),res.getString("titre_livre"),res.getDate(3).toLocalDate(),res.getDouble(4),res.getInt(5),livre.categorie_enum.valueOf(res.getString("categorie_livre")),res.getString(7)));
                
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list ;
    }
    
    
    

    
    
    
    
    
    
     public livre getById(livre l) {
        
        
         String requete = " select* from livre where titre_livre='"+l.getTitre_livre()+"'  and  categorie_livre='"+l.getCategorie_livre()+"' " ;
         
       try {
           
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
          {l = new livre(res.getInt(1),res.getString("titre_livre"),res.getDate(3).toLocalDate(),res.getDouble(4),res.getInt(5),categorie_enum.valueOf(res.getString("categorie_livre")));
         
           return l;
          }
            
        } catch (SQLException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null ;
        
    }

   

    @Override
    public void emprunter_livre(livre t) {
        try {
            String requete = " update livre set qte_livre='"+t.getQte_livre()+"' where id_livre='"+t.getId_livre()+"'" ;
            ste=cnx.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            ste = cnx.createStatement();
            result = ste.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }

    
   
       public ObservableList<PieChart.Data> getBookGraphStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {
            String qu1 = "SELECT COUNT(*) FROM livre";
            String qu2 = "SELECT COUNT(*) FROM emprunt_livre";
            ResultSet rs = execQuery(qu1);
            if (rs.next()) {
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Tout les livres (" + count + ")", count));
            }
            rs = execQuery(qu2);
            if (rs.next()) {
                int count = rs.getInt(1);
                data.add(new PieChart.Data("Livres Emprunté (" + count + ")", count));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<AnchorPane> getLivreContainer(String s) {
       
        
            String requete = "SELECT * from livre where categorie_livre ='"+s+"'";
         String requete2 = "SELECT * from livre ";
            List<AnchorPane> buttonlist = new ArrayList<>();
        try {
            //our Collection to hold newly created Buttons
            ste = cnx.createStatement();
            if(s==null)
            res=ste.executeQuery(requete2);
            else
             res=ste.executeQuery(requete);    
            while (res.next()) { 
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Bibliotheque/LivreContainer.fxml"));
                AnchorPane an = fxmlLoader.load();
                LivreContainerController Controller =  fxmlLoader.getController();
                livre sa =new livre(res.getInt(1),res.getString(2),res.getDate(3).toLocalDate(),res.getDouble(4),res.getInt(5),categorie_enum.valueOf(res.getString(6)),res.getString(7));
                Controller.setAtributes(sa);
                buttonlist.add(an);
                
            }   } catch (SQLException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(livreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return buttonlist ;
        
        
        
        
    }
       
       

    
    
       
       
   
       
       
       
       
       
       
    
}
