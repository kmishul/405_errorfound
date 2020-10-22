/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Req_Res;


import User.UserLogin;
import User.UserSignup;
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
    ObjectOutputStream OOS=new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream OIS=new ObjectInputStream(socket.getInputStream());
    //private Object OOS;
    public Req_Res() throws IOException{
         System.out.println("Connected!");
    }
    public String sendUserSignup(UserSignup user) throws IOException{
    //public void sendUserSignup(UserSignup user,String s) throws IOException{
        OOS.writeUTF("User SignUp");
        OOS.writeObject(user);
        String s=OIS.readUTF();
        System.out.println(s);
        return s;
    }
    public void sendUserLogin(UserLogin user,String s) throws IOException{
        OOS.writeUTF("User Login");
        OOS.writeObject(user);
        s=OIS.readUTF();
    }
}