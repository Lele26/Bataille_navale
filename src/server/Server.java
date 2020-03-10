package server;

import common.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
    private int port;
    public static List<ConnectedClient> clients;

    public Server(int p) {
        this.port = p;
        this.clients = new ArrayList();
        System.out.println("Nouveau thread server");
        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }

    public void addClient(ConnectedClient newClient) {
        Message mess = new Message("Server", newClient.getId() + "vient de se connecter");
        Iterator var4 = this.clients.iterator();

        while(var4.hasNext()) {
            ConnectedClient client = (ConnectedClient)var4.next();
            client.sendMessage(mess);
        }

        this.clients.add(newClient);
    }

    public void broadcastMessage(Message mess, int Id) {
        System.out.println("bcast");
        Iterator var4 = this.clients.iterator();

        while(var4.hasNext()) {
            ConnectedClient client = (ConnectedClient)var4.next();
            System.out.println("serveur,id= " + Id);
            if (client.getId() != Id) {
                System.out.println("envoi à " + Id);
                client.sendMessage(mess);
            }
        }

    }

    public void disconnectedClient(ConnectedClient discClient) {
        Iterator var3 = this.clients.iterator();

        while(var3.hasNext()) {
            ConnectedClient client = (ConnectedClient)var3.next();
            client.sendMessage(new Message("server", "Le client " + discClient.getId() + " nous a quitté"));
        }

    }

    public int getPort() {
        return this.port;
    }

    public int getNumClients() {
        return this.clients.size();
    }
}

