/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author kmish
 */
public class ClientHandler implements Runnable{
    private final Socket client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String user_name;
    private PreparedStatement st;
    
    ClientHandler(Socket client){
        this.client=client;}
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
