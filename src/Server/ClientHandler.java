/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Admin.AdminLogin;
import Server.Requests.AdminLoginRequest;
import Server.Requests.UserLoginRequest;
import Admin.AddTrain;
import Server.Requests.AddTrainRequest;
import Server.Requests.UserSignupRequest;
import User.UserLogin;
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
                else{
                    DOS.writeUTF("Error: may be username already exist,try another one");
                }
            
            }
            if(request.equals("User Login")) {
                System.out.println("reached client handler for login");
                UserLogin userl=(UserLogin)OIS.readObject();
                UserLoginRequest userlr=new UserLoginRequest(userl);
                if(userlr.checklogininfo()){
                    DOS.writeUTF("validlogindetails");
                    System.out.println("Valid USer Login");
                }
                else{
                    DOS.writeUTF("Wrong credentials");
                }
            }
            if(request.equals("Admin Login")) {
                System.out.println("reached client handler for adminlogin");
                AdminLogin adminl=(AdminLogin)OIS.readObject();
                AdminLoginRequest userlr=new AdminLoginRequest(adminl);
                if(userlr.checkadminlogininfo()){
                    DOS.writeUTF("validlogindetailsforadmin");
                    System.out.println("Valid Admin Login");
                }
                else{
                    DOS.writeUTF("Wrong credentials");
                    //DOS.writeUTF("Error: this username already exists,try using another one");   
                }
            }
            if(request.equals("Add Train")){
                AddTrain train=(AddTrain)OIS.readObject();
                AddTrainRequest trainn=new AddTrainRequest(train);
                if(trainn.addtrain()){
                    DOS.writeUTF("valid");
                    System.out.println("valid check\n");
                }
                else{
                    DOS.writeUTF("Error:this train num already exist");
                    System.out.println("unvalid check\n");
                }
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
