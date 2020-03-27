/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import com.pidev.entity.Produit;
import com.pidev.service.ProduitService;
import connexionbd.loadwindow;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nomproduit;
    @FXML
    private TextField typeproduit;
    @FXML
    private TextField prixproduit;
    @FXML
    private ImageView imageproduit;
    @FXML
    private TextField quantiteproduit;

     Produit ab;
    @FXML
    private Button ajouter;
    
    File selectedFile =null ;
      public void setProduit(Produit p){
    ab=p;
    this.nomproduit.setText(ab.getNom_produit());
      this.prixproduit.setText(ab.getPrix_produit()+"");
     
      this.quantiteproduit.setText(ab.getQuantite()+"");
      this.imageproduit.setImage(new Image("file:"+ab.getImage_produit()));
    
      
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
       
       
          ProduitService sa=new ProduitService();
          
        Produit p=new Produit();
        
           p.setNom_produit(nomproduit.getText());
     
              p.setPrix_produit(Double.parseDouble(prixproduit.getText()));
          
                 p.setQuantite(Integer.parseInt(quantiteproduit.getText()));
                
     
      p.setImage_produit(selectedFile.getName());
      File image = new File("D:/wamp/www/image/"+selectedFile.getName());
        try {
            Files.copy(selectedFile.toPath(), image.toPath());
        } catch (IOException ex) {
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
        System.out.println("***********");              
        System.out.println(p);
        System.out.println(ab.getId_produit());
              System.out.println("***********");              
 
            sa.update(p,ab.getId_produit());
            System.out.println("voila");
        
        
    }

    @FXML
    private void ajouterImage(ActionEvent event) {
        
        
        
         
        FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll();
        selectedFile =fc.showOpenDialog(null);
         Image  img = new Image("file:"+selectedFile.getPath());
        imageproduit.setImage(img);
        if(selectedFile!=null)
        {
        /*photoField.setText(selectedFile.getName());*/}
        
        
        
    
    }
    
}
