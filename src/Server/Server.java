/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author kmish
 */
public class Server {
    public static ServerSocket serverSocket;
    public static final int port=8806;
    public static void main(String args[]) throws IOException
    {
        new DBConnect();
        serverSocket=new ServerSocket(port);
        while(true)
        {
            Socket client=null;
            try
            {
                System.out.println("Waiting For Clients To Connect: ");
                client=serverSocket.accept();
                System.out.println("A new Client Connected: "+client);
                Thread t=new Thread(new ClientHandler(client));
                t.start();
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
