/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Mimi
 */
public class Cinema extends Application {
    
       @Override
    public void start(Stage primaryStage) {
        try {
           // Button btn = new Button();
            Parent root=FXMLLoader.load(getClass().getResource("AfficherFilms.fxml"));
            Scene scene =new Scene(root);
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Cinema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("!!!!!!!");
        launch(args);
    }
    
}
