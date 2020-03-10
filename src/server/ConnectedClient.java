package server;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectedClient implements Runnable {
    private int IdCounter = 0;
    private int Id;
    private String Username;
    private String Pwd;
    private Server server;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream Out;

    public ConnectedClient(Server se, Socket so) {
        this.server = se;
        this.socket = so;
        this.Id = this.IdCounter++;
        System.out.println("Constructeur connectedclient, id= " + this.Id);

        try {
            this.Out = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        System.out.println("Nouvelle connection, id= " + this.Id);
    }

    public void run() {
        try {
            this.in = new ObjectInputStream(this.socket.getInputStream());
            boolean isActive = true;

            while(isActive) {
                Message mess = (Message)this.in.readObject();
                if (mess == null) {
                    isActive = false;
                    this.server.disconnectedClient(this);
                } else {
                    System.out.println("message recu");
                    System.out.println("run() connectedclient, id= " + this.Id);
                    mess.setSender(String.valueOf(this.Id));
                    this.server.broadcastMessage(mess, this.Id);
                }
            }
        } catch (IOException var3) {
            System.out.println("probleme inputStream");
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

    }

    public void sendMessage(Message mess) {
        try {
            this.Out.writeObject(mess);
            this.Out.flush();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public boolean ConnectedServer(String password, String Username){
        return true;
    }

    public void closeClient() {
        try {
            this.in.close();
            this.Out.close();
            this.socket.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }
}

