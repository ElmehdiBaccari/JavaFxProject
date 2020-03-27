/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import ViewController.UserSession;
import com.pidev.entity.Produit;
import com.pidev.entity.Utilisateur;
import com.pidev.service.ProduitService;
import connexionbd.loadwindow;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableColumn<Produit, String> nomproduit;
    @FXML
    private TableColumn<Produit, String> typeproduit;
    @FXML
    private TableColumn<Produit, Double> prixproduit;
    @FXML
    private TableColumn<Produit, Integer> quantiteproduit;
    @FXML
    private TableColumn<Produit, ImageView> image;
    @FXML
    private TableView<Produit> tableproduit;
    @FXML
    private Button btnsupprimer;
    
      public static Produit ab;
    Utilisateur user ;
    @FXML
    private Button btnmodifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // afficher();
        // TODO
        
         user=UserSession.getInstace().getUser();
     if(user.getRole()==1)
     {
         btnmodifier.setVisible(false);
         btnsupprimer.setVisible(false);
     }
        afficher();
         ProduitService cs = new ProduitService();
        
        btnsupprimer.setOnAction(e->{
                    cs.supprimer_produit(tableproduit.getSelectionModel().getSelectedItem().getId_produit());
                    System.out.println("fio "+tableproduit.getSelectionModel().getSelectedItem().getId_produit());
            
                    ProduitService service = new ProduitService();
        /* add column to the tableview and set its items */
        ObservableList<Produit> listeProduit = FXCollections.observableArrayList(Produit.generateImageViews(service.getAll()));
        tableproduit.setItems(listeProduit);
        });
    }    
    
    
    
    
     public void afficher()
    {
        ProduitService service = new ProduitService();
        /* add column to the tableview and set its items */
        ObservableList<Produit> listeProduit = FXCollections.observableArrayList(Produit.generateImageViews(service.getAll()));
        tableproduit.setItems(listeProduit);
        System.out.println(listeProduit);
       ;
        
        
        nomproduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getNom_produit());
        }
        });
        
      
         typeproduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit,  String>, ObservableValue< String>>() {
        @Override
        public ObservableValue< String> call(TableColumn.CellDataFeatures<Produit,  String> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getType_produit().toString());
        }
        });


         
         
              prixproduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Double>, ObservableValue<Double>>() {
        @Override
        public ObservableValue<Double> call(TableColumn.CellDataFeatures<Produit, Double> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getPrix_produit());
        }
        });
              
              
          quantiteproduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Integer>, ObservableValue<Integer>>() {
        @Override
        public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Produit, Integer> param) {
        return new ReadOnlyObjectWrapper(param.getValue().getQuantite());
        }
        });
         
         
         
         
         
         
         
         
            image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, ImageView>, ObservableValue<ImageView>>() {
                @Override
                public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Produit, ImageView> param) {
                    return new ReadOnlyObjectWrapper(param.getValue().getImgview());
                }
            });
          
          
         
               
             
        
        
        
       

         

          
    }
     
     
     
      

    @FXML
    private void handleModfierAction(ActionEvent event) {
          if (tableproduit.getSelectionModel().getSelectedItem() != null) {

            
               try {
          Produit p = tableproduit.getSelectionModel().getSelectedItem();
        ab = p;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
        Parent root = loader.load();
       ModifierProduitController ac = loader.getController();
        ac.setProduit(p);
        
        Stage stage= new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        
        } catch (IOException ex) {
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
                   } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un produit");
            alert.show();
        }
    }

    @FXML
    private void acheter(ActionEvent event) throws SQLException {
        ProduitService sp = new ProduitService();
        if (tableproduit.getSelectionModel().getSelectedItem().getQuantite()>0)
        sp.stock(tableproduit.getSelectionModel().getSelectedItem().getQuantite()-1,tableproduit.getSelectionModel().getSelectedItem().getId_produit());
                ProduitService service = new ProduitService();
        /* add column to the tableview and set its items */
        ObservableList<Produit> listeProduit = FXCollections.observableArrayList(Produit.generateImageViews(service.getAll()));
        tableproduit.setItems(listeProduit);
        
    }

    
}
