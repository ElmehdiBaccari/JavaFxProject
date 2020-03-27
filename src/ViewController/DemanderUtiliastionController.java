/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.pidev.entity.Salle;
import com.pidev.entity.Utilisateur;
import com.pidev.service.SalleService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class DemanderUtiliastionController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextArea besoin;
    Utilisateur user ;
    Salle s ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate minDate ;
        LocalDate maxDate ;
        minDate = LocalDate.of(1989, 4, 16);
maxDate = LocalDate.of(2000, 4, 16);
date.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
               }});
        user=UserSession.getInstace().getUser();
        // TODO
    }    

    @FXML
    private void ConfirmerDemande(ActionEvent event) {
        
        SalleService sService =new SalleService();
        sService.Demander(s, user, date.getValue(), besoin.getText());
        
        
    }

    @FXML
    private void Annuler(ActionEvent event) {
        
        
        Stage s =(Stage) nom.getScene().getWindow();
        s.close();
        
        
        
    }
    
     public void setAttributes(Salle sa )
    {
        this.s=sa ;
        //this.img=path;
        this.nom.setText(s.getNom());
        this.img.setImage(new Image("file:"+s.getPhoto()));
        
    
    }
    
    
}
