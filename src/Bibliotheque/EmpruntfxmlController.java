/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bibliotheque;


import Utils.SendMail;
import com.pidev.entity.Utilisateur;
import com.pidev.entity.emprunt;
import com.pidev.entity.livre;
import com.pidev.service.empruntService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author thaer
 */
public class EmpruntfxmlController implements Initializable {

    @FXML
    private TableColumn<Utilisateur, String> id_nom;
    @FXML
    private TableColumn<Utilisateur, String> id_prenom;
    @FXML
    private TableColumn<livre,String> id_titre_livre;
    @FXML
    private TableColumn<emprunt, LocalDate> id_d_emprunt;
    @FXML
    private TableColumn<emprunt, LocalDate> id_d_retour;
    @FXML
    private TableView<emprunt> id_table;
    
     private ArrayList<emprunt> list = new ArrayList<emprunt>() ;
     ObservableList<livre> obs =FXCollections.observableArrayList();
     ObservableList<emprunt> obs1 =FXCollections.observableArrayList();
    @FXML
    private Button id_emprunt;
    @FXML
    private Button id_livrefx;
    @FXML
    private MenuItem delete_id;
    @FXML
    private TableColumn<Utilisateur,String> id_mail;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        emprunt ep =new emprunt();  
        
        empruntService es=new empruntService(); 
        ObservableList<emprunt> obs=FXCollections.observableArrayList(list);
        id_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        id_titre_livre.setCellValueFactory(new PropertyValueFactory<>("titre_livre"));
        
        id_d_emprunt.setCellValueFactory(new PropertyValueFactory<>("d_emprunt"));
        id_d_retour.setCellValueFactory(new PropertyValueFactory<>("d_retour"));
        id_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        id_table.setItems(obs);
        
        
        list =(ArrayList<emprunt>) es.getAllEmprunt();
        obs=FXCollections.observableArrayList(list);
        id_table.setItems(obs);
        
        
    
  
    
    
        
        
    }    

    @FXML
    private void emprunt(ActionEvent event) {
        
        
        id_emprunt.setOnAction(e->{   
        Parent root;
        
        try {
            
            root=FXMLLoader.load(getClass().getResource("listes_livresfxml.fxml"));
            id_emprunt.getScene().setRoot(root);
                    
              
                    
                    } catch (IOException ex) {
            Logger.getLogger(Listes_livresfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }   });
        
        
        
    }

    @FXML
    private void ajouter_livre(ActionEvent event) {
        
        
         
        id_livrefx.setOnAction(e->{   
        Parent root;
        
        try {
            
            root=FXMLLoader.load(getClass().getResource("ajouter_livre_fxml.fxml"));
            id_livrefx.getScene().setRoot(root);
                    
              
                    
                    } catch (IOException ex) {
            Logger.getLogger(Ajouter_livre_fxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }   });
        
    }

    @FXML
    private void delete_emprunt(ActionEvent event) {
        
         emprunt ep  = id_table.getSelectionModel().getSelectedItem();
        empruntService es = new empruntService();
        es.delete(ep);
         
        list =(ArrayList<emprunt>) es.getAllEmprunt();
        obs1=FXCollections.observableArrayList(list);
        id_table.setItems(obs1);
        
        
       
    }

    @FXML
    private void send_mail(ActionEvent event) {
        
        
        
        
          SendMail.Send(id_table.getSelectionModel().getSelectedItem().getMail());
          Alert alert= new Alert(Alert.AlertType.WARNING);
             alert.setTitle("WAZUUUUUUUUP");
             alert.setHeaderText(null);
             alert.setContentText("Mail has been sent successfully ");
             alert.showAndWait();
        
    }
    
}
