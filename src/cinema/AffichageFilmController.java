/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;


import Entities.commentaireFilm;
import ViewController.UserSession;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.pidev.entity.Film;
import com.pidev.entity.Utilisateur;
import com.pidev.service.FilmService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Mimi
 */
public class AffichageFilmController implements Initializable {

    @FXML
    private ImageView ImgFilm;
    @FXML
    private Label lbNomFilm;
    @FXML
    private Label lbGnereFilm;
    @FXML
    private Label lbDateFin;
    @FXML
    private Label lbDateDebut;
    @FXML
    private Label lbPrixFilm;
    @FXML
    private Label lbDescriptionFilm;
int id ;
    @FXML
    private Rating stars;
    @FXML
    private Label lbRating;
    @FXML
    private JFXTextArea commentairecontenu;
    @FXML
    private JFXListView<Pane> commentairelist;
    @FXML
    private JFXButton addcomment;
    @FXML
    private Label lblacteur;
    /**
     *
     * Initializes the controller class.
     */
    Utilisateur user ;
    
     public void setAttribute(int id)
    {
    this.id=id;
    float R =0 ;
    FilmService fs= new FilmService();
     Film f = fs.getFilmById(id);
     lbNomFilm.setText(f.getNom());
     lbGnereFilm.setText(f.getGenre()+"");
     lbPrixFilm.setText(f.getPrix()+"");
     lbDateDebut.setText(f.getDate_debut()+"");
     lbDateFin.setText(f.getDate_fin()+"");
     lbDescriptionFilm.setText(f.getDescription());
     Image img =new Image("file:"+f.getImage());
     ImgFilm.setImage(img);
      if(fs.searchRating(user.getId(), id)!=(-1))
         // stars.setRating(id);
        stars.setDisable(true);
      R =fs.calcul(id);
        lbRating.setText("("+R+")");
        stars.setRating(R);
        
        
        
        
        
        
                  int idf= f.getId();
lblacteur.setText(fs.AfficherActeurParFilm(idf));
        
   this.id=id;
    ObservableList<Pane> commentaireliste = FXCollections.observableArrayList();  
             FilmService service= new FilmService();

     Film Fcourant = new Film();
                             Fcourant = service.getFilmById(id);
    for (commentaireFilm c:service.AfficherCommentaireP(Fcourant))
        {
             Pane pane = new Pane();
        pane.setStyle(" -fx-background-color: white");
                                
                       JFXButton t1=new JFXButton("supprimer");    
            
                        t1.setStyle("-fx-font-weight: bold;");
                        
                     
                        HBox hb2=new HBox(t1);
                        
                        
                                hb2.setLayoutX(250);
                                hb2.setLayoutY(0);
                                hb2.setPrefWidth( 100); 
                                hb2.setPrefHeight( 35);
                                hb2.setStyle("-fx-background-color: #ea7066; ; -fx-background-radius: 0 0 10 0;");
                        
                                
                                Text quan1=new Text(c.getContent());        
                              // Label quant2 = new Label(String.valueOf(c.getContent()));
                                quan1.setLayoutX(10);
                                quan1.setLayoutY(20);
                                
                        quan1.setStyle("-fx-font-weight: bold;-fx-fill : #d82819;-fx-font-size:15px;");
                        //quant2.setStyle("-fx-font-weight: bold;-fx-fill : #d82819;-fx-font-size:15px;");
                      if(user.getId()==c.getUser_id())
                        {
                        pane.getChildren().addAll(quan1,hb2);
                        }
                        else{
                            pane.getChildren().addAll(quan1);
                        
                        }
                              t1.setOnMouseClicked((MouseEvent event2) -> {
     service.deletecommentaire(c.getId());
            // refresh();
            
           this.setAttribute(idf);
                              });
                                
      commentaireliste.add(pane);
                   
        
        // TODO
    }
             commentairelist.setItems(commentaireliste);
      
        
        
        
        
        
        
        
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        user =UserSession.getInstace().getUser();
        // TODO
        
    
}
    
   
    @FXML
    private void rate(MouseEvent event) {
        int rating=(int)(stars.getRating());
        float R =0 ;     
        stars.setDisable(true);
          FilmService fs= new FilmService();
        fs.insert(user.getId(), id, rating);
        R =fs.calcul(id);
        lbRating.setText("("+R+")");
         
    }

    
    
    @FXML
    private void addcomment(ActionEvent event) {
        commentaireFilm c2 = new commentaireFilm();
        c2.setContent(commentairecontenu.getText());
        c2.setFilm_id(id);
        c2.setUser_id(user.getId());
        FilmService service= new FilmService();
     Film f = service.getFilmById(id);
        service.AjouterCommentaire(c2);
        
         Film Fcourant = new Film();
                  
                        Fcourant = service.getFilmById(id);
                        // TODO
                        //qrcode
                        
                  int idf= Fcourant.getId();
                 
         ObservableList<Pane> commentaireliste = FXCollections.observableArrayList();  
       for (commentaireFilm c:service.AfficherCommentaireP(Fcourant))
        {
             Pane pane = new Pane();
        pane.setStyle(" -fx-background-color: white");
                                
                       JFXButton t1=new JFXButton("supprimer");    
            
                        t1.setStyle("-fx-font-weight: bold;");
                        
                     
                        HBox hb2=new HBox(t1);
                        
                        
                                hb2.setLayoutX(250);
                                hb2.setLayoutY(0);
                                hb2.setPrefWidth( 100); 
                                hb2.setPrefHeight( 35);
                                hb2.setStyle("-fx-background-color: #ea7066; ; -fx-background-radius: 0 0 10 0;");
                        
                                
                                Text quan1=new Text(c.getContent());        
                              // Label quant2 = new Label(String.valueOf(c.getContent()));
                                quan1.setLayoutX(10);
                                quan1.setLayoutY(20);
                                
                        quan1.setStyle("-fx-font-weight: bold;-fx-fill : #d82819;-fx-font-size:15px;");
                        //quant2.setStyle("-fx-font-weight: bold;-fx-fill : #d82819;-fx-font-size:15px;");
                      if(user.getId()==c.getUser_id())
                        {
                        pane.getChildren().addAll(quan1,hb2);
                        }
                        else{
                            pane.getChildren().addAll(quan1);
                        
                        }
                              t1.setOnMouseClicked((MouseEvent event2) -> {
                                 service.deletecommentaire(c.getId());
            // refresh();
            
           this.setAttribute(idf);
                              });
                                
      commentaireliste.add(pane);
                   
        
        // TODO
    }
             commentairelist.setItems(commentaireliste);
        
    }
    
    
}




