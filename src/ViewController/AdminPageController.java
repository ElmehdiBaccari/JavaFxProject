/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.pidev.entity.Salle;
import com.pidev.entity.Utilisateur;
import com.pidev.service.SalleService;
import com.pidev.service.Uservice;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Damdoub
 */
public class AdminPageController implements Initializable {
    @FXML
    private TableView<Utilisateur> tabUser;
    @FXML
    private TableView<Salle> tabSalle;
    @FXML
    private TextField searchfield;
    @FXML
    private ImageView Picture;
    @FXML
    private Label UName;
    @FXML
    private Label USName;
    @FXML
    private Label Imail;
    @FXML
    private Label Lmail;
    @FXML
    private Label Inumero;
    @FXML
    private Label Lnumero;
    @FXML
    private AnchorPane AnchorAdmin;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXTabPane mainTabPane;
    
    
    @FXML
    private JFXTextField salleNbrPlace;
    @FXML
    private JFXTextField salleName;
    @FXML
    private JFXComboBox<String> salleType;
    @FXML
    private JFXButton salleChose;
    @FXML
    private JFXTextArea salleDes;
    @FXML
    private ImageView salleImg;
    @FXML
    private Tab renewTab31;
    
    Salle salle ;
    private File selectedFile ;
    
    ObservableList<Utilisateur> data =FXCollections.observableArrayList();
    ObservableList<Salle> SalleData =FXCollections.observableArrayList();
    FilteredList<Utilisateur> filteredData ;
    FilteredList<Salle> filteredDataSalle ;
 

    @FXML
    private JFXButton btnAjouter;
    @FXML
    private JFXButton btnModifier;
    @FXML
    private JFXTextField search;
    @FXML
    private Tab gerer_visite;
    @FXML
    private Tab ajouter_collection;
    @FXML
    private Tab modifier_collection;
    @FXML
    private Tab ajouter_Produit;
    @FXML
    private Tab modifier_Produit;
    @FXML
    private Tab Gestion_livres;
    @FXML
    private Tab Gestion_empruntes;
    @FXML
    private Tab Gesiont_films;
    @FXML
    private Tab gestion_acteurs;
    @FXML
    private Tab gestion_realisation;
    @FXML
    private Tab stat_films;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            gerer_visite.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AfficherMusee.fxml")));
            modifier_collection.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AfficherCollection.fxml")));
            ajouter_collection.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AjouterCollection.fxml")));
            modifier_Produit.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AfficherProduit.fxml")));
            ajouter_Produit.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AjouterProduit.fxml")));
            Gestion_livres.setContent((Node) FXMLLoader.load(this.getClass().getResource("/Bibliotheque/ajouter_livre_fxml.fxml")));
            Gestion_empruntes.setContent((Node) FXMLLoader.load(this.getClass().getResource("/Bibliotheque/empruntfxml.fxml")));
             Gesiont_films.setContent((Node) FXMLLoader.load(this.getClass().getResource("/cinema/AjouterFilm.fxml")));
            gestion_acteurs.setContent((Node) FXMLLoader.load(this.getClass().getResource("/cinema/AjouterActeur.fxml")));
            gestion_realisation.setContent((Node) FXMLLoader.load(this.getClass().getResource("/cinema/AjouterRealisation.fxml")));
            stat_films.setContent((Node) FXMLLoader.load(this.getClass().getResource("/cinema/stat.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      btnModifier.setVisible(false);
        UName.setVisible(false);
        USName.setVisible(false);
         Imail.setVisible(false);
          Lmail.setVisible(false);
           Inumero.setVisible(false);
            Lnumero.setVisible(false);
            
            
        salleType.getItems().add("Cinema");
        salleType.getItems().add("Theathre");
        salleType.getItems().add("Formation");
        salleType.getItems().add("Conférence");
        salleType.setValue("Cinema");
            
            
            
            
        // User Table Initialize  
        TableColumn<Utilisateur,Integer> columnUserNom =new TableColumn("Nom");
        TableColumn<Utilisateur,Integer> columnUserPrenom =new TableColumn("Prenom");
        TableColumn<Utilisateur,Integer> columnUserMail =new TableColumn("Mail");
        TableColumn<Utilisateur,Integer> columnUserDate =new TableColumn("Date de naissance");
        TableColumn<Utilisateur,Integer> columnUserState =new TableColumn("Statut");
        
        columnUserNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnUserPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnUserMail.setCellValueFactory(new PropertyValueFactory<>("adressemail"));
        columnUserDate.setCellValueFactory(new PropertyValueFactory<>("datedenaissance"));
        columnUserState.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        columnUserNom.prefWidthProperty().bind(tabUser.widthProperty().divide(5));
        columnUserPrenom.prefWidthProperty().bind(tabUser.widthProperty().divide(5));
        columnUserMail.prefWidthProperty().bind(tabUser.widthProperty().divide(5));
        columnUserDate.prefWidthProperty().bind(tabUser.widthProperty().divide(5));
        columnUserState.prefWidthProperty().bind(tabUser.widthProperty().divide(5));
        
        
        
        
      //Salle Table Initialize
      
      
      
      
        TableColumn<Salle,Integer> columnSalleNom =new TableColumn("Nom");
        TableColumn<Salle,Integer> columnSalleType =new TableColumn("Type");
        TableColumn<Salle,Integer> columnSalleNbr =new TableColumn("Places");
        TableColumn<Salle,Integer> columnSalleDes =new TableColumn("Describtion");

        columnSalleNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnSalleType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnSalleNbr.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
        columnSalleDes.setCellValueFactory(new PropertyValueFactory<>("description"));
     
        columnSalleNom.prefWidthProperty().bind(tabSalle.widthProperty().divide(4));
        columnSalleType.prefWidthProperty().bind(tabSalle.widthProperty().divide(4));
        columnSalleNbr.prefWidthProperty().bind(tabSalle.widthProperty().divide(4));
        columnSalleDes.prefWidthProperty().bind(tabSalle.widthProperty().divide(4));
        
        
        
        
        tabUser.getColumns().addAll(columnUserNom,columnUserPrenom,columnUserMail,columnUserDate,columnUserState);
        tabSalle.getColumns().addAll(columnSalleNom,columnSalleType,columnSalleNbr,columnSalleDes);
        SalleService sService = new SalleService();
        Uservice serv = new Uservice();
        data=serv.getUsers();
        
        tabUser.setItems(serv.getUsers());
        
        SalleData=sService.getSalles();
        tabSalle.setItems(SalleData);
        filteredData= new FilteredList<>(data, e -> true);
        filteredDataSalle=new FilteredList<>(SalleData,e -> true);
        
         
             
         }
         
        // TODO

    @FXML
    private void advancedSearch(KeyEvent event) {
        
        
        
        
             searchfield.textProperty().addListener((observableValue,oldValue,newValue) ->{
        filteredData.setPredicate((Predicate<? super Utilisateur>) (Utilisateur user)->{
        if(newValue == null || newValue.isEmpty())
        {return true ;}
        String lowerCaseFilter =newValue.toLowerCase();
        if(user.getNom().contains(newValue))
        {return true;}
        else if(user.getPrenom().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        else if(user.getAdressemail().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        return false ;
    });
    });
         SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(tabUser.comparatorProperty());
         tabUser.setItems(sortedData);
        
        
        
    }

    @FXML
    private void showProfile(MouseEvent event) {
        
         Utilisateur user  = tabUser.getSelectionModel().getSelectedItem();
         Image img = new Image("file:"+user.getPhoto());
         Picture.setImage(img);
         UName.setText(user.getNom());
         USName.setText(user.getPrenom());
         Lnumero.setText(user.getNumero()+"");
         Lmail.setText(user.getAdressemail());
        UName.setVisible(true);
        USName.setVisible(true);
         Imail.setVisible(true);
          Lmail.setVisible(true);
           Inumero.setVisible(true);
            Lnumero.setVisible(true);
         
         
        
        
        
        
    }

    private void Disconnect(ActionEvent event) {
        
        
        try {
            AnchorAdmin.getScene().setRoot(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    } catch (IOException ex) {
            Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterSalle(ActionEvent event) {
         RequiredFieldValidator validator = new RequiredFieldValidator();
          validator.setMessage("Champ obligatoire !");
          RegexValidator regNumber = new RegexValidator();
          regNumber.setRegexPattern("^([0-7][0-9][0-9]|8)$");
          regNumber.setMessage("Nombre compris entre 150 et 800");
           RegexValidator regName = new RegexValidator();
          regName.setRegexPattern("^[a-zA-Z0-9 ]+$");
          regName.setMessage("que des lettres et des chiffres !");
          RegexValidator regDesc = new RegexValidator();
          regDesc.setRegexPattern("^[a-zA-Z ]+$");
          regDesc.setMessage("que des lettres !");
          
          
          salleName.getValidators().add(validator);
          salleName.getValidators().add(regName);
       
          salleNbrPlace.getValidators().add(validator);
           salleNbrPlace.getValidators().add(regNumber);
           
           salleDes.getValidators().add(validator);
           salleDes.getValidators().add(regDesc);
           if(salleName.validate()&&salleNbrPlace.validate()&&salleDes.validate())
           {
               
            if(selectedFile!=null)
            {
                try {
                    salle =new Salle(salleName.getText(),salleType.getValue(),Integer.parseInt(salleNbrPlace.getText()),"D:/wamp/www/image/"+salleName.getText()+selectedFile.getName(),salleDes.getText());
                    File image= new File("D:/wamp/www/image/"+salleName.getText()+selectedFile.getName());
                    Files.copy(selectedFile.toPath(), image.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else
            salle =new Salle(salleName.getText(),salleType.getValue(),Integer.parseInt(salleNbrPlace.getText()),"D:/wamp/www/image/default.png",salleDes.getText());        
           SalleService sService =new SalleService();
           sService.insert(salle);
           
           SalleData=sService.getSalles();
           tabSalle.setItems(SalleData);
           filteredDataSalle=new FilteredList<>(SalleData,e -> true);
           
           }
        
        
        
    }

    @FXML
    private void ModifierSalle(ActionEvent event) {
        
        
        salle.setNom(salleName.getText());
        salle.setType(salleType.getValue());
        salle.setNbrplace(Integer.parseInt(salleNbrPlace.getText()));
        if(selectedFile!=null)
        {
        salle.setPhoto("D:/wamp/www/image/"+salleName.getText()+selectedFile.getName());
         File image= new File("D:/wamp/www/image/"+salleName.getText()+selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), image.toPath());
            } catch (IOException ex) {
                Logger.getLogger(AdminPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        salle.setDescription(salleDes.getText());
        SalleService sService =new SalleService();
        sService.update(salle);
        
         btnModifier.setVisible(false);
        btnAjouter.setVisible(true);
        SalleData=sService.getSalles();
        tabSalle.getItems().clear();
        tabSalle.setItems(SalleData);
         filteredDataSalle=new FilteredList<>(SalleData,e -> true);
        
        
    }

    @FXML
    private void UploadImage(ActionEvent event) {
        
        
         FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files","*.jpg"),new FileChooser.ExtensionFilter("PNG files","*.png"),new FileChooser.ExtensionFilter("JPEG files","*.jpeg"));
        selectedFile =fc.showOpenDialog(null);
        if(selectedFile!=null)
        {
            Image img =new Image("file:"+selectedFile.getPath());
        salleImg.setImage(img);
                }
    }

    @FXML
    private void Delete(ActionEvent event) {
        
        salle= tabSalle.getSelectionModel().getSelectedItem();
         SalleService sService =new SalleService();
        sService.delete(salle);
        SalleData=sService.getSalles();
        tabSalle.getItems().clear();
        tabSalle.setItems(SalleData);
        filteredDataSalle=new FilteredList<>(SalleData,e -> true);
        
        
    }

    @FXML
    private void PrepareUpdate(ActionEvent event) {
        btnModifier.setVisible(true);
        btnAjouter.setVisible(false);
       salle= tabSalle.getSelectionModel().getSelectedItem();
       if(salle!=null)
       {
       salleName.setText(salle.getNom());
       salleType.setValue(salle.getType());
       salleNbrPlace.setText(salle.getNbrplace()+"");
       salleDes.setText(salle.getDescription());
       salleImg.setImage(new Image("file:"+salle.getPhoto()));
       }
       
        
        
    }

    @FXML
    private void research(KeyEvent event) {
        
        
        
          search.textProperty().addListener((observableValue,oldValue,newValue) ->{
        filteredDataSalle.setPredicate((Predicate<? super Salle>) (Salle salle)->{
        if(newValue == null || newValue.isEmpty())
        {return true ;}
        String lowerCaseFilter =newValue.toLowerCase();
        if(salle.getNom().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        else if(salle.getType().toLowerCase().contains(lowerCaseFilter))
        {return true;}
         else if(Integer.toString(salle.getNbrplace()).toLowerCase().contains(lowerCaseFilter))
        {return true;}
        return false ;
    });
    });
         SortedList<Salle> sortedData = new SortedList<>(filteredDataSalle);
         sortedData.comparatorProperty().bind(tabSalle.comparatorProperty());
         tabSalle.setItems(sortedData);
        
        
        
    }
        
    
    
      
    

    

  
    }    
    
   
   /* 
}
    }
    
    });*/
  
    

