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
    //DataOutputStream DOS1=new DataOutputStream(socket.getOutputStream());
    //DataInputStream DIS1=new DataInputStream(socket.getInputStream());
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
    
    public Socket getSocket()
    {
        return socket;
    }
    
    public Req_Res() throws IOException{
         System.out.println("Connected!");
          
          
    }
    
    public String sendUserSignup(UserDetail user) throws IOException, ClassNotFoundException{
    //public void sendUserSignup(UserSignup user,String s) throws IOException{
       OOS1.writeObject("User SignUp");
        OOS1.writeObject(user);
        OOS1.flush();
        s=(String) OIS1.readObject();
        System.out.println(s);
        return s;
    }
    public String sendUserLogin(UserDetail user) throws IOException, ClassNotFoundException{
        System.out.println("reached senduserlogin in req_ress");
        OOS1.writeObject("User Login");
        OOS1.writeObject(user);
        OOS1.flush();
        s=(String) OIS1.readObject();
        
        System.out.println(s+"  :this is s");
        return s;
    }
    public String sendAdminLogin(Admindetail admin) throws IOException, ClassNotFoundException{
        System.out.println("reached sendAdminlogin in req_ress");
        OOS1.writeObject("Admin Login");
        OOS1.writeObject(admin);
        OOS1.flush();
        s=(String) OIS1.readObject();
        System.out.println(s+"  :this is s");
        return s;
    }
    public String addtrain(ViewTrain train) throws IOException, ClassNotFoundException{
       //OOS1.reset();
        OOS1.writeObject("Add Train");
        OOS1.writeObject(train);
        OOS1.flush();
        s=(String) OIS1.readObject();
        System.out.println(s+"1");
        return s;
    }

    public String removeTrain(ViewTrain remtr)throws IOException, ClassNotFoundException{
        OOS1.writeObject("Remove Train");
        OOS1.writeObject(remtr);
        OOS1.flush();
        String s=(String) OIS1.readObject();
       return s;
        //s=DIS1.readUTF();
        //System.out.println(s);
        //return s;
    }

    public String canceltrain(ViewTrain train) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Cancel Train");
        OOS1.writeObject(train);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }

    public String uncanceltrain(ViewTrain train) throws IOException, ClassNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        OOS1.writeObject("Uncancel Train");
        OOS1.writeObject(train);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }

        

    public String viewTrains() throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("View Trains");
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
       
    }
    
     public String giveDiscount() throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Discounts");
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
       
    }
     
    public String addseatfc(ViewTrain train) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Add FC");
       OOS1.writeObject(train);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
       
    }
     public String addseatsc(ViewTrain train) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Add SC");
       OOS1.writeObject(train);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
    }
      public String addseatlc(ViewTrain train) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Add SLC");
       OOS1.writeObject(train);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
    }
    public String rerouteTrain(ViewTrain train) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Reroute Train");
       OOS1.writeObject(train);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
    }
    public String seatavail(String tnum) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Seat Avail");
       OOS1.writeObject(tnum);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
    }
     public String cancelbooking(String pnr) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Cancel Booking");
        OOS1.writeObject(pnr);
        OOS1.flush();
         s=(String) OIS1.readObject();
       return s;
       
    }
    public String passDetails() throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Pass Details");
       OOS1.flush();
       String s=(String) OIS1.readObject();
       return s;
    }
    public String reserveseat(PassDetail pass) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Reserve Seat");
       OOS1.writeObject(pass);
       OOS1.flush();
        s=(String) OIS1.readObject();
         return s;       
       
    }
    public String travelInfo() throws IOException, ClassNotFoundException{
    System.out.println("travel method check\n");
                
       OOS1.writeObject("Travel Info");
       OOS1.flush();
       s=(String) OIS1.readObject();
        System.out.println("travel method after\n");
        return s;
       }
    public String tickets() throws IOException, ClassNotFoundException{
    
                
       OOS1.writeObject("Tickets");
       OOS1.flush();
       s=(String) OIS1.readObject();
        return s;
    }
    public String searchtrain(String s1,String s2) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Search Train");
        OOS1.writeObject(s1);
        OOS1.writeObject(s2);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public String checkDiscount(String userid) throws IOException, ClassNotFoundException{
      OOS1.writeObject("Check Discount");
      OOS1.writeObject(userid);
       OOS1.flush();
       s=(String) OIS1.readObject();
        return s;
    }
    public String sendquery(Queries query) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Send Query");
        OOS1.writeObject(query);
        //OOS1.writeObject(s2);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
     public String viewQueries() throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("View Queries");
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
       
    }
    public String sendreply(Queries query) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Send Reply");
        OOS1.writeObject(query);
        //OOS1.writeObject(s2);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public String showreply(String u) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Show Reply");
        OOS1.writeObject(u);
        s=(String) OIS1.readObject();
        return s;
    }
}
