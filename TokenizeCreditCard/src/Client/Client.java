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
import Frames.UserRegistrationFrame;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Client extends JFrame implements Runnable
{
   private JTextArea displayArea; 
   private String chatServer;
   private Socket client;
   private UserRegistrationFrame regFrame;

   public Client( String host ) throws IOException
   {
      super( "Client" );

      chatServer = host;
      regFrame = new UserRegistrationFrame();
      displayArea = new JTextArea(); 
      add( new JScrollPane( displayArea ), BorderLayout.CENTER );
      setSize( 300, 150 );
      setVisible( false );
   } 

   @Override
   public void run() 
   {
      try 
      {
         connectToServer();
         regFrame.setVisible(true);
      } 
      catch ( EOFException eofException ) 
      {
         displayMessage( "\nClient terminated connection" );
      }
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   } 

   private void connectToServer() throws IOException
   {      
      displayMessage( "Attempting connection\n" );

      client = new Socket( InetAddress.getByName( chatServer ), 8081 );

      displayMessage( "Connected to: " + 
         client.getInetAddress().getHostName() );
   } 

   private void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() 
            {
               displayArea.append( messageToDisplay );
            } 
         } 
      ); 
   } 
   
   public static void main( String args[] ) throws IOException
   {
         new Thread(new Client( "127.0.0.1" )).start();  
   } 
} 
