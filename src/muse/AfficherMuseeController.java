/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import ViewController.UserSession;
import com.pidev.entity.Musee;
import com.pidev.entity.Utilisateur;
import com.pidev.service.MuseeService;
import connexionbd.SendEmail;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class AfficherMuseeController implements Initializable {
  
    @FXML
    private TableView<Musee> tableview;
    @FXML
    private TableColumn<Musee, String> txtnom;
    @FXML
    private TableColumn<Musee, String> txtprenom;
    @FXML
    private TableColumn<Musee, String> txtarrive;
    @FXML
    private TableColumn<Musee, String> txtemail;
    @FXML
    private TableColumn<Musee, Integer> txttelephone;
    @FXML
    private TableColumn<Musee, Integer> textnbr;
    @FXML
    private TableColumn<Musee, Float> txtprix;
    @FXML
    private Button btnsupprimer;

      public static Musee ab;
    @FXML
    private TextField searchfield;
    
      ObservableList<Musee> data =FXCollections.observableArrayList();
    FilteredList<Musee> filteredData ;
    Utilisateur user ;
    
    //u=session.getUser()
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          user = UserSession.getInstace().getUser();
         MuseeService ps= new MuseeService();
         /*if (u.getType()=="admin"){
              ArrayList<Musee> list=(ArrayList<Musee>) ps.getAll();
         }
         else if (u.getType=="normal"){
              ArrayList<Musee> list=(ArrayList<Musee>) ps.getUserVisit();
         }*/
         ArrayList<Musee> list;
         if(user.getAdressemail().equals("admin"))
        list=(ArrayList<Musee>) ps.getAll();
         else
         list=(ArrayList<Musee>) ps.getUserVisit(user.getAdressemail());    
        ObservableList<Musee> obs=FXCollections.observableArrayList(list);
        tableview.setItems(obs);
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        txtarrive.setCellValueFactory(new PropertyValueFactory<>("arrive"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        txttelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        textnbr.setCellValueFactory(new PropertyValueFactory<>("personne"));
         txtprix.setCellValueFactory(new PropertyValueFactory<>("fee"));
         
         ObservableList<Musee> ors;
         if(user.getAdressemail().equals("admin"))
         {
       
        data=ps.getUsers();
        tableview.setItems(ps.getUsers());
         }
         else
         {
             ors=FXCollections.observableArrayList(ps.getUserVisit(user.getAdressemail()));
         data=ors;
        tableview.setItems(ors);
         }
          
        filteredData= new FilteredList<>(data, e -> true);
         
        
        btnsupprimer.setOnAction(e->{
                    ps.delete(tableview.getSelectionModel().getSelectedItem().getId());
                    System.out.println("fio "+tableview.getSelectionModel().getSelectedItem().getNom());
             Parent root;
             data=ps.getUsers();
             tableview.setItems(ps.getUsers());
           
        });
        
           
        
        
        
       
        

               
        
    }  
    


    @FXML
    private void handleModfierAction(ActionEvent event) {
             if (tableview.getSelectionModel().getSelectedItem() != null) {

            
               try {
          Musee m = tableview.getSelectionModel().getSelectedItem();
        ab = m;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMusee.fxml"));
        Parent root = loader.load();
       ModifierMuseeController ac = loader.getController();
        ac.setMusee(m);
        
        Stage stage= new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        MuseeService ps= new MuseeService();
        data=ps.getUsers();
        tableview.setItems(ps.getUsers());
        
        
        } catch (IOException ex) {
            Logger.getLogger(ModifierMuseeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                   } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une visite");
            alert.show();
        }
    }

    @FXML
    private void SendEmail(ActionEvent event) {
       System.out.println(tableview.getSelectionModel().getSelectedItem().getEmail());
        SendEmail.Send(tableview.getSelectionModel().getSelectedItem().getEmail());
        
        
    }

    @FXML
    private void advancedSearch(KeyEvent event) {
              searchfield.textProperty().addListener((observableValue,oldValue,newValue) ->{
        filteredData.setPredicate((Predicate<? super Musee>) (Musee m)->{
        if(newValue == null || newValue.isEmpty())
        {return true ;}
        String lowerCaseFilter =newValue.toLowerCase();
        if(m.getNom().contains(newValue))
        {return true;}
        else if(m.getPrenom().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        else if(m.getEmail().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        return false ;
    });
    });
         SortedList<Musee> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tableview.comparatorProperty());
         tableview.setItems(sortedData);
        
    }
    }
    
    
    
        
    
    
    

