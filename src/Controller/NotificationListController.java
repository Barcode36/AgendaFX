/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainNotificationScreen;
import Model.Notification;
import Services.Notify;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class NotificationListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<Pane> listNotification;
    
    private LiteNotificationController notificationController = new LiteNotificationController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loadNotications(ArrayList<Notification> notifications) {

        for (Notification notification : notifications) {

            try {
                System.out.println("começei");
                Pane fxmlPane = FXMLLoader.load(getClass().getResource("/View/LiteNotification.fxml"));
                Pane pane = new Pane();
                pane.getChildren().setAll(fxmlPane);
                
                notificationController.load(notification);
                
                ArrayList<Pane> alNotifications = new ArrayList<>();
                
                pane.setOnMouseClicked((t) -> {
                    
                    try {
                        Notify.showNotification(notification);
                    } catch (Exception ex) {
                        Logger.getLogger(NotificationListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                alNotifications.add(pane);
                
                ObservableList<Pane> obsNotifications = FXCollections.observableArrayList(alNotifications);
                
                listNotification.getItems().clear();
                listNotification.setItems(obsNotifications);
                System.out.println("prenchida");
                       
            } catch (IOException ex) {
                Logger.getLogger(NotificationListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
