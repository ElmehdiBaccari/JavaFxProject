/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.pidev.entity.Utilisateur;
import com.pidev.service.Uservice;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class ProfileController implements Initializable {

    @FXML
    private Circle myCircle;
    @FXML
    private JFXTextField fieldNom;
    @FXML
    private JFXTextField fieldPrenom;
    @FXML
    private JFXTextField fieldNumero;
    @FXML
    private JFXDatePicker fieldDate;
    @FXML
    private JFXButton imgBtn;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    Utilisateur user ;
    
    File selectedFile ;
    @FXML
    private JFXButton validateBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          RequiredFieldValidator validator = new RequiredFieldValidator();
          validator.setMessage("Champ obligatoire !");
          RegexValidator reg = new RegexValidator();
          reg.setRegexPattern("^[a-zA-Z ]+$");
          RegexValidator regNum = new RegexValidator();
          regNum.setRegexPattern("^[0-9]{8}");
          reg.setMessage("Des lettres seulement !");
          regNum.setMessage("Ce Champ Contient 8 chiffres !");
              fieldNom.getValidators().add(validator);
          fieldNom.getValidators().add(reg);
       
          fieldPrenom.getValidators().add(validator);
           fieldPrenom.getValidators().add(reg);
           
           fieldNumero.getValidators().add(regNum);
          //fieldDate.getValidators().add(regDate);
         // fieldNumero.getValidators().add(validator);
         // fieldNom.getValidators().add(reg);
         // fieldPrenom.getValidators().add(reg);
          //fieldDate.getValidators().add(reg);
        //  fieldNumero.getValidators().add(reg);
          
          
          
          
          
        user = UserSession.getInstace().getUser();
 
        fieldPrenom.setText(user.getPrenom());
        fieldNom.setText(user.getNom());
        fieldNumero.setText(user.getNumero()+"");
        fieldDate.setValue(user.getDatedenaissance());
        myCircle.setStroke(Color.SEAGREEN);
        Image im = new Image("file:"+user.getPhoto());
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        
    }    
    
   
    
   // TextField yourTextField = new TextField();

    @FXML
    private void Modifier(ActionEvent event) {
      
        
      
        if(fieldNom.validate()&& fieldNumero.validate()&&fieldPrenom.validate())
        {
       try {
            user.setNom(fieldNom.getText());
            user.setPrenom(fieldPrenom.getText());
            user.setDatedenaissance(fieldDate.getValue());
            user.setNumero(Integer.parseInt(fieldNumero.getText()));
            if(selectedFile!=null){
            if(!user.getPhoto().equals("D:/wamp/www/image/"+user.getAdressemail()+selectedFile.getName()))
            {File image= new File("D:/wamp/www/image/"+user.getAdressemail()+selectedFile.getName());
            Files.copy(selectedFile.toPath(), image.toPath());}
            user.setPhoto("D:/wamp/www/image/"+user.getAdressemail()+selectedFile.getName());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                 alert.setTitle("Inscription");
                 alert.setHeaderText("Profil Modifié !");
                 alert.setContentText("Vous allez etre rederigé vers le menu principal !!");
                 alert.showAndWait();
        UserSession.getInstace(user);
        Uservice serv =new Uservice();
        serv.update(user);
        }
}
                
        
        
        
    

    @FXML
    private void changeImg(ActionEvent event) {
        
        
        
         FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files","*.jpg"),new FileChooser.ExtensionFilter("PNG files","*.png"),new FileChooser.ExtensionFilter("JPEG files","*.jpeg"));
        selectedFile =fc.showOpenDialog(null);
        if(selectedFile!=null)
        {
             Image im = new Image("file:"+selectedFile.getPath());
        myCircle.setFill(new ImagePattern(im));}
    }


    
}
