/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import Entities.Acteur;

import Entities.commentaireFilm;

import cinema.AfficherFilmController;
import com.pidev.entity.Film;
import connexionbd.ConnexionBD;
import connexionbd.GenreEnum;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Mimi
 */
public class FilmService implements Iservice<Film>
{
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
   DateFormat dateF= new SimpleDateFormat("yyyy-MM-dd HH-mm");
    
    public FilmService() {
    
    connection=ConnexionBD.getInstance().getCnx();
    }
    
    @Override
    public void insert(Film f) {
 String requete ="insert into film (nom,genre,prix,description,image,date_debut,date_fin) value('"+f.getNom()+"','"+f.getGenre()+"','"+f.getPrix()+"','"+f.getDescription()+"','"+f.getImage()+"','"+dateF.format(f.getDate_debut())+"','"+dateF.format(f.getDate_fin())+"')";
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
            String requete = " delete from film where id='"+id+"'" ;
            pst = connection.prepareStatement(requete);
              ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    
    
      
    public void deletecommentaire(int id) {
        try {
            String requete = " delete from commentaire where id='"+id+"'" ;
            pst = connection.prepareStatement(requete);
              ste=connection.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    
    
    
    @Override
    public void update(Film f){
              try {
            String requete = " update film set nom=? , genre=? , prix=? ,description=? ,image=?,date_debut=?,date_fin=? where id='"+f.getId()+"'"  ;
            pst = connection.prepareStatement(requete);
            pst.setString(1,f.getNom());
            pst.setString(2,f.getGenre().toString());
            pst.setDouble(3,f.getPrix());
            pst.setString(4,f.getDescription());
            pst.setString(5,f.getImage());
                        pst.setString(6,dateF.format(f.getDate_debut()));
                                                pst.setString(7,dateF.format(f.getDate_fin()));


            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @Override
    public List<Film> getAll() {
        List<Film> list = new ArrayList<>();
        String requete = " select * from film" ;
        try {
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while(res.next())
            {
                list.add(new Film(res.getInt(1),res.getString(2),GenreEnum.valueOf(res.getString(3)) ,res.getDouble(4),res.getString(5),res.getString(6),res.getDate(7),res.getDate(8)));
                
            }
            }
        catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

    @Override
    public Film getById(Film f) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    
    
       public List<AnchorPane> getFilmButton() throws IOException {
         String requete = "SELECT * from film";
            List<AnchorPane> buttonlist = new ArrayList<>();
        try {        
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            while (res.next()) { //iterate over every row returned
                FXMLLoader fxmlLoader = new FXMLLoader();
              
                fxmlLoader.setLocation(getClass().getResource("/cinema/AfficherFilm.fxml"));
                AnchorPane an = fxmlLoader.load();
                AfficherFilmController Controller =  fxmlLoader.getController();
                Controller.setAttributes(res.getInt(1),res.getString("image"),res.getString("nom"),res.getString("genre"));
              //  System.out.println(an);
                buttonlist.add(an);
                
            }   } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return buttonlist ;

    
    }
             public Film getFilmById(int id) {
          Film f = null;
         String requete = " select* from film  where id='"+id+"'" ;
        try {
           
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {f=new Film(res.getInt(1),res.getString(2),GenreEnum.valueOf(res.getString(3)) ,res.getDouble(4),res.getString(5),res.getString(6),res.getDate(7),res.getDate(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ActeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f ;
        
    }
             
             
        public double searchRating(int idUser , int idFilm) 
            {double a=-1;
        try {
            System.out.println(idUser+" "+idFilm);
            String requete = " select* from rating  where id_user='"+idUser+"' and id_film='"+idFilm+"' " ;
            ste = connection.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
             return res.getInt(4);
                
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
             }
    
        
        
        public void insert(int iduser ,int idfilm,int note)
        {
        try {
           
            String requete="insert into rating (id_user,id_film,rating) value("+iduser+","+idfilm+","+note+")";
            ste = connection.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        
        public float calcul(int idfilm)
        {
            float a =0 ;
        try {
            String requete="select AVG(rating) from rating WHERE id_film="+idfilm+" ";
            ste = connection.createStatement();
           res=ste.executeQuery(requete); 
           if(res.next())
           a= res.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        }
        
        
        
        
        public Map<String,Integer> getStat() {
        Map<String,Integer> map = new HashMap<String, Integer>();
    try {

            String query = "SELECT AVG(rating) as nbr,film.nom as nom from rating JOIN film on rating.id_film=film.id group by id_film";
            Statement st= connection.createStatement();
           ResultSet rest = st.executeQuery(query);
            System.out.println("Affichage Done");

            while (rest.next()) {
                map.put(rest.getString("nom"),rest.getInt("nbr"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return map ;
    }
       
    public List<commentaireFilm> AfficherCommentaireP(Film P)
    {   List ALLproducts = new ArrayList();
        try {  
           String query="SELECT * FROM `commentaire` WHERE id_film="+P.getId();
           Statement st= connection.createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next())
        {   
            commentaireFilm pr = new commentaireFilm();
            
            pr.setId(rest.getInt("id"));
            pr.setFilm_id(rest.getInt("id_film"));
            pr.setUser_id(rest.getInt("id_user"));
            pr.setContent(rest.getString("content"));
            pr.setCreatedAt(rest.getDate("createdAt"));
              ALLproducts.add(pr);
        }
           
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    return ALLproducts;       
    
    }
        public String AfficherActeurParFilm(int id)
    {   List <Acteur>ALLproducts = new ArrayList();
        try {  
           String query=    "SELECT * from acteur JOIN realisation on acteur.id=realisation.idacteur JOIN film on film.id="+id+" where realisation.idfilm="+id;
           Statement st= connection.createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next())
        {   
            Acteur pr = new Acteur();
            
            pr.setId(rest.getInt("id"));
     
            pr.setNom(rest.getString("nom"));
            pr.setPrenom(rest.getString("prenom"));
              ALLproducts.add(pr);
        }
           
        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
String Acteursstr="";
           
       for(Acteur pr: ALLproducts)
       {
           Acteursstr=Acteursstr+pr.getNom()+" "+pr.getPrenom()+",";
       
       }
     String Acteursstr1=  Acteursstr.substring(0, Acteursstr.length()-1);
    return Acteursstr1;       
    
    }
    
    
  
    
            public void AjouterCommentaire(commentaireFilm c)
    {
       java.util.Date utiledate= new java.util.Date();
            java.sql.Date datnow= new java.sql.Date(utiledate.getTime());
   
        String query2="INSERT INTO `commentaire`(`id_film`, `id_user`, `content`, `createdAt`) VALUES (?,?,?,?)";
        PreparedStatement ps2;
        try {
            ps2 = connection.prepareStatement(query2);
           
            ps2.setInt(1,c.getFilm_id());
            ps2.setInt(2,c.getUser_id());
            ps2.setString(3,c.getContent());
            ps2.setDate(4,datnow);
           
        ps2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FilmService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


