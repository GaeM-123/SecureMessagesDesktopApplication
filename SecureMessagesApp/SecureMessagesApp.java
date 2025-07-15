
package securemessagesapp;

import za.ac.tut.ui.SecureMessagesFrame;

import javax.swing.SwingUtilities;

public class SecureMessagesApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SecureMessagesFrame();
        });
    }

}
