/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bibliotheque;


import ViewController.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.pidev.entity.Utilisateur;
import com.pidev.entity.emprunt;
import com.pidev.entity.livre;
import com.pidev.service.empruntService;
import com.pidev.service.livreService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author thaer
 */
public class Ajouter_empruntController implements Initializable {

    @FXML
    private JFXDatePicker id_d_emprunt;
    @FXML
    private JFXDatePicker id_r_emprunt;
    @FXML
    private JFXButton id_emprunt;
    
    private ArrayList<livre> list = new ArrayList<livre>() ;
     ObservableList<livre> obs =FXCollections.observableArrayList();
   
    
    livre lv =new livre();  
    Utilisateur user ;
    @FXML
    private AnchorPane rootPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       user = UserSession.getInstace().getUser();
       
       
    }    

    @FXML
    private void ajouter_emprunt(ActionEvent event) {
        
        String test_date=valid();
        if(test_date.isEmpty())
        {
          
        emprunt e =new emprunt();
        e.setD_emprunt(id_d_emprunt.getValue());
        e.setD_retour(id_r_emprunt.getValue());
        e.setId_user(user.getId());
        e.setId_livre(lv.getId_livre());
        lv.setQte_livre(lv.getQte_livre()-1);
   
        livreService ls= new livreService();
        ls.emprunter_livre(lv);
        empruntService es =new empruntService();
        es.ajouterEmprunt(e);
        }
        else{
            Alert alert_errors = new Alert(Alert.AlertType.ERROR);
            alert_errors.setContentText(test_date);
            alert_errors.show();
            
        
        }
        
        
        
    }
    
    
    private String valid()
    {
       
        
        String error_msg = "";
        if (id_d_emprunt.getValue() != null) {
            if (id_d_emprunt.getValue().isBefore(LocalDate.now())) {
                error_msg += "La Date saisie ne peut pas étre inférieur a la date  d'aujourd'hui \n";
                System.out.println("helllooooo");
            }
        } else {
            error_msg += "Date d'emprunt est obligatoire \n";
     

        
    }
        
        
        return error_msg;
       
    }     
    public void setLivre(livre l)
    {
        this.lv=l;
       
    }

    @FXML
    private void cancel(ActionEvent event) {

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    public void setAttributes(livre l)
    {
    
    this.lv=l;
    
    }
    
}
