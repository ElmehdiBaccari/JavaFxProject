/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.pidev.entity.Utilisateur;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class CaptureWindowController implements Initializable {

    @FXML
    private ImageView imgHolder;
    
    public boolean run =true;
    
    File TakenImage ;
    
    Image image ;
     Webcam cam ;
    @FXML
    private JFXButton valid;
    @FXML
    private AnchorPane mainAnchor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        valid.setVisible(false);
         cam =Webcam.getDefault();
        cam.setViewSize(new Dimension(640, 480));
        cam.open();
        new VideoFeedTaker().start();
        //cam.close();
    
        
    }    

    @FXML
    private void snap(ActionEvent event) {
        
        run=false ;
        try {
            ImageIO.write(cam.getImage(),"PNG",new File("D:/wamp/www/image/Capture.png"));
        } catch (IOException ex) {
            Logger.getLogger(CaptureWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        valid.setVisible(true);
        cam.close();
         
       
        
       
        
    }

    @FXML
    private void repeat(ActionEvent event) {
        cam.open();
        valid.setVisible(false);
        run=true;
         new VideoFeedTaker().start();
        
    }

    @FXML
    private void Validate(ActionEvent event) {
        Utilisateur user = new Utilisateur();
        TakenImage=new File("D:/wamp/www/image/Capture.png");
        user.setPhoto(TakenImage.getPath());
        UserSession.getInstace(user);
        Stage stage = (Stage) mainAnchor.getScene().getWindow();
         stage.close();
        
        
        
    }
    
    
    
    class VideoFeedTaker extends Thread{
        
        @Override
        public void run(){       
     while(run)
    {
         try {
             BufferedImage img = cam.getImage();
             
             
             image = SwingFXUtils.toFXImage(img, null);
             imgHolder.setImage(image);
             Thread.sleep(20);
         } catch (InterruptedException ex) {
             Logger.getLogger(CaptureWindowController.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
        }
    }
   
}
