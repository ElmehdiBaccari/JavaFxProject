/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Damdoub
 */
public class WebcamSnap {
    
    
    
   public  WebcamSnap(){}
   
   public void testCapture()
   {
       Webcam cam =Webcam.getDefault();
      
        cam.setViewSize(new Dimension(640, 480));
        
       cam.open();
       try {
           ImageIO.write(cam.getImage(), "PNG", new File("D:/wamp/www/image/firstCapture.png"));
       } catch (IOException ex) {
           Logger.getLogger(WebcamSnap.class.getName()).log(Level.SEVERE, null, ex);
       }
        cam.close();
   }
    
    
}
