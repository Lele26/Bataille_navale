package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceive implements Runnable {
    private Client client;
    private Socket socket;
    private ObjectInputStream in;

    public ClientReceive(Client c, Socket s) {
        this.client = c;
        this.socket = s;
    }

    public void run() {
        try {
            this.in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        boolean isActive = true;

        while(isActive) {
            try {
                Message mess = (Message)this.in.readObject();
                if (mess != null) {
                    this.client.messageReceived(mess);
                } else {
                    isActive = false;
                }
            } catch (ClassNotFoundException var4) {
                var4.printStackTrace();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

        this.client.disconnectedServer();
    }
}
