package common;

import java.io.Serializable;

public class Message implements Serializable{
    private String sender;
    private String content;

    public Message (String s, String c) {
        this.sender = s;
        this.content = c;
    }

    public String toString() {
        return sender+": "+content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}

