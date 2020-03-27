/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import ViewController.UserSession;
import com.pidev.entity.Musee;
import com.pidev.entity.Salle;
import com.pidev.entity.Utilisateur;
import com.pidev.service.MuseeService;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
//import java.util.Date;
import java.util.Date;
import java.util.function.Predicate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class AjouterMuseeController implements Initializable {
         MuseeService ms = new MuseeService();
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date(System.currentTimeMillis());  
      
 
    @FXML
    private Button ajoutervisite;
    private TextField txtnom;
    @FXML
    private DatePicker datearrive;
    @FXML
    private TextField txtpersonnes;
    @FXML
    private Pane gmap_pane;
    GoogleMap gmap;
    Utilisateur user ;
    @FXML
    private Label Price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user =UserSession.getInstace().getUser();
        Price.setText("0");
         //MuseeService ms = new MuseeService();
         //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          // Date dd = java.sql.Date.valueOf(dateouverture.getValue());
        //Date df = java.sql.Date.valueOf(datefermeture.getValue());
           gmap = new GoogleMap();
        
        gmap_pane.getChildren().add(gmap.getwebview());
  
        ajoutervisite.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent e) {
                     
         
          
       
              
                            java.sql.Date d =new java.sql.Date(datearrive.getValue().getYear()-1900,
                        datearrive.getValue().getMonthValue()-1,
                        datearrive.getValue().getDayOfMonth()
                           );
                                                        
                          Musee m=new Musee(user.getNom(),d, user.getPrenom(),user.getAdressemail(),user.getNumero(),Integer.parseInt(txtpersonnes.getText()),(float)(Integer.parseInt(txtpersonnes.getText())*15));
                         ms.ajouterMusee(m);
                           
                          System.out.println(datearrive.getValue());
                   
                      
           }
                  
              });
        // TODO
    }
 
    

    @FXML
    private void map_clicked(MouseEvent event) {
         gmap.activateListener();
        gmap.setdepMarkerPosition(gmap.currentlat, gmap.currentlng);
        System.out.println(gmap.currentlat);
    }

    @FXML
    private void calcul(KeyEvent event) {
        
        
        
         
        if( txtpersonnes.getText().isEmpty())
        {Price.setText("0");}
        else 
        {Price.setText(Float.parseFloat(txtpersonnes.getText())*15+"");}

    }


  
    
    
    
    
    
    
      
    
}
