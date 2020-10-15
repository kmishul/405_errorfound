/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Folio
 */
public class server {
    public static ServerSocket server;
    public static final int port=8000;
    public static void main(String args[]) throws IOException
    {
        server=new ServerSocket(port);
        while(true)
        {
            Socket client=null;
            try
            {
                System.out.println("Waiting For Clients To Connect: ");
                client=server.accept();
                System.out.println("A new Client Connected: "+client);
                Thread t=new Thread(new Client_handler(client));
                t.start();
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}
