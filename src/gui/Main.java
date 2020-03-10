package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.UnknownHostException;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ecran1_Connexion.fxml"));
        AnchorPane root = loader.load();
        primaryStage.setTitle("Bataille Navale - Connexion");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public static void main(String[] args) throws UnknownHostException {

        launch(args);

    }

    private static void printUsage() {
        System.out.println("java client.Client <address> <port>");
        System.out.println("\t<address>: server's ip address");
        System.out.println("\t<port>: server's port");
    }
}
