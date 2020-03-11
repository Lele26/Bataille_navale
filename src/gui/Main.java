package gui;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.UnknownHostException;
import java.util.List;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
   public static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception{
        List<String> arguments = this.getParameters().getRaw();
        if (arguments.size() != 2) {
            printUsage();
        } else {
            String address = arguments.get(0);
            int port = Integer.parseInt(arguments.get(1));
            Client client = new Client(address, port);
            this.client = client;
        }
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
        System.out.println("java gui.MainGui <address> <port>");
        System.out.println("\t<address>: server's ip address");
        System.out.println("\t<port>: server's port");
    }
}
