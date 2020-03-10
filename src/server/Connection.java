package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable {
    private Server server;
    private ServerSocket serverSocket;

    public Connection(Server s) {
        try {
            this.server = s;
            this.serverSocket = new ServerSocket(this.server.getPort());
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public void run() {
        try {
            while(true) {
                System.out.println("attente de nouveaux clients");
                Socket sockNewClient = this.serverSocket.accept();
                System.out.println("Connecté le client");
                ConnectedClient newClient = new ConnectedClient(this.server, sockNewClient);
                System.out.println("ajout du client dans la liste");
                newClient.setId(this.server.getNumClients());
                this.server.addClient(newClient);
                System.out.println("Thread client");
                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
                System.out.println("Thread client start");
            }
        } catch (IOException var4) {
            System.out.println("Classe Connection : Erreur IOException");
            var4.printStackTrace();
        }
    }
}

