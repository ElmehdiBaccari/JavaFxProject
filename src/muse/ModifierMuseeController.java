/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muse;



import com.pidev.entity.Musee;
import com.pidev.service.MuseeService;
import connexionbd.loadwindow;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duroy Mehdi
 */
public class ModifierMuseeController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txttelephone;
    @FXML
    private TextField txtpersonnes;
    @FXML
    private TextField txtprix;
    private DatePicker datearrive;

    /**
     * Initializes the controller class.
     */
       Musee ab;
    @FXML
    private TextField txtid;
    @FXML
    private AnchorPane aa;
    public void setMusee(Musee m){
    ab=m;
   //this.txtid.setText(ab.getId()+"");
      this.txtnom.setText(ab.getNom());
      this.txtprenom.setText(ab.getPrenom());
      this.txtemail.setText(ab.getEmail());
      this.txttelephone.setText(ab.getTelephone()+"");
       this.txtpersonnes.setText(ab.getPersonne()+"");
         this.txtprix.setText(ab.getFee()+"");
      
      
      
}

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("salem");
        // TODO
    }  
    

    
  

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
           MuseeService sa=new MuseeService();
           Musee m1 = new Musee(txtnom.getText(),txtprenom.getText(), txtemail.getText(), Integer.parseInt(txttelephone.getText()), Integer.parseInt(txtpersonnes.getText()), Float.parseFloat(txtprix.getText()));
        Musee m=new Musee();
        m.setNom(txtnom.getText());
         m.setPrenom(txtprenom.getText());
           m.setEmail(txtemail.getText());
              m.setTelephone(Integer.valueOf(txttelephone.getText()));
              m.setPersonne(Integer.valueOf(txtpersonnes.getText()));
                 m.setFee(Float.valueOf(txtprix.getText()));
             //     m.setArrive(Date.valueOf(datearrive.getValue()));
                 
                        
            
 
            sa.update(m1,ab.getId());
         
            
Stage s =(Stage) aa.getScene().getWindow();
s.close();
           
      
      
           
    }
}
      
    
         
    
      
        
    
    

