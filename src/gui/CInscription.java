package gui;

import database.UserController;
import database.UserNew;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author dhondt lea
 */
public class CInscription implements Initializable {





    @FXML
    private Text actiontarget ;


    @FXML
    private TextField Pseudo ;

    @FXML
    private PasswordField InputPwd ;

    @FXML
    private TextField Mail;



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

    public TextField getMail()
    {
        return this.Mail ;
    }

    public void setMail (TextField Mail)
    {
        this.Mail = Mail ;
    }



    @FXML public void submitPressed (ActionEvent event) throws SQLException, IOException {
        UserController uc = new UserController();
        UserNew userNew = new UserNew();
        userNew.login = this.getPseudo().getText();
        userNew.password = this.getPwd().getText();
        userNew.email = this.getMail().getText();


        if (uc.UserAdd(userNew) == "inscrit"){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inscription");
            alert.setHeaderText("Votre inscription a bien été crée");
            alert.setContentText("Vous pouvez désormais vous connecter");

            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.OK) {
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                stage.setTitle("Bataille Navale - Connexion");
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ecran1_Connexion.fxml"))));
                stage.show();
            }
        }
        else {
            Alert dialogE = new Alert(AlertType.ERROR);
            dialogE.setTitle("Erreur de mail/login");
            dialogE.setContentText("L'adresse mail ou le nom d'utilisateur existe déja !");
            dialogE.showAndWait();
        }





    }

    @FXML public void retour (ActionEvent event) throws SQLException, IOException {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setTitle("Bataille Navale - Connexion");
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ecran1_Connexion.fxml"))));
        stage.show();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
