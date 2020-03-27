/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bibliotheque;

import com.pidev.entity.livre;
import connexionbd.loadwindow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class LivreContainerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    livre l;
    @FXML
    private ImageView img;
    @FXML
    private Label titrelivre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setAtributes(livre liv)
    {
    this.l=liv;
    if(l.getImg()!=null)
    this.img.setImage(new Image("file:"+l.getImg()));
    this.titrelivre.setText(l.getTitre_livre());
    }

    @FXML
    private void DemanderEmrinte(MouseEvent event) {
        
        
        Ajouter_empruntController controller =(Ajouter_empruntController)loadwindow.loadWindow(getClass().getResource("ajouter_emprunt.fxml"), "Emprunter Livre", null);
        controller.setAttributes(l);
    }
    
}
