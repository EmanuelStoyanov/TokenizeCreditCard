/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

    protected int serverPort = 8081;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public Server(int port) {
        this.serverPort = port;
    }

    public void run() {
        openServerSocket();
        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println("Waiting..");
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            try {
                new Thread(new MessageHandle(clientSocket)).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port" + serverPort, e);
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8081)).start();
    }
}
