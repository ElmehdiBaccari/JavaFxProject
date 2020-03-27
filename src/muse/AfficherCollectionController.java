/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import ViewController.UserSession;
import com.pidev.entity.Collection;
import com.pidev.entity.Utilisateur;
import com.pidev.service.CollectionService;
import connexionbd.pdf;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class AfficherCollectionController implements Initializable {

    @FXML
    private TableView<Collection> tableviewcoll;
    @FXML
    private TableColumn<Collection, String> nomcollection;
    @FXML
    private TableColumn<Collection, String> datecollection;
    @FXML
    private TableColumn<Collection, String> descriptioncollection;
    @FXML
    private TableColumn<Collection , ImageView> imageCol;
    @FXML
    private Button btnsupprimer;
    
       Collection ab;
    
      ObservableList<Collection> data =FXCollections.observableArrayList();
    FilteredList<Collection> filteredData ;
    @FXML
    private Button pdf;
   
    
    Utilisateur user ;
    @FXML
    private Button btnModifier;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     //  afficher();
     
     user=UserSession.getInstace().getUser();
     if(user.getRole()==1)
     {
         btnModifier.setVisible(false);
         btnsupprimer.setVisible(false);
     }
     
      rafrechir();
      
            CollectionService cs= new CollectionService();
        btnsupprimer.setOnAction(e->{
                    cs.delete(tableviewcoll.getSelectionModel().getSelectedItem().getNum_collection());
                    CollectionService service = new CollectionService();
        /* add column to the tableview and set its items */
        ObservableList<Collection> listeCollection = FXCollections.observableArrayList(Collection.generateImageViews(service.getAll()));
        tableviewcoll.setItems(listeCollection);
            
           
        
                }

    
);
        
        /*
          data=cs.getCollections();
        tableviewcoll.setItems(cs.getCollections());
        filteredData= new FilteredList<>(data, e -> true);
        */
    }   
     public void rafrechir()
    {
        CollectionService service = new CollectionService();
        /* add column to the tableview and set its items */
        ObservableList<Collection> listeCollection = FXCollections.observableArrayList(Collection.generateImageViews(service.getAll()));
        tableviewcoll.setItems(listeCollection);
        System.out.println(listeCollection);
       
        
        
        nomcollection.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Collection, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Collection, String> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getNom_collection());
        }
        });
        
         datecollection.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Collection, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Collection, String> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getDate_collection());
        }
        });
         
                  descriptioncollection.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Collection, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Collection, String> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getDescription());
        }
        });
         
         
         
            imageCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Collection, ImageView>, ObservableValue<ImageView>>() {
                @Override
                public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Collection, ImageView> param) {
                    return new ReadOnlyObjectWrapper(param.getValue().getImgview());
                }
            });
          
          
         
               
       
        
        
        
       

         

          
    }

     
   
    @FXML
    private void handleModfierAction(ActionEvent event) {
         if (tableviewcoll.getSelectionModel().getSelectedItem() != null) {

            
               try {
         Collection c = tableviewcoll.getSelectionModel().getSelectedItem();
        ab = c;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCollection.fxml"));
        Parent root = loader.load();
       ModifierCollectionController ac = loader.getController();
        ac.setCollection(c);
        
        Stage stage= new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        
        } catch (IOException ex) {
            Logger.getLogger(ModifierCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
                   } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une collection");
            alert.show();
        }
    }
/*
    @FXML
    private void advancedSearch(KeyEvent event) {
              searchfield.textProperty().addListener((observableValue,oldValue,newValue) ->{
        filteredData.setPredicate((Predicate<? super Collection>) (Collection c)->{
        if(newValue == null || newValue.isEmpty())
        {return true ;}
        String lowerCaseFilter =newValue.toLowerCase();
        if(c.getNom_collection().contains(newValue))
        {return true;}
        else if(c.getDescription().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        return false ;
    });
    });
         SortedList<Collection> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tableviewcoll.comparatorProperty());
         tableviewcoll.setItems(sortedData);
    }
    */

    @FXML
    private void pdfgenerator(ActionEvent event) {
         pdf a=new pdf();
    
    a.creation();
        System.out.println("ok!");  
        
    }
    
    }
    

