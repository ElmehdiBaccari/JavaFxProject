/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bibliotheque;



import ViewController.AdminPageController;
import com.jfoenix.controls.JFXTextField;
import com.pidev.entity.livre;
import com.pidev.entity.livre.categorie_enum;
import com.pidev.service.livreService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * FXML Controller class
 *
 * @author thaer
 */
public class Ajouter_livre_fxmlController implements Initializable {
    livre lv =new livre();
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date(System.currentTimeMillis());  
      
          private Boolean isReadyForSubmission = false;
    private livreService databaseHandler =new livreService();
 PieChart bookChart;

    @FXML
    private DatePicker txt_date_edition;
    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_prix_livre;
    @FXML
    private TextField txt_quantite_livre;
    @FXML
    private Button btn_ajouter_livre;
    @FXML
    private ComboBox<livre.categorie_enum> cbox_categorie;
    @FXML
    private TableView<livre> idtable;
    private TableColumn<livre,Integer> id_idlivre;
    @FXML
    private TableColumn<livre,Integer> qteid;
    @FXML
    private TableColumn<livre,Integer> titreid;
    @FXML
    private TableColumn<livre,LocalDate> dateid;
    @FXML
    private TableColumn<livre,Double> prixid;
   @FXML
    private TableColumn<livre,categorie_enum> categorie_id;

   
    private Button emprunt_idfx;
   
private static boolean valid  ;
Alert alert = new Alert(AlertType.INFORMATION);

    @FXML
    private Label errorNom;
    @FXML
    private Label errorprix;
    @FXML
    private Label errorqte;
    @FXML
    private Label errorcategorie;
    @FXML
    private Label errordate;
    
    @FXML
    private PieChart bookPieChart;
    @FXML
    private StackPane bookInfoContainer;
    @FXML
    private ImageView photoField;
    
    File selectedFile ;
    @FXML
    private Button btnModif;
    @FXML
    private TextField searchBox;
    
    
    ObservableList<livre> data=FXCollections.observableArrayList();
	FilteredList<livre> filteredData;
        
     private ArrayList<livre> list = new ArrayList<livre>() ;
     ObservableList<livre> obs =FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnModif.setVisible(false);
        valid =true ;
        livre lv =new livre();
        cbox_categorie.setItems(FXCollections.observableArrayList(lv.getCategorie_livre().values()));
        livreService ps=new livreService();
        list =(ArrayList<livre>) ps.getAll();
        ObservableList<livre> obs=FXCollections.observableArrayList(list);
        filteredData = new FilteredList<>(obs,e->true);
         titreid.setCellValueFactory(new PropertyValueFactory<>("titre_livre"));
         dateid.setCellValueFactory(new PropertyValueFactory<>("date_edition"));
         prixid.setCellValueFactory(new PropertyValueFactory<>("prix_livre"));
         qteid.setCellValueFactory(new PropertyValueFactory<>("qte_livre"));
        // id_idlivre.setCellValueFactory(new PropertyValueFactory<>("id_livre"));
         categorie_id.setCellValueFactory(new PropertyValueFactory<>("categorie_livre"));
         idtable.setItems(obs);
         
        
         
 
        initDrawer();
        initGraphs();
        
      
      
    }    

    
    
    
    
    
    
    
    
    
     public static void color(TextField tf, String color) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if (color.equals("red")) {
            if (! styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.remove("error");                   
        }
    }
      public static void color1(DatePicker tf, String color) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if (color.equals("red")) {
            if (! styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.remove("error");                   
        }
    }
      
       public static void color2(ComboBox<categorie_enum> tf, String color) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if (color.equals("red")) {
            if (! styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.remove("error");                   
        }
    }
    @FXML
    private void ajouter_livre(ActionEvent event) {
          if(txt_titre.getText().isEmpty())
        { color(txt_titre,"red");errorNom.setText("Ce champ est obligatoire !");valid=false;}
         else if(!validateName(txt_titre.getText()))
        { color(txt_titre,"red");errorNom.setText("Le Titre doit contenir que des caractéres !");valid=false;} 
       
        
        if(txt_prix_livre.getText().isEmpty())
        { color(txt_prix_livre,"red");errorprix.setText("Ce champ est obligatoire !");valid=false;}
       else if(!validateNumber(txt_prix_livre.getText()))
        { color(txt_prix_livre,"red");errorprix.setText("insert numbers plz");valid=false;} 
        
        if(txt_quantite_livre.getText().isEmpty())
        { color(txt_quantite_livre,"red");errorqte.setText("Ce champ est obligatoire !");valid=false;}
       else if(!validateNumber(txt_quantite_livre.getText()))
        { color(txt_quantite_livre,"red");errorqte.setText("insert numbers plz");valid=false;}  
        
        
       if(txt_date_edition.getEditor().getText().isEmpty())
        { color1(txt_date_edition,"red");errordate.setText("Ce champ est obligatoire !");valid=false;}
        
        
        if(cbox_categorie.getItems().isEmpty())
        { color2(cbox_categorie,"red");errorcategorie.setText("Ce champ est obligatoire !");valid=false;}
        
         
        if (valid)
        { 
           
           
            livreService ls=new livreService();
            livre liv = new livre(txt_titre.getText(),txt_date_edition.getValue(),Double.parseDouble(txt_prix_livre.getText()),Integer.parseInt(txt_quantite_livre.getText()),cbox_categorie.getValue());
             if(selectedFile!=null)
        {
        liv.setImg("D:/wamp/www/image/"+selectedFile.getName());
         File image= new File("D:/wamp/www/image/"+selectedFile.getName());
           
                try {
                    Files.copy(selectedFile.toPath(), image.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(Ajouter_livre_fxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
            if(ls.getById(liv)==null)
           {
               System.out.println("slt");
            //ls.ajouterlivre(new livre(txt_titre.getText(),txt_date_edition.getValue(),Double.parseDouble(txt_prix_livre.getText()),Integer.parseInt(txt_quantite_livre.getText()),cbox_categorie.getValue()));
            ls.ajouterlivre(liv);
               list =(ArrayList<livre>) ls.getAll();
            obs=FXCollections.observableArrayList(list);
            idtable.setItems(obs);
           bookPieChart.setData(databaseHandler.getBookGraphStatistics());
         }
            else
            {
                livre l =ls.getById(liv);
                l.setQte_livre(l.getQte_livre()+Integer.parseInt(txt_quantite_livre.getText()));
                System.out.println(l.getQte_livre()+Integer.parseInt(txt_quantite_livre.getText()));
                ls.update(l);
                
                list =(ArrayList<livre>) ls.getAll();
                obs=FXCollections.observableArrayList(list);
                idtable.setItems(obs);
                bookPieChart.setData(databaseHandler.getBookGraphStatistics());
            }
        
        }
    valid=true ;
    }
    
        
    @FXML
    private void modifier_livre(MouseEvent event) {
        
           lv.setTitre_livre(txt_titre.getText());
           lv.setDate_edition(txt_date_edition.getValue());
           lv.setPrix_livre(Double.parseDouble(txt_prix_livre.getText()));
           lv.setQte_livre(Integer.parseInt(txt_quantite_livre.getText()));
           lv.setCategorie_livre(cbox_categorie.getValue());
           if (selectedFile!=null)
        {
        lv.setImg("D:/wamp/www/image/"+selectedFile.getName());
         File image= new File("D:/wamp/www/image/"+selectedFile.getName());
           
                try {
                    Files.copy(selectedFile.toPath(), image.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(Ajouter_livre_fxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
               
           livreService ls = new livreService();
           ls.update(lv);
           btnModif.setVisible(false);
           btn_ajouter_livre.setVisible(true);
           
       
       txt_titre.setText("");
            txt_date_edition.setValue(null);
            txt_prix_livre.setText("");
            txt_quantite_livre.setText("");
            
            photoField.setImage(null);
      
         
           
           
           
           
           
           
        idtable.getItems().clear();
        list =(ArrayList<livre>) ls.getAll();
        obs=FXCollections.observableArrayList(list);
        idtable.setItems(obs);
        
    }

      
    @FXML
    private void handledelete(ActionEvent event) {
        
        livre l  = idtable.getSelectionModel().getSelectedItem();
        livreService ls = new livreService();
        ls.delete(l);
         
       list =(ArrayList<livre>) ls.getAll();
        obs=FXCollections.observableArrayList(list);
       idtable.setItems(obs);
        
        
         bookPieChart.setData(databaseHandler.getBookGraphStatistics());
    }

    @FXML
    private void handleupdate(ActionEvent event) {
        
        lv  = idtable.getSelectionModel().getSelectedItem();
       
            txt_titre.setText(lv.getTitre_livre());
            txt_date_edition.setValue(lv.getDate_edition());
            txt_prix_livre.setText(""+lv.getPrix_livre());
            txt_quantite_livre.setText(""+lv.getQte_livre());
            cbox_categorie.setValue(lv.getCategorie_livre());
            photoField.setImage(new Image("file:"+lv.getImg()));
            btnModif.setVisible(true);
            btn_ajouter_livre.setVisible(false);
        
       
       
        
    }

    private void consulter_emprunt(ActionEvent event) {
        
         emprunt_idfx.setOnAction(e->{   
        Parent root;
        
        try {
            
            root=FXMLLoader.load(getClass().getResource("empruntfxml.fxml"));
            emprunt_idfx.getScene().setRoot(root);
                    
              
                    
                    } catch (IOException ex) {
            Logger.getLogger(EmpruntfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }   });
        
        
    }
      
      
    
    
    public static final Pattern CHARACTHERS_ONLY = 
    Pattern.compile("^[a-zA-Z ]+$", Pattern.CASE_INSENSITIVE);
    public static boolean validateName(String nameStr) {
    Matcher matcher = CHARACTHERS_ONLY .matcher(nameStr);
    return matcher.find(); }
    
    public static final Pattern VALID_NUMBER = 
    Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
    
    public static boolean validateNumber(String numberStr) {
    Matcher matcher1 = VALID_NUMBER .matcher(numberStr);
    return matcher1.find(); }

    @FXML
    private void handleMsgKey(KeyEvent event) {
        
         
        color2(cbox_categorie, "blue");
         color(txt_quantite_livre, "blue");
          color(txt_prix_livre, "blue");
           color(txt_titre, "blue");
           color1(txt_date_edition, "blue");
            
           errorprix.setText("");
            errorNom.setText("");
             errorqte.setText("");
             errorcategorie.setText("");
             errordate.setText("");
             
        
        
    }

    @FXML
    private void idtable(MouseEvent event) {
    }
    
    
    private void initGraphs() {
      
        bookPieChart = new PieChart(databaseHandler.getBookGraphStatistics());
        bookInfoContainer.getChildren().add(bookPieChart);
        
        
    }

    private void initDrawer() {
        
       
        
    }

    @FXML
    private void selectImg(ActionEvent event) {
        
         FileChooser fc = new FileChooser();
         fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files","*.jpg"),new FileChooser.ExtensionFilter("PNG files","*.png"),new FileChooser.ExtensionFilter("JPEG files","*.jpeg"));
        selectedFile =fc.showOpenDialog(null);
        if(selectedFile!=null)
        {
        photoField.setImage(new Image("file:"+selectedFile.getPath()));}
        
        
    }

    

    @FXML
    private void searchLivre() {
        
        
          searchBox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filteredData.setPredicate((Predicate<? super livre>)(livre livre)->{
                            
                            
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
                                
				String lowerCaseFilter=newValue.toLowerCase();
				if(livre.getTitre_livre().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(livre.getCategorie_livre().toString().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                           
				return false;
				
			});
		});
		SortedList<livre> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(idtable.comparatorProperty());
		idtable.setItems(sortedData);
        
        
        
        
    }
    
 
  
     
    
    
    
    
    
    
    
    
      
      
  }
        
        
        
    

 
    

