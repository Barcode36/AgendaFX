/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Model.Notification;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class RowNotificationController implements Initializable {

    @FXML
    private Rectangle typeColor;

    @FXML
    private Label title;

    @FXML
    private Label scheduled;

    private User user = UserDAO.getUser();

    private Notification notification;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        load();

    }

    public Rectangle getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(Rectangle typeColor) {
        this.typeColor = typeColor;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Label getScheduled() {
        return scheduled;
    }

    public void setScheduled(Label scheduled) {
        this.scheduled = scheduled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void load() {

        if (notification != null) {
            if (notification.getTypeColor() != null) {
                System.out.println(notification.getTypeColor());
                typeColor.setStyle(notification.getTypeColor());
            }
            if (notification.getTitle() != null) {
                title.setText(notification.getTitle());
            } else {
                title.setText("sem titulo");
            }
            if (notification.getScheduledDay() != null) {
                scheduled.setText(notification.getScheduledDate() + " as " + notification.getScheduledHour());
            } else {
                title.setText("sem data marcada");
            }
        }

    }

}
