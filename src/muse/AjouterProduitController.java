/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import com.pidev.entity.Produit;
import com.pidev.service.ProduitService;
import connexionbd.TypeEnum;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class AjouterProduitController implements Initializable {

       ProduitService ps = new ProduitService();
    
     private FileChooser fileChooser = new FileChooser();
    final Stage stage = new Stage();
    private Image images;
     File selectedFile ;
    
    
    
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField prixproduit;
    @FXML
    private ComboBox<TypeEnum> typeproduit;
    @FXML
    private TextField quantiteproduit;
    @FXML
    private Button ajouterproduit;
    @FXML
    private Button parcourir;
    @FXML
    private ImageView txtimage;    
      
    
    Image img ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeproduit.getItems().add(TypeEnum. Commerciale);
         typeproduit.getItems().add(TypeEnum.Souvenir);
         typeproduit.getItems().add(TypeEnum.Copie);
         typeproduit.getItems().add(TypeEnum.Painture);
         typeproduit.setValue(TypeEnum.Commerciale);
       ProduitService fs = new ProduitService();
        ajouterproduit.setOnAction(e->{
            
                        if ( !controleNecessaire()   ) {
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("erreur");
                alert.setHeaderText("le nomproduit ,le prixproduit ,le typeproduit ,quantiteproduit  sont obligatoires ");
                alert.showAndWait();
        }
            try {
              Produit p =new Produit(nomproduit.getText(),typeproduit.getValue(),Double.valueOf(prixproduit.getText()),selectedFile.getName(),Integer.valueOf(quantiteproduit.getText()));
                  File des = new File("D:/wamp/www/image/"+selectedFile.getName());
                          System.out.println(selectedFile.getName());
                          System.out.println("********");
                          
                          if(selectedFile!=null)
                         Files.copy(selectedFile.toPath(),des.toPath()); 
                          
                         fs.AjouterProduit(p);
            } catch (SQLException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*
            Parent root;
            try {
                root=FXMLLoader.load(getClass().getResource("AfficherProduit.fxml"));
                ajouterproduit.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
           
        });   
       
      
    }  
    
    
    @FXML
    private void ajouterImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll();
        selectedFile =fc.showOpenDialog(null);
           img = new Image("file:"+selectedFile.getPath());
        txtimage.setImage(img);
        if(selectedFile!=null)
        {
        /*photoField.setText(selectedFile.getName());*/}
        
        
        
    }
    
        private boolean controleNecessaire() {
        if ("".equals(nomproduit.getText())
                ||"".equals(prixproduit.getText())
                ||"".equals(quantiteproduit.getText())
                ||"".equals(typeproduit.getValue()))
             
            return false;
        return true;    
    }
    
    
    
    
     @FXML
    private void controleSaisiePrix(KeyEvent event) {
        String text="";
        String concat = "";
        System.out.println(event.getCode());
        if("DIGIT0".equals(event.getCode().toString())||
                "DIGIT1".equals(event.getCode().toString())||
                "DIGIT2".equals(event.getCode().toString())||
                "DIGIT3".equals(event.getCode().toString())||
                "DIGIT4".equals(event.getCode().toString())||
                "DIGIT5".equals(event.getCode().toString())||
                "DIGIT6".equals(event.getCode().toString())||
                "DIGIT7".equals(event.getCode().toString())||
                "DIGIT8".equals(event.getCode().toString())||
                "DIGIT9".equals(event.getCode().toString())||
                "NUMPAD0".equals(event.getCode().toString())||
                "NUMPAD1".equals(event.getCode().toString())||
                "NUMPAD2".equals(event.getCode().toString())||
                "NUMPAD3".equals(event.getCode().toString())||
                "NUMPAD4".equals(event.getCode().toString())||
                "NUMPAD5".equals(event.getCode().toString())||
                "NUMPAD6".equals(event.getCode().toString())||
                "NUMPAD7".equals(event.getCode().toString())||
                "NUMPAD8".equals(event.getCode().toString())||
                "NUMPAD9".equals(event.getCode().toString()))
        {
            concat = concat.concat(event.getText());
        }
        else {
            prixproduit.setText(text);
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("erreur");
                alert.setHeaderText("prix incorrect");
                alert.showAndWait();
        }
        
            
        
    }
    
    
    
      
     @FXML
    private void controleSaisiequantite(KeyEvent event) {
        String text="";
        String concat = "";
        System.out.println(event.getCode());
        if("DIGIT0".equals(event.getCode().toString())||
                "DIGIT1".equals(event.getCode().toString())||
                "DIGIT2".equals(event.getCode().toString())||
                "DIGIT3".equals(event.getCode().toString())||
                "DIGIT4".equals(event.getCode().toString())||
                "DIGIT5".equals(event.getCode().toString())||
                "DIGIT6".equals(event.getCode().toString())||
                "DIGIT7".equals(event.getCode().toString())||
                "DIGIT8".equals(event.getCode().toString())||
                "DIGIT9".equals(event.getCode().toString())||
                "NUMPAD0".equals(event.getCode().toString())||
                "NUMPAD1".equals(event.getCode().toString())||
                "NUMPAD2".equals(event.getCode().toString())||
                "NUMPAD3".equals(event.getCode().toString())||
                "NUMPAD4".equals(event.getCode().toString())||
                "NUMPAD5".equals(event.getCode().toString())||
                "NUMPAD6".equals(event.getCode().toString())||
                "NUMPAD7".equals(event.getCode().toString())||
                "NUMPAD8".equals(event.getCode().toString())||
                "NUMPAD9".equals(event.getCode().toString()))
        {
            concat = concat.concat(event.getText());
        }
        else {
            quantiteproduit.setText(text);
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("erreur");
                alert.setHeaderText("prix incorrect");
                alert.showAndWait();
        }
        
            
        
    }

    
}
