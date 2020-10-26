/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Req_Res;
import Admin.*;
import User.*;
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

import java.io.IOException;

import java.io.IOException;

import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Folio
 */

public class Req_Res implements Serializable{
    public final String ip="localhost";
    public final int port=8806;

    public final Socket socket = new Socket(ip,port);
    ObjectOutputStream OOS1=new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream OIS1=new ObjectInputStream(socket.getInputStream());
    DataOutputStream DOS1=new DataOutputStream(socket.getOutputStream());
    DataInputStream DIS1=new DataInputStream(socket.getInputStream());
    String s;

    //private Object OOS;
    public ObjectOutputStream getObjectOutputStream()
    {
        return OOS1;
    }
    public ObjectInputStream getObjectInputStream()
    {
        return OIS1;
    }
    public DataInputStream getdataInputStream()
    {
        return DIS1;
    }
    public Socket getSocket()
    {
        return socket;
    }
    
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
    public String sendUserLogin(UserDetail user) throws IOException{
        System.out.println("reached senduserlogin in req_ress");
        DOS1.writeUTF("User Login");
        DOS1.flush();
        OOS1.writeObject(user);
        OOS1.flush();
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
    public String addtrain(ViewTrain train) throws IOException{
        DOS1.writeUTF("Add Train");
        OOS1.writeObject(train);
        s=DIS1.readUTF();
        System.out.println(s);
        return s;
    }

    public void removeTrain(ViewTrain remtr)throws IOException{
        DOS1.writeUTF("Remove Train");
        OOS1.writeObject(remtr);
        //s=DIS1.readUTF();
        //System.out.println(s);
        //return s;
    }

    public String canceltrain(CancelTrain train) throws IOException{
        DOS1.writeUTF("Cancel Train");
        OOS1.writeObject(train);
        s=DIS1.readUTF();
        return s;
    }

    public String uncanceltrain(CancelTrain train) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DOS1.writeUTF("Uncancel Train");
        OOS1.writeObject(train);
        s=DIS1.readUTF();
        return s;
    }

        

    public void viewTrains() throws IOException{
    
       DOS1.writeUTF("View Trains");
       
    }
    public void passDetails() throws IOException{
    
       DOS1.writeUTF("Pass Details");
       
    }
    public void reserveseat() throws IOException{
    
       DOS1.writeUTF("Reserve Seat");
       
    }
    public void travelInfo() throws IOException{
    System.out.println("travel method check\n");
                
       DOS1.writeUTF("Travel Info");
       DOS1.flush();
       System.out.println("travel method after\n");
    }
    public void searchtrain(String s1,String s2) throws IOException, ClassNotFoundException{
        DOS1.writeUTF("Search Train");
        DOS1.writeUTF(s1);
        DOS1.writeUTF(s2);
        //s=(String) OIS1.readObject();
        //return s;
    }
}
