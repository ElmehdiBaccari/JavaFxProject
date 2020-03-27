/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.pidev.entity.Salle;
import connexionbd.loadwindow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class SalleShowController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label type;
    @FXML
    private Label nbrplaces;
    @FXML
    private Label describtion;
    @FXML
    private ImageView img;

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
        this.img.setImage(new Image("file:"+s.getPhoto()));
        this.nbrplaces.setText(s.getNbrplace()+"");
        this.describtion.setText(s.getDescription());
        this.type.setText(s.getType());
    
    }

    @FXML
    private void Demander(ActionEvent event) {
        
        
        DemanderUtiliastionController controller = (DemanderUtiliastionController)loadwindow.loadWindow(getClass().getResource("DemanderUtiliastion.fxml"), "Demande", null);
        controller.setAttributes(s);
        
    }

    @FXML
    private void extrairePdf(ActionEvent event) {
    }
    
}
