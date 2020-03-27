 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.pidev.entity.Utilisateur;
import com.pidev.service.Uservice;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField fNom;
    @FXML
    private TextField fPrenom;
    @FXML
    private TextField fMail;
    @FXML
    private DatePicker fDate;
    @FXML
    private PasswordField pField;
    @FXML
    private PasswordField cPField;
    @FXML
    private Label errorNom;
    @FXML
    private Label errorPrenom;
    @FXML
    private Label errorMail;
    @FXML
    private Label errorMdp;
    @FXML
    private Label errorCmdp;
    
    private File selectedFile ;
    private static boolean valid  ;
    Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private Button imageBtn;
    @FXML
    private TextField photoField;
    @FXML
    private TextField numberField;
    
    File image ;
    @FXML
    private Label numberError;
    @FXML
    private AnchorPane AnchorCreateAcc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valid =true ;
        photoField.setDisable(true);
        // TODO
    }    
    
    
     public static void color(TextField tf,String color) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if (color.equals("red")) {
            if (! styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.remove("error");                   
        }
    }
     
      public static final Pattern CHARACTHERS_ONLY = 
    Pattern.compile("^[a-zA-Z ]+$", Pattern.CASE_INSENSITIVE);
      public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
      public static final Pattern VALID_PASSWORD_REGEX = 
    Pattern.compile("^[a-zA-Z0-9]{6,12}$", Pattern.CASE_INSENSITIVE);
      
      public static final Pattern VALID_NUMBER = 
    Pattern.compile("^[0-9]{8}", Pattern.CASE_INSENSITIVE);

    public static boolean validateMail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find(); }
    public static boolean validateName(String nameStr) {
        Matcher matcher = CHARACTHERS_ONLY .matcher(nameStr);
        return matcher.find(); }
    public static boolean validatePassword(String passStr) {
        Matcher matcher = VALID_PASSWORD_REGEX .matcher(passStr);
        return matcher.find(); }
     public static boolean validateNumber(String numberStr) {
        Matcher matcher = VALID_NUMBER .matcher(numberStr);
        return matcher.find(); }
    
    

    @FXML
    private void createUser(ActionEvent event) {
        
        
        Utilisateur usertest =new Utilisateur() ;
        usertest.setAdressemail(fMail.getText());
        Uservice servtest  = new Uservice();
         if(fNom.getText().isEmpty())
        { color(fNom,"red");errorNom.setText("Ce champ est obligatoire !");valid=false;}
         else if(!validateName(fNom.getText()))
        { color(fNom,"red");errorNom.setText("Le nom doit contenir que des caracthéres !");valid=false;}  
         
         if(fPrenom.getText().isEmpty())
        { color(fPrenom,"red");errorPrenom.setText("Ce champ est obligatoire !");valid=false;}
         else if(!validateName(fPrenom.getText()))
        { color(fPrenom,"red");errorPrenom.setText("Le prenom doit contenir que des caracthéres !");valid=false;}
         
         if(fMail.getText().isEmpty())
        { color(fMail,"red");errorMail.setText("Ce champ est obligatoire !");valid=false;}
         else if(!validateMail(fMail.getText()))
        { color(fMail,"red");errorMail.setText("Adresse mail non valide !");valid=false;}
         else if(servtest.searchByMail(usertest)!=null)
         {color(fMail,"red");errorMail.setText("Adresse mail deja utilisé !");valid=false;}
        
         
          if(pField.getText().isEmpty())
        { color(pField,"red");errorMdp.setText("Ce champ est obligatoire !");valid=false;}
          else if(!validatePassword(pField.getText()))
        { color(pField,"red");errorMdp.setText("Mot de passe trop court ou trop long !");valid=false;}
          
          
            if(cPField.getText().isEmpty())
        { color(cPField,"red");errorCmdp.setText("Ce champ est obligatoire !");valid=false;}
             else if(!cPField.getText().equals(pField.getText()))
        { color(cPField,"red");errorCmdp.setText("les deux mot de passe ne sont pas identiques!");valid=false;}
            
           if(!validateNumber(numberField.getText())&& !numberField.getText().isEmpty())
             { color(numberField,"red");numberError.setText("numero incorrect !");valid=false;}
            
            
        if (valid)
        {
             try {
                 Utilisateur user =UserSession.getInstace().getUser() ;
                 selectedFile=new File(user.getPhoto());
                 if(numberField.getText().isEmpty())
                 {
                     if(selectedFile==null)
                     {
                        
                  user =new Utilisateur(fNom.getText(),fPrenom.getText(),fMail.getText(),fDate.getValue(),null,cPField.getText(),0); }
                 else
                     {
                         
                user =new Utilisateur(fNom.getText(),fPrenom.getText(),fMail.getText(),fDate.getValue(),"D:/wamp/www/image/"+fMail.getText()+selectedFile.getName(),cPField.getText(),0);}}
                 
                 
                else
                 {
                      if(selectedFile==null)
                      {
                  user =new Utilisateur(fNom.getText(),fPrenom.getText(),fMail.getText(),fDate.getValue(),null,cPField.getText(),Integer.parseInt(numberField.getText()));}
                 else
                      { 
                  user =new Utilisateur(fNom.getText(),fPrenom.getText(),fMail.getText(),fDate.getValue(),"D:/wamp/www/image/"+fMail.getText()+selectedFile.getName(),cPField.getText(),Integer.parseInt(numberField.getText())); }
                 }
                 Uservice serv =new Uservice();
                  serv.insert(user);
                 if(selectedFile!=null)
                 {
                     image= new File("D:/wamp/www/image/"+fMail.getText()+selectedFile.getName());
                     Files.copy(selectedFile.toPath(), image.toPath());}
                 alert.setTitle("Inscription");
                 alert.setHeaderText("Compte créé avec Succès !");
                 alert.setContentText("Vous pouvez connecter dès maintenant .");
                 alert.showAndWait();
                 Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                 Stage app_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(root);
                 app_stage.setScene(scene);
                 app_stage.show();
            } catch (IOException ex) {
                 Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        valid=true ;
        
        
        
    }

    @FXML
    private void handleMsgKey(KeyEvent event) {
        
        color(fNom, "blue");
        color(fPrenom, "blue");
         color(fMail, "blue");
          color(pField, "blue");
           color(cPField, "blue");
           color(numberField, "blue");
            
            numberError.setText("");
            errorNom.setText("");
             errorPrenom.setText("");
             errorMail.setText("");
             errorMdp.setText("");
             errorCmdp.setText("");
             
        
        
    }

    

    @FXML
    private void uploadImage(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(new ExtensionFilter("JPG files","*.jpg"),new ExtensionFilter("PNG files","*.png"),new ExtensionFilter("JPEG files","*.jpeg"));
        selectedFile =fc.showOpenDialog(null);
        if(selectedFile!=null)
        {
        photoField.setText(selectedFile.getName());}
        
       
      
        
        
        
        
        
    }

    @FXML
    private void GoToLogin(ActionEvent event) {
        
        try {
           AnchorCreateAcc.getScene().setRoot(FXMLLoader.load(getClass().getResource("Login.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Capture(ActionEvent event) {
        
      ToolBarAssistant.loadWindow(getClass().getResource("captureWindow.fxml"), "Prendre Une Photo", null);
        
        
    }

    
}
