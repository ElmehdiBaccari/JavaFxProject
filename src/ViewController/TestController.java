/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.jfoenix.controls.JFXMasonryPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class TestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JFXMasonryPane root = new JFXMasonryPane();
	ScrollPane pane = new ScrollPane(root);
	pane.setFitToWidth(true);

	Scene scene = new Scene(pane, 300, 300);

	for(int i = 0; i < 200; i++)
		root.getChildren().add(new Label("Test"));
        
        
        
    }    
    
}
