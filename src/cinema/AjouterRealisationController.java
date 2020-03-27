/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import Entities.Acteur;
import com.pidev.entity.Realisation;
import com.pidev.service.RealisationService;
import connexionbd.GenreEnum;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mimi
 */
public class AjouterRealisationController implements Initializable {

    @FXML
    private AnchorPane ajouterRealisation;
    @FXML
    private ComboBox<String> cmbNomFilm;
    @FXML
    private ComboBox<String> cmbNomActeur;
    private DatePicker pickDateReal;
    @FXML
    private Button btnAffecter;
    ArrayList<String>list;
    ArrayList<Realisation>list1;
    @FXML
    private TableColumn<Realisation, String> nomFilmC1;
    @FXML
    private TableColumn<Realisation, GenreEnum> genreC2;
    @FXML
    private TableColumn<Realisation, String> nomActeurC3;
    @FXML
    private TableColumn<Realisation, String> prenomActeurC4;
    @FXML
    private TableColumn<Realisation, String> nationaliteActeurC5;
    private TableColumn<Realisation, String> dateRealC6;
    @FXML
    private TableView<Realisation> tabRealisation;
    @FXML
    private Button btnSupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        RealisationService rs= new RealisationService();
        list= (ArrayList<String>)rs.getAllNameActeur();
        ObservableList<String> obs=FXCollections.observableArrayList(list);
        
        list= (ArrayList<String>)rs.getAllNameFilm();
        ObservableList<String> obs1=FXCollections.observableArrayList(list);
        cmbNomActeur.getItems().addAll(obs);
        cmbNomFilm.getItems().addAll(obs1);
        
       list1=(ArrayList<Realisation>) rs.getAll();
       ObservableList<Realisation> obss=FXCollections.observableArrayList(list1);
        
        nomFilmC1.setCellValueFactory(new PropertyValueFactory<>("nomFilm"));
        genreC2.setCellValueFactory(new PropertyValueFactory<>("genreFilm"));
        nomActeurC3.setCellValueFactory(new PropertyValueFactory<>("nomActeur"));
        prenomActeurC4.setCellValueFactory(new PropertyValueFactory<>("prenomActeur"));
       nationaliteActeurC5.setCellValueFactory(new PropertyValueFactory <>("nationaliteActeur"));
        //dateRealC6.setCellValueFactory(new PropertyValueFactory<>("date_realisation"));    
        
        tabRealisation.setItems(obss);
        // Date d1 = java.sql.Date.valueOf("2010-11-11");
       //  rs.insert(new Realisation(41, 9, d1));
        
       

       
        btnAffecter.setOnAction(e->{
           // System.out.println(""+cmbNomActeur.getValue());
           // System.out.println(""+rs.getIdByNameFilm(cmbNomFilm.getValue()));
           
           rs.insert(new Realisation(rs.getIdByNameFilm(cmbNomFilm.getValue()),rs.getIdByNameActeur(cmbNomActeur.getValue())));
              list1=(ArrayList<Realisation>) rs.getAll();
       ObservableList<Realisation> ors=FXCollections.observableArrayList(list1);
        tabRealisation.setItems(ors);    
           
        });
        btnSupprimer.setOnAction(e->{
            Realisation r = tabRealisation.getSelectionModel().getSelectedItem();
            System.out.println(""+r.getId());
            rs.delete(r);
            list1=(ArrayList<Realisation>) rs.getAll();
       ObservableList<Realisation> ors=FXCollections.observableArrayList(list1);
        tabRealisation.setItems(ors);    
           
    });
        // TODO
    }    
    
}
