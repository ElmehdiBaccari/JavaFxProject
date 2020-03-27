/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import com.pidev.entity.Collection;
import com.pidev.service.CollectionService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class AjouterCollectionController implements Initializable {

    CollectionService cs = new CollectionService();
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date(System.currentTimeMillis());  
      File selectedFile ;
      
    @FXML
    private TextField txtnomcollection;
    @FXML
    private DatePicker txtdatecollection;
    @FXML
    private TextArea txtdescription;
    @FXML
    private Button ajoutercollection;
    @FXML
    private Button parcourir;
    
    
      private FileChooser fileChooser = new FileChooser();
    final Stage stage = new Stage();
    private Image images;
     @FXML
    private ImageView txtimage;
     
    Image img ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ajoutercollection.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent e) {
                      
                                   if ( !controleNecessaire()   ) {
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("erreur");
                alert.setHeaderText("le nomcollection , Imagecollection , datecollection  sont obligatoires ");
                alert.showAndWait();
        }
                                   else {
                      
                            java.sql.Date d =new java.sql.Date(txtdatecollection.getValue().getYear()-1900,
                        txtdatecollection.getValue().getMonthValue()-1,
                        txtdatecollection.getValue().getDayOfMonth()
                                    
                           );
                                                        
                          Collection c=new Collection( txtnomcollection.getText(),d,selectedFile.getName(),txtdescription.getText());
                          File des = new File("D:/wamp/www/image/"+selectedFile.getName());
                          System.out.println(selectedFile.getName());
                          System.out.println("********");
                          
                          if(selectedFile!=null)
                        {       try {
                            Files.copy(selectedFile.toPath(),des.toPath());
                                } catch (IOException ex) {
                                    Logger.getLogger(AjouterCollectionController.class.getName()).log(Level.SEVERE, null, ex);
                                }
}
                          
                         cs.ajouterCollection(c);
                        
                        }
                  }
              });

    }
    
    
 

    @FXML
    private void ajouterImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll();
        selectedFile =fc.showOpenDialog(null);
         img = new Image("file:"+selectedFile.getPath());
        txtimage.setImage(img);
        if(selectedFile!=null)
        {
        /*photoField.setText(selectedFile.getName());*/}
        
        
        
    }
    
    
        private boolean controleNecessaire() {
        if ("".equals(txtnomcollection.getText())
                ||"".equals(txtdatecollection.getValue())
                ||"".equals(txtdescription.getText()))
            
            return false;
        return true;    
    }
    
}
