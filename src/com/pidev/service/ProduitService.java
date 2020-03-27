/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;


import com.pidev.entity.Produit;
import connexionbd.ConnexionBD;
import connexionbd.TypeEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Duroy Mehdi
 */
public class ProduitService {
    
     Connection cnx = ConnexionBD.getInstance().getCnx();
    public Statement ste;
    private  ResultSet r;
        private PreparedStatement pst;
    
    

    
     
    public void AjouterProduit(Produit p) throws SQLException {
        String req = "insert into produit (nom_produit,type_produit,prix_produit,image_produit,	quantite) value('"+p. getNom_produit()+"','"+p.getType_produit()+"','"+p.getPrix_produit()+"','"+p.getImage_produit()+"','"+p.getQuantite()+"')";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.executeUpdate();
        System.out.println("Produit ajouté");
    }

    

    public void supprimer_produit(int id ) {
        String req = "delete from produit where id_produit='"+id+"'";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
              ste=cnx.createStatement();
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void update(Produit p,int id ) throws SQLException{
        String req="UPDATE produit SET nom_produit=?,prix_produit=?,quantite=?,image_produit=? WHERE id_produit="+id;
       
        pst=cnx.prepareStatement(req);
        pst.setString(1,p.getNom_produit());
        pst.setDouble(2,p.getPrix_produit());
        pst.setInt(3,p.getQuantite());
        pst.setString(4,p.getImage_produit());
       
        pst.executeUpdate();
        System.out.println(pst.execute());
         System.out.println("Modification  avec succes");
    }
    
    
    
    
     public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        String requete = " select * from produit" ;
        try {
            ste = cnx.createStatement();
            r=ste.executeQuery(requete);
            while(r.next())
            {
                list.add(new Produit(r.getInt(1),r.getString(2),TypeEnum.valueOf(r.getString(3)),r.getDouble(4),r.getString(5),r.getInt(6)));
            }

            }
        catch (SQLException ex) {
            Logger.getLogger(CollectionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }
     
     
   public void stock(int s ,int id ) throws SQLException{
        String req="UPDATE produit set quantite=? WHERE id_produit="+id;
       
        pst=cnx.prepareStatement(req);
  
        pst.setInt(1,s);
       
        pst.executeUpdate();
        System.out.println(pst.execute());
         System.out.println("Modification  avec succes");
    }
    
    
}
