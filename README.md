# Secure Messages Desktop Application

A Java Swing desktop application that allows users to encrypt plain text messages using a Caesar cipher encryption technique. This tool provides a simple interface to open message files, encrypt the contents, and save the result.

## 🔐 Features

- Load plain messages from text files
- Optionally type messages manually
- Encrypt messages using Caesar cipher (shift by 3)
- Save encrypted messages to a file
- View plain and encrypted messages side by side
- Clear/reset the interface
- Simple and intuitive menu-based GUI

## 🛠️ Technologies Used

- Java SE (JDK 8+)
- Java Swing (GUI framework)
- File I/O (BufferedReader, FileWriter)
- Object-Oriented Programming principles

## 📂 Project Structure

SecureMessagesApp/
├── SecureMessagesFrameLibrary/
  ├── ui/SecureMessagesFrame.java
  ├── message/Message.java
  └── encryprion/MessageEncryptor.java
├── README.md
├── SecureMessagesApp/
  └── SecureMessagesApp.java
