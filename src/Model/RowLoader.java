/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.RowNotificationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class RowLoader extends Pane {

    private Pane row;

    ///////Construtores  //  Constructos /////////
    public RowLoader() {
        this.row = new Pane();

    }

    public Pane loadPane(String fileName) {

        try {
            
            row = new FXMLLoader().load(getClass().getResource("/View/"+fileName));
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel carregar as notificaçoes:"+ex);
        }
        
        return row;
    }
    
    public Pane loadRow(Notification not){
        
        
        
        try {
            
            RowNotificationController controller = new RowNotificationController();
            
            controller.setNotification(not);
            controller.load();
            
            row = new FXMLLoader().load(getClass().getResource("/View/RowNotification.fxml"));
          
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel carregar as notificaçoes:"+ex);
        }
        
        return row; 
    }

}
