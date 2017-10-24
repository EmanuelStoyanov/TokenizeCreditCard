/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import MainClasses.Database;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import MainClasses.CreditCard;
import MainClasses.Message;
import MainClasses.User;
import Comparators.CompareByCardNumber;
import Comparators.CompareByToken;

public class MessageHandle implements Runnable {

    private Socket clientSocket = null;
    private ObjectInputStream inputStream;
    private Database db;
    private Session session;

    public MessageHandle(Socket clientSocket) throws IOException {
        this.db = new Database();
        try {
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("IOException.");
            System.exit(1);
        }
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Connection Established!");
        try {
            while (true) {
                try {
                    Message message = (Message) inputStream.readObject();
                    handle(message);
                } catch (EOFException ex) {
                    System.out.println("EOFException");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        }
    }

    private void handle(Message message) throws IOException{
        String action = message.getAction();
        Object obj = message.getObject();

            switch (action) {
                case "Register":
                    db.add((User) obj);
                    break;
                case "SignIn":
                    User user = (User) obj;
                    session = new Session(user);
                    sendSession();
                    break;
                case "Tokenize":
                    Database updatedDatabase = session.registerCard((CreditCard) obj, db);
                    db = updatedDatabase;
                    break;
                case "Untokenize":
                    String card = session.getCardByToken((String) obj);
                    sendObject(card);
                    break;
                case "Export By Tokens":
                    session.exportByComparator(new CompareByToken(), new File("CardsByToken.txt"));
                    break; 
                case "Export By Credit Card":
                    session.exportByComparator(new CompareByCardNumber(), new File("CardsByCard.txt"));
                    break;
            }

    }

    private void sendObject(Object obj) {
        System.out.println("The obj is : " + obj);
        try {
            ObjectOutputStream outputStream
                    = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(obj);
        } catch (IOException ex) {
            System.out.println("Io exc");
            System.out.println(ex);
        }
    }
    
    private void sendSession() {
        System.out.println("Sending Session");
        sendObject((Session) session);
        System.out.println("Session sent");
    }
}
