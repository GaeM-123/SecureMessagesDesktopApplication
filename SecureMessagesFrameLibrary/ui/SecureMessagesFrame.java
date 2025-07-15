package za.ac.tut.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import za.ac.tut.encryprion.MessageEncryptor;

public class SecureMessagesFrame extends JFrame {

    private JPanel mainPnl;
    private JPanel txtAreaPnl;
    private JPanel headingPnl;

    private JLabel headingLbl;

    private JTextArea plainMessageTxtArea;
    private JTextArea encryptedMessageTxtArea;

    private JScrollPane plainScrollPane;
    private JScrollPane encryptedScrollPane;

    // Menu bar and items
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openFileMenuItem;
    private JMenuItem encryptionMenuItem;
    private JMenuItem saveEncryptionMenuItem;
    private JMenuItem clearMenuItem;
    private JMenuItem exitMenuItem;

    public SecureMessagesFrame() {
        setTitle("Secure Messages");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        openFileMenuItem = new JMenuItem("Open Plain Message File");
        openFileMenuItem.addActionListener(new OpenBtnListener());

        encryptionMenuItem = new JMenuItem("Encrypt Message");
        encryptionMenuItem.addActionListener(new EncryptionBtnListener());

        saveEncryptionMenuItem = new JMenuItem("Save Encrypted Message");
        saveEncryptionMenuItem.addActionListener(new SaveBtnListener());

        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ClearBtnListener());

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitBtnListener());

        // Add items to menu
        fileMenu.add(openFileMenuItem);
        fileMenu.add(encryptionMenuItem);
        fileMenu.add(saveEncryptionMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(clearMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Main panel
        mainPnl = new JPanel(new BorderLayout());

        // Heading
        headingPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingLbl = new JLabel("Message Encryptor", SwingConstants.CENTER);
        headingLbl.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        headingLbl.setForeground(Color.BLUE);
        headingLbl.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        headingPnl.add(headingLbl);

        // Text Areas
        txtAreaPnl = new JPanel(new GridLayout(1, 2, 5, 5));

        plainMessageTxtArea = new JTextArea();
        plainMessageTxtArea.setEditable(false);
        plainMessageTxtArea.setLineWrap(true);
        plainMessageTxtArea.setWrapStyleWord(true);
        plainScrollPane = new JScrollPane(plainMessageTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        plainScrollPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Plain Message"));

        encryptedMessageTxtArea = new JTextArea();
        encryptedMessageTxtArea.setEditable(false);
        encryptedMessageTxtArea.setLineWrap(true);
        encryptedMessageTxtArea.setWrapStyleWord(true);
        encryptedScrollPane = new JScrollPane(encryptedMessageTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        encryptedScrollPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Encrypted Message"));

        txtAreaPnl.add(plainScrollPane);
        txtAreaPnl.add(encryptedScrollPane);

        mainPnl.add(headingPnl, BorderLayout.NORTH);
        mainPnl.add(txtAreaPnl, BorderLayout.CENTER);

        add(mainPnl);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void clearFields() {
        plainMessageTxtArea.setText("");
        encryptedMessageTxtArea.setText("");
    }

    // Open file listener
    private class OpenBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fChooser = new JFileChooser();
            int result = fChooser.showOpenDialog(SecureMessagesFrame.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File chosenFile = fChooser.getSelectedFile();

                try (BufferedReader reader = new BufferedReader(new FileReader(chosenFile))) {
                    plainMessageTxtArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SecureMessagesFrame.this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Encrypt button listener
    private class EncryptionBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String plainText = plainMessageTxtArea.getText();

            if (!plainText.isEmpty()) {
                String encryptedText = MessageEncryptor.encrypt(plainText);
                encryptedMessageTxtArea.setText(encryptedText);
            } else {
                JOptionPane.showMessageDialog(SecureMessagesFrame.this, "No plain message to encrypt", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Save encrypted message listener
    private class SaveBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String encryptionText = encryptedMessageTxtArea.getText();

            if (!encryptionText.isEmpty()) {
                JFileChooser fileChooser = new JFileChooser();
                int opt = fileChooser.showSaveDialog(SecureMessagesFrame.this);

                if (opt == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(encryptionText);
                        JOptionPane.showMessageDialog(SecureMessagesFrame.this, "Encrypted message saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearFields();
                    } catch (IOException ioe) {
                        JOptionPane.showMessageDialog(SecureMessagesFrame.this, "Failed to save file: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(SecureMessagesFrame.this, "Encrypted message area is empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Clear button listener
    private class ClearBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            clearFields();
        }
    }

    // Exit button listener
    private class ExitBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
}
