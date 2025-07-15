
package za.ac.tut.message;

public class Message {
    
    private String plain;
     private String encrypted;

    public Message() 
    {
          this.plain = "";
          this.encrypted = "";
    }

    public Message(String plain, String encrypted) {
        this.plain = plain;
        this.encrypted = encrypted;
    }
    
   

    public String getPlainMessage() {
        return plain;
    }

    public void setPlainMessage(String plain) {
        this.plain = plain;
    }

    public String getEncryptedMessage() {
        return encrypted;
    }

    public void setEncryptedMessage(String encrypted) {
        this.encrypted = encrypted;
    }
    
    
    
}
