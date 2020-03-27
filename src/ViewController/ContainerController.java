/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.pidev.entity.Salle;
import com.pidev.service.*;
import connexionbd.loadwindow;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class ContainerController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private Label nom;
    
    Salle s ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void setAttributes(Salle sa )
    {
        this.s=sa ;
        //this.img=path;
        this.nom.setText(s.getNom());
        Image image = new Image("file:"+s.getPhoto(), img.getFitWidth(), img.getFitHeight(), false, false);
        this.img.setImage(image);
    
    
    }

    @FXML
    private void ConsulterSalle(MouseEvent event) {
        
       SalleShowController controller = (SalleShowController)loadwindow.loadWindow(getClass().getResource("SalleShow.fxml"), "Salle", null);
        controller.setAttributes(s);
        
    }
    
}
