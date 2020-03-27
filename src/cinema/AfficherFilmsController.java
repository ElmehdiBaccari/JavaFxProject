/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;


import com.jfoenix.controls.JFXMasonryPane;
import com.pidev.entity.Film;
import com.pidev.service.FilmService;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mimi
 */
public class AfficherFilmsController implements Initializable {

    @FXML
    private JFXMasonryPane women;
    FilteredList<Film> filteredData;
    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<AnchorPane> Anchorlist = new ArrayList<>();
        FilmService sService =new FilmService();
        try {
            Anchorlist=sService.getFilmButton();
        } catch (IOException ex) {
            Logger.getLogger(AfficherFilmsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        women.getChildren().clear();
        System.out.println(Anchorlist);
        women.getChildren().addAll(Anchorlist);
        
        
        
        scroll.setFitToWidth(true);
         Platform.runLater(() -> {
	try {
		Method lc = JFXMasonryPane.class.getDeclaredMethod("layoutChildren");
		Field matrix = JFXMasonryPane.class.getDeclaredField("matrix");
		//Field valid = JFXMasonryPane.class.getDeclaredField("valid");
		//valid.setAccessible(true);
		matrix.setAccessible(true);
		lc.setAccessible(true);

		matrix.set(women, null);
		//valid.set(Man, false);
		lc.invoke(women);
	} catch (Exception e) {
		e.printStackTrace();
	}
});
        // TODO
    }    

    
   
    
}
