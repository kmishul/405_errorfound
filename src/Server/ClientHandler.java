/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.Requests.UserSignupRequest;
import User.UserSignup;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmish
 */
public class ClientHandler implements Runnable{
    private final Socket client;
    private String user_name;
    private PreparedStatement st;
    private ObjectOutputStream OOS;
    private ObjectInputStream OIS;
    private DataOutputStream DOS;
    private DataInputStream DIS;
    
    ClientHandler(Socket client) throws IOException{
        this.client=client;
        OOS=new ObjectOutputStream(client.getOutputStream());
        OIS=new ObjectInputStream(client.getInputStream());
        DOS=new DataOutputStream(client.getOutputStream());
        DIS=new DataInputStream(client.getInputStream());
        System.out.println("\n done1");
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String request;
        System.out.println("\n done2");
        try {
            System.out.println("\n done3");
            request=DIS.readUTF();
            if(request.equals("User SignUp")){
                UserSignup user=(UserSignup)OIS.readObject();
                //String userid=user.UserId;
                UserSignupRequest userr=new UserSignupRequest(user);
                if(userr.adduser()){
                    DOS.writeUTF("valid");
                    System.out.println("valid check\n");
                }
                else
                    DOS.writeUTF("Error: may be username already exist,try another one");
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            //System.out.println(e);
            System.out.println("\n 1");
            System.out.println(e);
        }
        
        
        
    }
    
    
    
}
