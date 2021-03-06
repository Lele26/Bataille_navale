package client;

import common.Message;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

/**
 * Classe encapsulant la tâche d'envoi d'un message
 */
public class ClientSend implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;
    private String message;
    private int type;
    
    /**
     * Constructeur de la classe ClientSend
     * @param socket Le socket à utiliser pour communiquer avec le serveur
     * @param out  L'objet "out" sur lequel nous allons écrire le message à envoyer
     * @param message Le message à envoyer
     */
    public ClientSend(Socket socket, ObjectOutputStream out, String message) {
        this.socket = socket;
        this.out = out;
        this.message = message;
        this.type = 1;
    }
    
    public ClientSend(Socket socket, ObjectOutputStream out, String message,int type) {
        this.socket = socket;
        this.out = out;
        this.message = message;
        this.type = type;
    }
    
    @Override
    public void run() {
        try {
            Message mess = new Message("client", this.message,this.type);
            out.writeObject(mess);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}