/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Duroy Mehdi
 */
public class pdf {
     public void creation()
    { 
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        fc.setTitle("save tp pdf");
        fc.setInitialFileName("mescollections.pdf");
        Stage window = new Stage();
try{
               
            File file=fc.showSaveDialog(window);
            String str = file.getAbsolutePath();
            if(file!=null)
            {
                OutputStream fil = new FileOutputStream(new File(str));


            Document document = new Document();

            PdfWriter.getInstance(document, fil);


            document.open();
            
        
            Paragraph paragraph = new Paragraph("  ");
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragraph);
       
            document.close();
            fil.close();

                
            }
    
           //TextField imageSource = new TextField("file:///C:/Users/raed/Desktop/joker/3GoDyyxS1Vw.jpg");
           
           

        } catch (Exception e) {


            e.printStackTrace();

        }

    }
    
}
