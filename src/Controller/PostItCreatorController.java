/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostItDAO;
import DAO.TypeDAO;
import DAO.UserDAO;
import Main.MainPostItCreator;
import Main.MainPostItScreen;
import Model.PostIt;
import Model.Type;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PostItCreatorController implements Initializable {

  
    
    @FXML
    private JFXDatePicker dpDate;

    @FXML
    private JFXTimePicker tmTime;

    @FXML
    private JFXToggleButton tgSound;

    @FXML
    private ComboBox<Type> cbTypes;

    @FXML
    private Rectangle recType;

    @FXML
    private JFXTextArea txtBody;

    @FXML
    private TextField txtTitle;

    @FXML
    private Button btSound;

    @FXML
    private Label lblSound;
    
    private boolean soundIsVisible = false;
    
    private File sound;
    
    private LocalDate date;
    
    private LocalTime time;
    
    private PostItDAO dao = new PostItDAO();
    
    private TypeDAO typeDao = new  TypeDAO();
    
    @FXML
    public void close() {

       MainPostItCreator.getWindow().close();
        
    }

    @FXML
    public void create() {
       
        PostIt postIt = new PostIt();
                

        postIt.setTitle(txtTitle.getText());
        postIt.setBody(txtBody.getText());
        postIt.setMusic(sound);
        postIt.setType(cbTypes.getSelectionModel().getSelectedItem());
        postIt.setUser(UserDAO.getUser());
        postIt.setWarned(false);
        
        date = dpDate.getValue();
        time = tmTime.getValue();
        
        postIt.setScheduledDay(date,time);
        
        if(dao.Insert(postIt)){
            
            JOptionPane.showMessageDialog(null, "Criado");
            
        }
        
        PostItDAO.setPostIt(postIt);
         
    }

    @FXML
    public void look() {

        try {
            
            if(MainPostItScreen.getWindow() != null){
                MainPostItScreen.getWindow().close();
            }
            
            MainPostItScreen postItScreen = new MainPostItScreen();
            
            postItScreen.start(new Stage());
            
            PostIt postIt = new PostIt();

                postIt.setTitle(txtTitle.getText());
                postIt.setBody(txtBody.getText());
                postIt.setMusic(sound);
                postIt.setType(cbTypes.getSelectionModel().getSelectedItem());
                postIt.setUser(UserDAO.getUser());
                postIt.setWarned(false);

                if(dpDate != null && tmTime != null){
                    
                    date = dpDate.getValue();
                    time = tmTime.getValue();

                    postIt.setScheduledDay(date,time);
                }

                PostItDAO.setPostIt(postIt);
            
            PostItScreenController.loadPostIt(postIt);
            
        } catch (Exception ex) {
            Logger.getLogger(PostItCreatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void changeSoundVisibility() {

        tgSound.fontProperty().get();
        
        soundIsVisible = !soundIsVisible;
            
            btSound.setVisible(soundIsVisible);
            lblSound.setVisible(soundIsVisible);
            
        if(soundIsVisible == false){
            
            sound = null;
            lblSound.setText("Se nao escolher será selecionado o padrao");
            
        }        
    }
    
    
    @FXML
    void soundChoose() {

        ExtensionFilter soundFilter = new ExtensionFilter("Sounds", "*.wav", "*.mp3");
        FileChooser SoundChooser = new FileChooser();
        SoundChooser.getExtensionFilters().add(soundFilter);
        
        sound = SoundChooser.showOpenDialog(new Stage());
        
        if (sound != null) {

            lblSound.setText(sound.getName());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillComboBox();
        
        cbTypes.valueProperty().addListener((ov, t, t1) -> {
        
             String color = cbTypes.getSelectionModel().getSelectedItem().getPrimaryColor(); // change the color of Rectangle: recType when change the notification //alterar a cor do retângulo: recType ao alterar o tipo de notificação
                
             recType.setStyle("-fx-fill: "+color+";");
            
        });
        
    }    

    private void fillComboBox() {
        
         ArrayList<Type> arTypes = new ArrayList<>();
         
         for(Type type:  typeDao.selectAllFromUser(UserDAO.getUser().getId().intValue())){
             
             arTypes.add(type);
             
         }

        ObservableList<Type> obTypes = FXCollections.observableArrayList(arTypes);    

        cbTypes.setItems(obTypes);  
    
    }
    
}
