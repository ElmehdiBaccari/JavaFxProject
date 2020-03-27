/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;


import com.pidev.entity.Film;
import com.pidev.service.FilmService;
import connexionbd.GenreEnum;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.plaf.FileChooserUI;


/**
 * FXML Controller class
 *
 * @author Mimi
 */
public class AjouterFilmController implements Initializable {
    File selectedFile;

    @FXML
    private AnchorPane AjouterFilm;
    @FXML
    private TextField txtNomFilm;
    @FXML
    private ComboBox<GenreEnum> cmbGenre;
    @FXML
    private TextField txtPrixFilm;
    @FXML
    private TextArea txtDescriptionFilm;
    @FXML
    private Button btnAjouterFilm;
    @FXML
    private Label imageFilm;
    @FXML
    private Button parcourrirFilm;
    
    private FileChooser fileChooser =new FileChooser();
    final Stage stage =new Stage();
    private Image images;
    @FXML
    private ImageView txtImage;
    @FXML
    private TableColumn<Film, String> nomC1;
    @FXML
    private TableColumn<Film, GenreEnum> genreC2;
    @FXML
    private TableColumn<Film, Double> prixC3;
    @FXML
    private TableColumn<Film, String> descriptionC4;
    @FXML
    private Button btnSupprimerFilm;
    @FXML
    private Button btnModifierFilm;
        ArrayList<Film> list ;
    @FXML
    private TableView<Film> tabFilm;
    @FXML
    private DatePicker pickerDatedeb;
    @FXML
    private DatePicker pickerDateFin;
    @FXML
    private Label imageFilm1;
    @FXML
    private Label imageFilm2;
    @FXML
    private TableColumn<Film, String> dateC5;
    @FXML
    private TableColumn<Film, String> dateC6;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         FilmService fs= new FilmService();
        list= (ArrayList<Film>) fs.getAll();
        
        ObservableList<Film> obs=FXCollections.observableArrayList(list);
        
        nomC1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        genreC2.setCellValueFactory(new PropertyValueFactory<>("genre"));
        prixC3.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descriptionC4.setCellValueFactory(new PropertyValueFactory<>("description"));
       dateC5.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
        dateC6.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

        
        
       
      tabFilm.setItems(obs);
      
    tabFilm.setRowFactory(tv -> {      
    TableRow<Film> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 1) {

            Film clickedRow = row.getItem();
            printRow(clickedRow);
        }
    });
    return row ;
});
      
      
        
         cmbGenre.getItems().add(GenreEnum.Action);
         cmbGenre.getItems().add(GenreEnum.Animation);
         cmbGenre.getItems().add(GenreEnum.Aventure);
         cmbGenre.getItems().add(GenreEnum.Comedie);
         cmbGenre.getItems().add(GenreEnum.Drame);
         cmbGenre.getItems().add(GenreEnum.Horreur);
         cmbGenre.getItems().add(GenreEnum.Policier);
         cmbGenre.getItems().add(GenreEnum.Romance);
         cmbGenre.setValue(GenreEnum.Action);

            btnAjouterFilm.setOnAction(e->{
                 Date d1 = java.sql.Date.valueOf(pickerDatedeb.getValue());
 Date d2 = java.sql.Date.valueOf(pickerDateFin.getValue());
                     if(selectedFile!=null)
                 {
                     
                     System.out.println("hello");
                     File image= new File("D:/wamp/www/image/"+selectedFile.getName());
                         try {
                             
                             Files.copy(selectedFile.toPath(), image.toPath());
                              fs.insert(new Film(txtNomFilm.getText(), cmbGenre.getValue(),Double.parseDouble(txtPrixFilm.getText()),txtDescriptionFilm.getText(),"D:/wamp/www/image/"+selectedFile.getName(),d1,d2));
                                list= (ArrayList<Film>) fs.getAll();
                                ObservableList<Film> ors=FXCollections.observableArrayList(list);
                                tabFilm.getItems().clear();
                                tabFilm.setItems(ors);
                         } catch (IOException ex) {
                             Logger.getLogger(AjouterFilmController.class.getName()).log(Level.SEVERE, null, ex);
                         }
}                   else
                    {
                         System.out.println("bye");
                    fs.insert(new Film(txtNomFilm.getText(), cmbGenre.getValue(),Double.parseDouble(txtPrixFilm.getText()),txtDescriptionFilm.getText(),"D:/wamp/www/image/Capture.png",d1,d2));
                    list= (ArrayList<Film>) fs.getAll();
                    ObservableList<Film> ors=FXCollections.observableArrayList(list);
                    tabFilm.getItems().clear();
                    tabFilm.setItems(ors);
                    
                    }
                     
           
           
        });
                        
                        btnModifierFilm.setOnAction(e->{       
                            Date d1 = java.sql.Date.valueOf(pickerDatedeb.getValue());
                            Date d2 = java.sql.Date.valueOf(pickerDateFin.getValue());
                           
                    // File image= new File("D:/wamp64/www/image/"+selectedFile.getName());
                            Film film = tabFilm.getSelectionModel().getSelectedItem() ;
                            if(selectedFile!=null)
                 {
                   
                     File image= new File("D:/wamp/www/image/"+selectedFile.getName());
                         try {
                              System.out.println("Salut");
                             File file=new File("file:"+film.getImage());
           
                             Path p =Paths.get(film.getImage());
                             Files.deleteIfExists(p);
                             Files.copy(selectedFile.toPath(), image.toPath());
                             Film f=new Film(txtNomFilm.getText(), cmbGenre.getValue(),Double.parseDouble(txtPrixFilm.getText()),txtDescriptionFilm.getText(),"D:/wamp/www/image/"+selectedFile.getName(),d1,d2);
                             f.setId(tabFilm.getSelectionModel().getSelectedItem().getId()) ;
                             fs.update(f);
                              list= (ArrayList<Film>) fs.getAll();
                                ObservableList<Film> ors=FXCollections.observableArrayList(list);
                                tabFilm.getItems().clear();
                                tabFilm.setItems(ors);
                         } catch (IOException ex) {
                             Logger.getLogger(AjouterFilmController.class.getName()).log(Level.SEVERE, null, ex);
                         }
}                   else
                    {
                        System.out.println("bye");
                       Film f=new Film(txtNomFilm.getText(), cmbGenre.getValue(),Double.parseDouble(txtPrixFilm.getText()),txtDescriptionFilm.getText(),film.getImage(),d1,d2);
                        f.setId(tabFilm.getSelectionModel().getSelectedItem().getId()) ;
                       fs.update(f);
                        FilmService serv= new FilmService();
                        list= (ArrayList<Film>) serv.getAll();
                        ObservableList<Film> ors=FXCollections.observableArrayList(list);
                        tabFilm.getItems().clear();
                        tabFilm.setItems(ors);
                       
                    } 
           
        });
                        
            btnSupprimerFilm.setOnAction(e->{
                           
            Film f1 = tabFilm.getSelectionModel().getSelectedItem();
            fs.delete(f1.getId());
             list= (ArrayList<Film>) fs.getAll();
        ObservableList<Film> ors=FXCollections.observableArrayList(list);
        tabFilm.setItems(ors);
                             // System.out.println(""+f1.getImage());
            File file=new File("file:"+f1.getImage());
             try {
                 Path p =Paths.get(f1.getImage());
                 Files.deleteIfExists(p);
             } catch (IOException ex) {
                 Logger.getLogger(AjouterFilmController.class.getName()).log(Level.SEVERE, null, ex);
             }
                            
                            
                        });
            
           
             
        // TODO
    }    
    
    @FXML
    private void ajouterImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll();
        selectedFile =fc.showOpenDialog(null);
        if(selectedFile!=null)
        {
        txtImage.setImage(new Image("file:"+selectedFile.getPath()));}
        
        
        
    }
    private void printRow(Film f) {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     String dd = dateFormat.format(f.getDate_debut());
     String df = dateFormat.format(f.getDate_fin());
    
    Image img =new Image("file:"+f.getImage());
    System.out.println(f.getImage());
    txtImage.setImage(img);
    txtNomFilm.setText(f.getNom());
    txtPrixFilm.setText(f.getPrix()+"");
    txtDescriptionFilm.setText(f.getDescription());
    cmbGenre.setValue(f.getGenre());
    pickerDatedeb.setValue(LocalDate.parse(dd,formatter));
    pickerDateFin.setValue(LocalDate.parse(df,formatter));
    
    
    
}

    @FXML
    private void reload(ActionEvent event) {
        
         FilmService fs= new FilmService();
        list= (ArrayList<Film>) fs.getAll();
        ObservableList<Film> obs=FXCollections.observableArrayList(list);
        tabFilm.setItems(obs);
        
    }
    
    
}
