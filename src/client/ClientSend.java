package client;


import common.Message;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable {
    private Client client;
    private Socket socket;
    private ObjectOutputStream out;

    public ClientSend(Client c, Socket so, ObjectOutputStream o) {
        this.client = c;
        this.socket = so;
        this.out = o;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("Votre message >> ");
            String m = sc.nextLine();
            Message mess = new Message("client", m);
            this.client.sendMessage(mess, this.out);
        }
    }
}
