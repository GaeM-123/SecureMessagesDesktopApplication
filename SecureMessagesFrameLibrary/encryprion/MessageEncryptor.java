
package za.ac.tut.encryprion;


public class MessageEncryptor {
    
    private static final int SHIFT = 3; 

    public static String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();
        for (char i : plainText.toCharArray()) {
            if (Character.isLetter(i)) {
                char base = Character.isUpperCase(i) ? 'A' : 'a';
                encryptedText.append((char) ((i - base + SHIFT) % 26 + base));
            } else {
                encryptedText.append(i); 
            }
        }
        return encryptedText.toString();
    }
    
}


