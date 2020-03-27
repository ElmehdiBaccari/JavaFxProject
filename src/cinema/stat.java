/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

/**
 *
 * @author Mimi
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.pidev.service.FilmService;
import connexionbd.ConnexionBD;
import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class stat implements Initializable {

    ObservableList<PieChart.Data> stat=FXCollections.observableArrayList();

    ArrayList<String> libelle = new ArrayList<String>();
    ArrayList<Integer> quantiteDispo = new ArrayList<Integer>();
    @FXML
    private PieChart piechart;
    @FXML
    private BarChart<String, Integer> barchart;
    FilmService service_stat= new FilmService();
    
    private Connection connection;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          connection=ConnexionBD.getInstance().getCnx();
         
    try {
        
        
        Statement stm =  connection.createStatement();
        ResultSet rest=stm.executeQuery("SELECT AVG(rating) as nbr,film.nom as nom from rating JOIN film on rating.id_film=film.id group by id_film");
        
        while(rest.next())
        {
            libelle.add(rest.getString("nom"));
            quantiteDispo.add(rest.getInt("nbr"));
            stat.add(new PieChart.Data(rest.getString("nom"), rest.getInt("nbr")));
        }
    }
    catch (SQLException ex) {
        Logger.getLogger(stat.class.getName()).log(Level.SEVERE, null, ex);
    }
    piechart.setAnimated(true);
    piechart.maxHeight(1000);
    piechart.setData(stat);
    XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
    
    for (Entry<String,Integer> i: service_stat.getStat().entrySet() ) {
        String nom=i.getKey();
        int nbr=i.getValue();
        XYChart.Data<String, Integer> d = new XYChart.Data<>(nom, nbr);
        series1.getData().add(d);
    }
    
    barchart.getData().addAll(series1);
    
}
       
    }
    
    
