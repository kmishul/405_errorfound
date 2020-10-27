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
import Admin.Admindetail;
import Admin.CancelTrain;
import Admin.PassDetail;
import Admin.PassDetails;
import Admin.ViewTrain;
import Admin.RemoveTrain;
import Server.Requests.AddCoachesRequest;
import Server.Requests.AddTrainRequest;
import Server.Requests.CancelBooking;
import Server.Requests.PassDetailsRequest;
import Server.Requests.UserSignupRequest;
import Server.Requests.ViewTrainsRequest;
import Server.Requests.CancelTrainRequest;
import Server.Requests.RemoveTrainRequest;
import Server.Requests.RerouteTrainRequest;
import Server.Requests.ReserveSeatsRequest;
import Server.Requests.SearchTrainRequest;
import Server.Requests.SeatsAvailRequest;
import Server.Requests.TicketsRequest;
import Server.Requests.TravelInfoRequest;
import User.UserDetail;
import User.UserLogin;
import User.UserSignup;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmish
 */
public class ClientHandler implements Runnable,Serializable{
    private final Socket client;
    private String user_name;
    private PreparedStatement st;
    private ObjectOutputStream OOS;
    private ObjectInputStream OIS;
    private DataOutputStream DOS;
    private DataInputStream DIS;
    private UserDetail mainId;
    
    ClientHandler(Socket client) throws IOException{
        this.client=client;
        OOS=new ObjectOutputStream(client.getOutputStream());
        OIS=new ObjectInputStream(client.getInputStream());
        DOS=new DataOutputStream(client.getOutputStream());
        DIS=new DataInputStream(client.getInputStream());
        System.out.println("\n done1");
    }
    
    private UserDetail getUserMainID()
    {
        return this.mainId;
       
    }
    private void setUserMainID(UserDetail u)
    {
        this.mainId=u;
       
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String request;
        System.out.println("\n done2");
        try {   
            while(true){
                request=DIS.readUTF();
                System.out.println("\n doubt clear");
            
                if(request.equals("User SignUp")){
                UserDetail user=(UserDetail)OIS.readObject();
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
                UserDetail userl=(UserDetail)OIS.readObject();
                
                UserLoginRequest userlr=new UserLoginRequest(userl);
                if(userlr.checklogininfo()){
                    DOS.writeUTF("validlogindetails");
                    DOS.flush();
                    this.setUserMainID(userl);
                    System.out.println("Valid USer Login");
                }
                else{
                    DOS.writeUTF("Wrong credentials");
                    DOS.flush();
                }
            }
            if(request.equals("Admin Login")) {
                System.out.println("reached client handler for adminlogin");
                Admindetail adminl=(Admindetail)OIS.readObject();
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
                ViewTrain train=(ViewTrain)OIS.readObject();
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
            if(request.equals("Cancel Train")){
                ViewTrain train=(ViewTrain)OIS.readObject();
                CancelTrainRequest trainn=new CancelTrainRequest(train);
                if(trainn.canceltrain()){
                    DOS.writeUTF("valid");
                    System.out.println("valid check\n");
                }
                else{
                    DOS.writeUTF("Error:this train is already cancelled or does not exist");
                    System.out.println("unvalid check\n");
                }
            }
            if(request.equals("Reserve Seat")){
                System.out.println("1\n");
                PassDetail pass=(PassDetail)OIS.readObject();
                ReserveSeatsRequest rss=new ReserveSeatsRequest(pass);
                boolean b=rss.bookticket(this.getUserMainID());
               if(b){ System.out.println("9\n");
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("seat booked\n");
                }
                else{
                    OOS.writeObject("Sorry ! Not Available");
                    OOS.flush();
                    System.out.println("Sorry ! Not Available\n");
                }
            }
            if(request.equals("Uncancel Train")){
                ViewTrain train=(ViewTrain)OIS.readObject();
                CancelTrainRequest trainn=new CancelTrainRequest(train);
                if(trainn.uncanceltrain()){
                    DOS.writeUTF("valid");
                    System.out.println("valid check\n");
                }
                else{
                    DOS.writeUTF("Error:this train is not cancelled or it does not exist");
                    System.out.println("unvalid check\n");
                }
            }
            
            if(request.equals("View Trains"))
            { 
                ViewTrainsRequest v=new ViewTrainsRequest();
                String res=v.getTrain();
                if(res.equals("valid"))
                {   ArrayList<ViewTrain> vt1;
                    vt1=(ArrayList<ViewTrain>)v.getList();
                    OOS.writeObject("valid");
                    OOS.writeObject(vt1);
                   
                }
            
            else {
                OOS.writeObject("Invalid");
                    System.out.println("Invalid\n");
                }
            }
            if(request.equals("Search Train")){
                String startstn=DIS.readUTF();
                String stopstn=DIS.readUTF();
                SearchTrainRequest s=new SearchTrainRequest();
                String res=s.getTrain(startstn, stopstn);
                if(res.equals("valid"))
                {   System.out.println("checkview1\n");
                    ArrayList<ViewTrain> vt1;
                    vt1=(ArrayList<ViewTrain>)s.getList();
                    System.out.println("listcheck\n");
                    OOS.writeObject("valid");
                    OOS.writeObject(vt1);
                    //OOS.flush();
                    System.out.println("checkview2\n");
                    DOS.writeUTF(res);
                    System.out.println("checkview3\n");
                }
            
                else {
                        OOS.writeObject("Invalid");
                        System.out.println("Invalid\n");
                    }
            }
            
            if(request.equalsIgnoreCase("Pass Details"))
            {
                PassDetailsRequest p=new PassDetailsRequest();
                String res=p.getPassengers();
                if(res.equalsIgnoreCase("valid"))
                {
                    ArrayList<PassDetail> vt2;
                    vt2=(ArrayList<PassDetail>)p.getList();
                    OOS.writeObject("valid");
                    OOS.writeObject(vt2);
                    
                }
            
            else {
                OOS.writeObject("Invalid");
                    System.out.println("Invalid\n");
                }
            }
            
            if(request.equals("Remove Train")){
                ViewTrain train=(ViewTrain)OIS.readObject();
                RemoveTrainRequest cncl=new RemoveTrainRequest(train);
                String Res=cncl.removetrain();
                OOS.writeObject(Res);
//                if(cncl.removetrain()){
//                    OOS.writeObject("removetrainvalid");
//                    System.out.println("valid check\n");
//                }
//                else{
//                    OOS.writeObject("Error:this train does not exist");
//                    System.out.println("unvalid check\n");
//                }
            }
            
            if(request.equals("Travel Info"))
            {   System.out.println("travel check\n");
                TravelInfoRequest t=new TravelInfoRequest();
                String res=t.getInfo(this.getUserMainID());
                if(res.equalsIgnoreCase("valid"))
                {
                    ArrayList<PassDetail> pd;
                    pd=(ArrayList<PassDetail>)t.getPassList();
                   
                    ArrayList<ViewTrain> vt;
                    vt=(ArrayList<ViewTrain>)t.getTrainList();
                    OOS.writeObject("valid");
                    OOS.writeObject(pd);
                    OOS.writeObject(vt);
                    OOS.flush();
                    System.out.println("last check\n");
                
                }
            
            else {
                OOS.writeObject("Invalid");
                OOS.flush();
                    System.out.println("Invalid\n");
                }
            }
            
            if(request.equals("Tickets"))
            {  
                TicketsRequest t=new TicketsRequest();
                String res=t.getTickets(this.getUserMainID());
                if(res.equalsIgnoreCase("valid"))
                {
                    ArrayList<PassDetail> pd;
                    pd=(ArrayList<PassDetail>)t.getPassList();
                   
                    ArrayList<ViewTrain> vt;
                    vt=(ArrayList<ViewTrain>)t.getTrainList();
                    OOS.writeObject("valid");
                    OOS.writeObject(pd);
                    OOS.writeObject(vt);
                    OOS.flush();
                    System.out.println("last check\n");
                
                }
            
            else {
                OOS.writeObject("Invalid");
                OOS.flush();
                    System.out.println("Invalid\n");
                }
            }
            if(request.equals("Cancel Booking")){
                System.out.println("1\n");
                String pnr=DIS.readUTF();
                System.out.println(pnr);
                CancelBooking ccn=new CancelBooking();
                if(ccn.cancel(pnr)){
                    OOS.writeObject("valid");
                }
                else{
                    OOS.writeObject("Error:Wrong Credentials");
                }
            }
            if(request.equals("Reroute Train")){
                ViewTrain train=(ViewTrain) OIS.readObject();
                RerouteTrainRequest rer=new RerouteTrainRequest();
                if(rer.reroute(train)){
                    System.out.println("valid");
                    OOS.writeObject("valid");
                }
                else{
                    OOS.writeObject("Train number does not exist");
                }
                    
            }
            if(request.equals("Add FC")){
                AddCoachesRequest add=new AddCoachesRequest();
                ViewTrain train=(ViewTrain) OIS.readObject();
                if(add.addfc(train)){
                    OOS.writeObject("valid");
                }
                else{
                    OOS.writeObject("Train does not exist");
                    System.out.println("error2");
                }
            }
            if(request.equals("Add SC")){
                AddCoachesRequest add=new AddCoachesRequest();
                ViewTrain train=(ViewTrain) OIS.readObject();
                if(add.addsc(train)){
                    OOS.writeObject("valid");
                }
                else{
                    OOS.writeObject("Train does not exist");
                    System.out.println("error2");
                }
            }
            if(request.equals("Add SLC")){
                AddCoachesRequest add=new AddCoachesRequest();
                ViewTrain train=(ViewTrain) OIS.readObject();
                if(add.addslc(train)){
                    OOS.writeObject("valid");
                }
                else{
                    OOS.writeObject("Train does not exist");
                    System.out.println("error2");
                }
            }
            if(request.equals("Seat Avail")){
                String tnum=DIS.readUTF();
                SeatsAvailRequest av=new SeatsAvailRequest();
                if(av.checktrainname(tnum)){
                    int fc=av.getseatsfc(tnum);
                    int sc=av.getseatssc(tnum);
                    int slc=av.getseatsslc(tnum);
                    OOS.writeObject("valid");
                    OOS.writeObject(fc);
                    OOS.writeObject(sc);
                    OOS.writeObject(slc);
                }
                else{
                    OOS.writeObject("Train Numbwer does not exist");
                    System.out.println("No Train");
                }
            }
        }
        }catch (IOException ex) {
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
