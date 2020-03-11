package gui;

import database.UserController;
import database.UserCon;
import client.Client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dhondt lea
 */
public class CAuthentification implements Initializable {



    private CListJoueurs CListJoueurs ;

    @FXML
    private Text actiontarget ;


    @FXML
    private TextField Pseudo ;

    @FXML
    private PasswordField InputPwd ;

    public PasswordField getPwd()
    {
        return this.InputPwd ;
    }

    public void setPwd(PasswordField InputPwd)
    {
        this.InputPwd = InputPwd ;
    }

    public TextField getPseudo()
    {
        return this.Pseudo ;
    }

    public void setPseudo (TextField Pseudo)
    {
        this.Pseudo = Pseudo ;
    }



    @FXML public void submitPressed (ActionEvent event) throws SQLException, IOException {
        UserController uc = new UserController();
        UserCon user = new UserCon();
        user.login = this.getPseudo().getText();
        user.password = this.getPwd().getText();

        if (uc.CheckUser(user)){
            

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            stage.setTitle("Bataille Navale - Joueurs connectés");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ecran2_joueursconnectés.fxml"))));
            stage.show();
            
            Main.client.sendConnexionMessage(user.login);

        }
        else{
            Alert dialogE = new Alert(Alert.AlertType.ERROR);
            dialogE.setTitle("Erreur de login/password");
            dialogE.setContentText("Le nom d'utilisateur/mot de passe n'existe pas!!");
            dialogE.showAndWait();
        }
    }

    @FXML public void inscrire (ActionEvent event) throws SQLException, IOException {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setTitle("Bataille Navale - Inscription");
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ecran1_Inscription.fxml"))));
        stage.show();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}