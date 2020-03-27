/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mimi
 */
public class AfficherFilmController implements Initializable {

    @FXML
    private AnchorPane ctnFilm;
    @FXML
    private ImageView imgFilm;
    @FXML
    private Label lbNomFilm;
    @FXML
    private Label lbGenreFilm;
    @FXML
    private Button btnDetails;
    
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnDetails.setOnAction(e->{
         AffichageFilmController controller= (AffichageFilmController) loader.loadWindow(getClass().getResource("AffichageFilm.fxml")," AffichageFilm", null);
         controller.setAttribute(id);
        });

    }    
    public void setAttributes(int id ,String img,String lbNom,String lbGenre)
    {
        this.imgFilm.setImage(new Image("file:"+img));
        this.lbNomFilm.setText(lbNom);
        this.lbGenreFilm.setText(lbGenre);
        this.id=id;
    }
    
}
