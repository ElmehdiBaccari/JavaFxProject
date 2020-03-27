/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.pidev.entity.Utilisateur;
import com.pidev.service.Uservice;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class loginController implements Initializable {

    @FXML
    private TextField loginField;
    @FXML
    private TextField passField;
    @FXML
    private Label errorLogin;
    @FXML
    private Label errorPass;
    @FXML
    private AnchorPane AnchorLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
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

    @FXML
    private void handleLogin(MouseEvent event) {
        Utilisateur user = new Utilisateur();
        Uservice serv =new Uservice();
        user.setAdressemail(loginField.getText());
        user =serv.searchByMail(user);
        if(loginField.getText().isEmpty())
        {
         //empty login field
            
            color(loginField, "red");
            errorLogin.setText("Empty login field !");
        }
        else if(passField.getText().isEmpty())
        {
        // empty pass field
             color(passField, "red");
            
            errorPass.setText("Empty password field !");
        }
        else if(user==null)
        {
        //  mail not found
            color(loginField, "red");
            
            errorLogin.setText("user not found!");
            
        }
        else if (!user.getMotdepasse().equals(passField.getText()))
        {
        // wrong password
            color(passField, "red");
            
            errorPass.setText("wrong password  !");
        }
        else if (user.getStatut().equals("susspendu"))
        {
        // account suspended
             System.out.println("account susspended !");
        }
        
        else if (user.getRole()==1)
                {
        
        
        try {
            
            UserSession session = UserSession.getInstace(user);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("HomePage.fxml"));
            Parent root =loader.load();
            Scene scene = new Scene(root);
            Stage app_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
         try {
             UserSession session = UserSession.getInstace(user);
           AnchorLogin.getScene().setRoot(FXMLLoader.load(getClass().getResource("AdminPage.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            
        
        }
    }

    @FXML
    private void handleMsgError(MouseEvent event) {
        
        
        color(loginField, "blue");
        color(passField, "blue");
            
            errorLogin.setText("");
             errorPass.setText("");
    }

    @FXML
    private void handleMsgErrorKey(KeyEvent event) {
        color(loginField, "blue");
        color(passField, "blue");
            
        errorLogin.setText("");
        errorPass.setText("");
        
        
        
        
    }

    @FXML
    private void GoToCreateAccount(ActionEvent event) {
        try {
           AnchorLogin.getScene().setRoot(FXMLLoader.load(getClass().getResource("CreateAccount.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
  
}
