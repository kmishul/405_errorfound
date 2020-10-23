/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Req_Res;


import Admin.AdminLogin;
import User.UserLogin;
import User.UserSignup;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Folio
 */
public class Req_Res implements Serializable{
    String ip="localhost";
    int port=8806;
    Socket socket = new Socket(ip,port);
    ObjectOutputStream OOS1=new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream OIS1=new ObjectInputStream(socket.getInputStream());
    DataOutputStream DOS1=new DataOutputStream(socket.getOutputStream());
    DataInputStream DIS1=new DataInputStream(socket.getInputStream());
    String s;
    //private Object OOS;
    public Req_Res() throws IOException{
         System.out.println("Connected!");
          
          
    }
    public String sendUserSignup(UserSignup user) throws IOException{
    //public void sendUserSignup(UserSignup user,String s) throws IOException{
       DOS1.writeUTF("User SignUp");
        OOS1.writeObject(user);
        s=DIS1.readUTF();
        System.out.println(s);
        return s;
    }
    public String sendUserLogin(UserLogin user) throws IOException{
        System.out.println("reached senduserlogin in req_ress");
        DOS1.writeUTF("User Login");
        OOS1.writeObject(user);
        s=DIS1.readUTF();
        System.out.println(s+"  :this is s");
        return s;
    }
    public String sendAdminLogin(AdminLogin admin) throws IOException{
        System.out.println("reached sendAdminlogin in req_ress");
        DOS1.writeUTF("Admin Login");
        OOS1.writeObject(admin);
        s=DIS1.readUTF();
        System.out.println(s+"  :this is s");
        return s;
    }
}
        