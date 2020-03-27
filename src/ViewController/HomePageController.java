/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.pidev.entity.Utilisateur;
import com.pidev.service.SalleService;
import com.pidev.service.livreService;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Damdoub
 */
public class HomePageController implements Initializable {
    
  
 
    @FXML
    private JFXDrawer drawer;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private JFXHamburger hamburger;
    private Utilisateur User ;
    @FXML
    private Tab renewTab31;
    @FXML
    private AnchorPane anchorSalle;
    @FXML
    private JFXMasonryPane Man;
    @FXML
    private ScrollPane SPane;
    @FXML
    private JFXButton btnAll;
    @FXML
    private JFXButton btnThea;
    @FXML
    private JFXButton btnCinema;
    @FXML
    private JFXButton btnFormation;
    @FXML
    private JFXButton btnConf;
    @FXML
    private Tab collectionhome;
    @FXML
    private Tab collectionproduit;
    @FXML
    private Tab proggramervisite;
    @FXML
    private Tab consultervisite;
    @FXML
    private JFXMasonryPane manLivre;
    @FXML
    private JFXButton toutlivre;
    @FXML
    private JFXButton toutEconomie;
    @FXML
    private JFXButton toutScience;
    @FXML
    private JFXButton toutInformatique;
    @FXML
    private JFXButton toutMedcine;
    @FXML
    private JFXButton ToutSport;
    @FXML
    private JFXButton toutArt;
    @FXML
    private ScrollPane SpaneLivre;
    @FXML
    private Tab front_film;
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        try {
            collectionhome.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AfficherCollection.fxml")));
            collectionproduit.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AfficherProduit.fxml")));
            proggramervisite.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AjouterMusee.fxml")));
            consultervisite.setContent((Node) FXMLLoader.load(this.getClass().getResource("/muse/AfficherMusee.fxml")));
            front_film.setContent((Node) FXMLLoader.load(this.getClass().getResource("/cinema/AfficherFilms.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
        
        List<AnchorPane> Anchorlist = new ArrayList<>();
        List<AnchorPane> AnchorlistLivre = new ArrayList<>();
        initDrawer();
        SalleService sService =new SalleService();
        livreService servicelivre =new livreService();
        Anchorlist=sService.getSalleContainer(null);
        AnchorlistLivre=servicelivre.getLivreContainer(null);
        Man.getChildren().clear();
        Man.getChildren().addAll(Anchorlist);
        manLivre.getChildren().clear();
        manLivre.getChildren().addAll(AnchorlistLivre);
       
        SPane.setFitToWidth(true);
        SpaneLivre.setFitToWidth(true);
         Platform.runLater(() -> {
	try {
		Method lc = JFXMasonryPane.class.getDeclaredMethod("layoutChildren");
		Field matrix = JFXMasonryPane.class.getDeclaredField("matrix");
		//Field valid = JFXMasonryPane.class.getDeclaredField("valid");
		//valid.setAccessible(true);
		matrix.setAccessible(true);
		lc.setAccessible(true);

		matrix.set(Man, null);
		//valid.set(Man, false);
		lc.invoke(Man);
	} catch (Exception e) {
		e.printStackTrace();
	}
});
         Platform.runLater(() -> {
	try {
		Method lc = JFXMasonryPane.class.getDeclaredMethod("layoutChildren");
		Field matrix = JFXMasonryPane.class.getDeclaredField("matrix");
		//Field valid = JFXMasonryPane.class.getDeclaredField("valid");
		//valid.setAccessible(true);
		matrix.setAccessible(true);
		lc.setAccessible(true);

		matrix.set(manLivre, null);
		//valid.set(Man, false);
		lc.invoke(manLivre);
	} catch (Exception e) {
		e.printStackTrace();
	}
});
         
        
          toutlivre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer(null);
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
          toutEconomie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer("economie");
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
          toutMedcine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer("medecine");
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
          toutArt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer("art");
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
          ToutSport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer("sport");
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
          toutScience.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer("scientifique");
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
          toutInformatique.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> AnchorlistLivre =servicelivre.getLivreContainer("informatique");
                manLivre.getChildren().clear();
                manLivre.getChildren().addAll(AnchorlistLivre);
                // Button was clicked, do something...
            }
        });
         
         
         btnAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> Anchorlist=sService.getSalleContainer(null);
                Man.getChildren().clear();
                Man.getChildren().addAll(Anchorlist);
                // Button was clicked, do something...
            }
        });
         
         btnCinema.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> Anchorlist=sService.getSalleContainer("Cinema");
                Man.getChildren().clear();
                Man.getChildren().addAll(Anchorlist);
                // Button was clicked, do something...
            }
        });
          btnThea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> Anchorlist=sService.getSalleContainer("Théathre");
                Man.getChildren().clear();
                Man.getChildren().addAll(Anchorlist);
                // Button was clicked, do something...
            }
        });
            btnFormation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> Anchorlist=sService.getSalleContainer("Formation");
                Man.getChildren().clear();
                Man.getChildren().addAll(Anchorlist);
                // Button was clicked, do something...
            }
        });
             btnConf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnchorPane> Anchorlist=sService.getSalleContainer("Conférence");
                Man.getChildren().clear();
                Man.getChildren().addAll(Anchorlist);
                // Button was clicked, do something...
            }
        });
         
         Platform.runLater(()->SPane.requestLayout());
        
	
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    private void initDrawer() {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
            
            VBox toolbar = loader.load();
            
         
            drawer.setSidePane(toolbar);
            //ToolbarController controller = loader.getController();
           // controller.setBookReturnCallback(this);
        } catch (IOException ex) {
          // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }
    
    
    public void reload()
    {
    
    
    
    
    }
    
    
  
    
}
