/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.jfoenix.controls.JFXMasonryPane;
import com.pidev.entity.Utilisateur;
import com.pidev.service.Uservice;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author Damdoub
 */
public class Launcher extends Application {
    
    @Override
    public void start(Stage stage) throws Exception { 
       Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Date d = Date.from(Instant.MIN);
        Utilisateur user = new Utilisateur();
        Uservice serv =new Uservice();
        //System.out.println(serv.getAll());
       // Utilisateur user2 =new Utilisateur("adem","damdoum","adem@gmail.com","zihdzihd","ishfifh",1);
        //serv.insert(user2);
        launch();
    }
    
}
