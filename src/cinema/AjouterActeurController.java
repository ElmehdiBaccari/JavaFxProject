/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import Entities.Acteur;
import com.pidev.service.ActeurService;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mimi
 */
public class AjouterActeurController implements Initializable {

    @FXML
    private AnchorPane AjouterActeur;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtNationalite;
    @FXML
    private DatePicker pickerDateNais;
    @FXML
    private Button btnAjouter;
    
    ArrayList<Acteur>list;
    @FXML
    private TableColumn<Acteur,String> nomC1;
    @FXML
    private TableColumn<Acteur, String> prenomC2;
    @FXML
    private TableColumn<Acteur,Date> dateNaissC3;
    @FXML
    private TableColumn<Acteur, String> nationaliteC4;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<Acteur> tabActeur;
    @FXML
    private Button btnModifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ActeurService as=new ActeurService();
        
        list= (ArrayList<Acteur>) as.getAll();
        ObservableList<Acteur> obs=FXCollections.observableArrayList(list);
       
        nomC1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomC2.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissC3.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        nationaliteC4.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        tabActeur.setItems(obs);
        
        btnAjouter.setOnAction(e->{
            Date d1 = java.sql.Date.valueOf(pickerDateNais.getValue());
            as.insert(new Acteur(txtNom.getText(),txtPrenom.getText(), d1, txtNationalite.getText()));
            list= (ArrayList<Acteur>) as.getAll();
        ObservableList<Acteur> ors=FXCollections.observableArrayList(list);
        tabActeur.setItems(ors);
            
        });
        
        btnModifier.setOnAction(e->{
            Date d1 = java.sql.Date.valueOf(pickerDateNais.getValue());  
            Acteur a=new Acteur(txtNom.getText(),txtPrenom.getText(), d1, txtNationalite.getText());
            a.setId(tabActeur.getSelectionModel().getSelectedItem().getId()) ;
            as.update(a);
            list= (ArrayList<Acteur>) as.getAll();
        ObservableList<Acteur> ors=FXCollections.observableArrayList(list);
        tabActeur.getItems().clear();
        tabActeur.setItems(ors);
           
         
        });
        
        btnSupprimer.setOnAction(e->{
            Acteur a = tabActeur.getSelectionModel().getSelectedItem();
            as.delete(a.getId());
            list= (ArrayList<Acteur>) as.getAll();
        ObservableList<Acteur> ors=FXCollections.observableArrayList(list);
        tabActeur.setItems(ors);
           
    });

        
        
        
        tabActeur.setRowFactory(tv -> {
        TableRow<Acteur> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {

            Acteur clickedRow = row.getItem();
            printRow(clickedRow);
        }
        });
        return row ;
        });
        
        
    }    
    
    
    
    private void printRow(Acteur a) {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     String dd = dateFormat.format(a.getDate_naissance());    
    
    txtNom.setText(a.getNom());
    txtPrenom.setText(a.getPrenom());
    txtNationalite.setText(a.getNationalite());
    pickerDateNais.setValue(LocalDate.parse(dd,formatter));
    
    
    
}
}
