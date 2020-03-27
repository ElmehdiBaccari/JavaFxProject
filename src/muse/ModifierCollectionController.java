/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import ViewController.AdminPageController;
import ViewController.HomePageController;
import com.pidev.entity.Collection;
import com.pidev.service.CollectionService;
import connexionbd.loadwindow;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class ModifierCollectionController implements Initializable {

    @FXML
    private TextField nomcollection;
    @FXML
    private DatePicker datecollection;
    @FXML
    private ImageView image;
    @FXML
    private TextArea description;

    
   Collection ab;
    @FXML
    private Button ajouter;
    
    File selectedFile ;
   
    public void setCollection(Collection c){
    ab=c;
    
      this.nomcollection.setText(ab.getNom_collection());
      this.description.setText(ab.getDescription());
      this.image.setImage(new Image("file:"+ab.getImage()));
     
      
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) throws SQLException, IOException {
        
         CollectionService sa=new CollectionService();
         
        Collection c=new Collection();
        
    c.setNom_collection(nomcollection.getText());
    c.setDescription(description.getText());
    if(selectedFile!=null)
    {
    c.setImage(selectedFile.getName());
      File image = new File("D:/wamp/www/image/"+selectedFile.getName());
       
            Files.copy(selectedFile.toPath(), image.toPath());
    }
    else
        c.setImage(null);
        
          
                  //a.setArrive(datearrive.getValue());
                        
        System.out.println("***********");              
        System.out.println(c);
        System.out.println(ab.getNum_collection());
              System.out.println("***********");              
 
            sa.update(c,ab.getNum_collection());
            System.out.println("voila");
        
           
    }

    @FXML
    private void ajouterImage(ActionEvent event) {
        
        
        
          FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll();
        selectedFile =fc.showOpenDialog(null);
         Image  img = new Image("file:"+selectedFile.getPath());
        image.setImage(img);
        if(selectedFile!=null)
        {
        /*photoField.setText(selectedFile.getName());*/}
    }
    
}
