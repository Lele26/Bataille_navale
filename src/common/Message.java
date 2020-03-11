/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;


public class Message implements Serializable {
    private String sender;
    private String content;
    private int type;
    
    public Message(String sender, String content)
    {
        this.sender = sender;
        this.content = content;
        this.type = 1;
    }
    
    public Message(String sender, String content,int type)
    {
        this.sender = sender;
        this.content = content;
        this.type = type;
    }
    
    public String toString() {
         return "Emetteur du message : " + this.sender + "\nContenu du message : " + this.getContent();
    }
 
    public void setSender(String id) {
        this.sender = id;
    }
    
    public String getSender() {
        return this.sender;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
