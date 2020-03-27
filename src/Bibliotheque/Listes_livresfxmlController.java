/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bibliotheque;


import com.pidev.entity.livre;
import com.pidev.entity.livre.categorie_enum;
import com.pidev.service.livreService;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author thaer
 */
public class Listes_livresfxmlController implements Initializable {


     @FXML
    private TableView<livre> idtable;
    @FXML
    private TableColumn<livre,Integer> qteid;
    @FXML
    private TableColumn<livre,Integer> titreid;
    @FXML
    private TableColumn<livre,LocalDate> dateid;
    @FXML
    private TableColumn<livre,Double> prixid;
    @FXML
    private TableColumn<livre, categorie_enum> categorie_id;
    @FXML
    private MenuItem id_emprunt;
    
    ObservableList<livre> data=FXCollections.observableArrayList();
	FilteredList<livre> filteredData;
        
     private ArrayList<livre> list = new ArrayList<livre>() ;
     ObservableList<livre> obs =FXCollections.observableArrayList();
    
     
    @FXML
    private Button btn_emprunt;
    private TextField searchBox;

    
    livre lv =new livre();  
    public Listes_livresfxmlController() {
        
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        livre lv =new livre();  
        livreService ls=new livreService(); 
        ObservableList<livre> obs=FXCollections.observableArrayList(list);
        list =(ArrayList<livre>) ls.getAll();
        obs=FXCollections.observableArrayList(list);
        
        filteredData = new FilteredList<>(obs,e->true);
        
        
        titreid.setCellValueFactory(new PropertyValueFactory<>("titre_livre"));
        dateid.setCellValueFactory(new PropertyValueFactory<>("date_edition"));
         prixid.setCellValueFactory(new PropertyValueFactory<>("prix_livre"));
         qteid.setCellValueFactory(new PropertyValueFactory<>("qte_livre"));
       
         categorie_id.setCellValueFactory(new PropertyValueFactory<>("categorie_livre"));
         idtable.setItems(obs);
        
        list =(ArrayList<livre>) ls.getAll();
        obs=FXCollections.observableArrayList(list);
        idtable.setItems(obs);
        
    }    

    @FXML
    private void demander_emprunt(ActionEvent event) {
    
        lv =idtable.getSelectionModel().getSelectedItem();
        if(lv.getQte_livre()==0)
        {
           Alert alert= new Alert(Alert.AlertType.WARNING);
             alert.setTitle("WARNING");
             alert.setHeaderText(null);
             alert.setContentText("qte = 0");
             alert.showAndWait();
        
        }
        
        else{
        
            
             
           
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("ajouter_emprunt.fxml"));
        
         try {
            Parent parent = loader.load();
            Ajouter_empruntController emprunt ;
            
            emprunt=loader.getController();
             emprunt.setLivre(lv);
             
             
            
            
             emprunt.setLivre(lv); 
            System.out.println(lv); 
            emprunt.setLivre(lv); 
             Stage stage = null;
            
                stage = new Stage(StageStyle.DECORATED);
           
            stage.setTitle("Emprunter un livre");
            stage.setScene(new Scene(parent));
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(Listes_livresfxmlController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        
        
        
        
        
        
        }
        
    }

    @FXML
    private void consulter_emprunt(ActionEvent event) {
        
        
        btn_emprunt.setOnAction(e->{   
        Parent root;
        
        try {
            
            root=FXMLLoader.load(getClass().getResource("empruntfxml.fxml"));
            btn_emprunt.getScene().setRoot(root);
                    
              
                    
                    } catch (IOException ex) {
            Logger.getLogger(EmpruntfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }   });
        
        
        
        }


 
    }

    

