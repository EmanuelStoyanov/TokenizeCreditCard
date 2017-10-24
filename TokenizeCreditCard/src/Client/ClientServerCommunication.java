/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author emo
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import MainClasses.Message;

public final class ClientServerCommunication {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private final int PORT = 8081;

    public ClientServerCommunication() {
        try {
            openSocket();
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    public void openSocket() {
        try {
            socket = new Socket((String) null, PORT);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No server with port "
                    + PORT + " was found!\nTerminating...",
                    "Server not found",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }
    }

    public void sendMessage(Object obj, String action) {
        try {
            outputStream.writeObject(new Message(obj, action));
        } catch (IOException ex) {
            Logger.getLogger(ClientServerCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object recieveObject() throws ClassNotFoundException {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (IOException ex) {
            System.out.println(ex);

        return null;
    }
}
}
