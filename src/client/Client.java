package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String address;
    private int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Client(String ad, int p) {
        this.address = ad;
        this.port = p;

        try {
            System.out.println("Socket du client");
            this.socket = new Socket(ad, p);
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            Thread threadClientSend = new Thread(new ClientSend(this, this.socket, this.out));
            threadClientSend.start();
            Thread threadClientReceive = new Thread(new ClientReceive(this, this.socket));
            threadClientReceive.start();
        } catch (UnknownHostException var5) {
            System.out.println("Erreur client UnknownHostException");
            var5.printStackTrace();
        } catch (IOException var6) {
            System.out.println("Erreur client IOException");
            var6.printStackTrace();
        }

    }

    public void messageReceived(Message mess) {
        System.out.println("\nMessage reçu : " + mess);
    }

    public void sendMessage(Message mess, ObjectOutputStream out) {
        try {
            out.writeObject(mess);
            out.flush();
        } catch (IOException var4) {
            System.out.println("Probléme pour envoyer le message");
            var4.printStackTrace();
        }

    }

    public void disconnectedServer() {
        if (this.out != null || this.socket != null || this.in != null) {
            try {
                this.out.close();
                this.socket.close();
                this.in.close();
                System.exit(0);
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }
}
