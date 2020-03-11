/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.UserController;
import database.UserNew;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.MainServer;

/**
 *
 * @author dhond
 */
public class CListJoueurs implements Initializable{
    
    

    @FXML
    private Text actiontarget ;


    @FXML
    private ListView<String> listView;
    
    
    private ObservableList<String> items;

    
    @FXML public void quitter (ActionEvent event) throws SQLException, IOException {
       
       

       final Node source = (Node) event.getSource();
       final Stage stage = (Stage) source.getScene().getWindow();
       stage.close();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems((ObservableList<String>) Main.client.getListPseudos());
    }

    /**
     * @return the list
     */
    public ListView getList() {
        return this.listView;
    }

    /**
     * @param list the list to set
     */
    public void setList(ListView list) {
        
        this.listView = list;
    }
    
}
