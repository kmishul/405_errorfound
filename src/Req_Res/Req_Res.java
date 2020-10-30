/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Req_Res;
import Admin.*;
import User.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Folio
 */

public class Req_Res implements Serializable{
    private final String ip;
    private final int port;
    private final Socket socket;
    private final ObjectOutputStream OOS1;
    private final ObjectInputStream OIS1;
    private String s;

    //To get ObjectOuputStream Object
    public ObjectOutputStream getObjectOutputStream()
    {
        return OOS1;
    }
    //To get ObjectInputStream Object
    public ObjectInputStream getObjectInputStream()
    {
        return OIS1;
    }
    //To get Socket
    public Socket getSocket()
    {
        return socket;
    }
    
    //Constructor --call upon object creation
    public Req_Res() throws IOException{
        this.ip = "localhost";
        this.port = 8806;
        this.socket = new Socket(ip,port);
        this.OOS1 = new ObjectOutputStream(socket.getOutputStream());
        this.OIS1 = new ObjectInputStream(socket.getInputStream());
         System.out.println("Connected!");
          
          
    }
    
    public String sendUserSignup(UserDetail user) throws IOException, ClassNotFoundException{
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
        return s;
    }
    public String sendAdminLogin(Admindetail admin) throws IOException, ClassNotFoundException{
        System.out.println("reached sendAdminlogin in req_ress");
        OOS1.writeObject("Admin Login");
        OOS1.writeObject(admin);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public String addTrain(ViewTrain train) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Add Train");
        OOS1.writeObject(train);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }

    public String removeTrain(ViewTrain remtr)throws IOException, ClassNotFoundException{
        OOS1.writeObject("Remove Train");
        OOS1.writeObject(remtr);
        OOS1.flush();
        String s=(String) OIS1.readObject();
       return s;
        }

    public String cancelTrain(ViewTrain train) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Cancel Train");
        OOS1.writeObject(train);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }

    public String uncancelTrain(ViewTrain train) throws IOException, ClassNotFoundException {
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
     
    public String addSeatfc(ViewTrain train) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Add FC");
       OOS1.writeObject(train);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
       
    }
     public String addSeatsc(ViewTrain train) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Add SC");
       OOS1.writeObject(train);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
    }
      public String addSeatlc(ViewTrain train) throws IOException, ClassNotFoundException{
    
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
    public String seatAvail(String tnum,Date date) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Seat Avail");
       OOS1.writeObject(tnum);
       OOS1.writeObject(date);
       OOS1.flush();
       s=(String) OIS1.readObject();
       return s;
    }
     public String cancelBooking(String pnr) throws IOException, ClassNotFoundException{
    
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
    public String reserveSeat(PassDetail pass,int discount) throws IOException, ClassNotFoundException{
    
       OOS1.writeObject("Reserve Seat");
       OOS1.writeObject(pass);
       OOS1.writeObject(discount);
       OOS1.flush();
        s=(String) OIS1.readObject();
         return s;       
       
    }
    public void reserveWaiting(PassDetail pass,int discount) throws IOException, ClassNotFoundException{
       OOS1.writeObject("Reserve Waiting");
       OOS1.writeObject(pass);
       OOS1.writeObject(discount);
       OOS1.flush();      
       
    }
    public String travelInfo() throws IOException, ClassNotFoundException{
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
    public String searchTrain(String s1,String s2) throws IOException, ClassNotFoundException{
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
    public String sendQuery(Queries query) throws IOException, ClassNotFoundException{
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
    public String sendReply(Queries query) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Send Reply");
        OOS1.writeObject(query);
        //OOS1.writeObject(s2);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public String showReply(String u) throws IOException, ClassNotFoundException{
        OOS1.writeObject("Show Reply");
        OOS1.writeObject(u);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public String notification(String s) throws IOException, ClassNotFoundException{
        System.out.println("req res method");
        OOS1.writeObject("Notify");
        OOS1.writeObject(s);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public String changePass(String s,String p,String p1) throws IOException, ClassNotFoundException{
        System.out.println("req res method");
        OOS1.writeObject("Change Password");
        OOS1.writeObject(s);
        OOS1.writeObject(p);
        OOS1.writeObject(p1);
        OOS1.flush();
        s=(String) OIS1.readObject();
        return s;
    }
    public UserDetail userprofile(String uid) throws IOException, ClassNotFoundException{
        System.out.println("req res method");
        OOS1.writeObject("User Profile");
        OOS1.writeObject(uid);
        OOS1.flush();
        UserDetail u=(UserDetail) OIS1.readObject();
        return u;
    }
}
