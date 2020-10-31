/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.Requests.AddTrainRequest;
import Server.Requests.AddCoachesRequest;
import Server.Requests.AdminLoginRequest;
import Server.Requests.UserLoginRequest;
import Admin.Admindetail;
import Admin.PassDetail;
import Admin.ViewTrain;
import Server.Requests.*;
import User.Queries;
import User.UserDetail;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    private UserDetail mainId;
    
    ClientHandler(Socket client) throws IOException{
        this.client=client;
        OOS=new ObjectOutputStream(client.getOutputStream());
        OIS=new ObjectInputStream(client.getInputStream());
    }
    
    private UserDetail getUserMainID()  //To get userId Of user
    {
        return this.mainId; 
    }
    private void setUserMainID(UserDetail u) //To set userID of user
    {
        this.mainId=u;
    }

    
    
    
    
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String request;
        System.out.println("\n done2");
        try {   
            while((client.isClosed()==false)){
                request=(String) OIS.readObject();
                System.out.println("\n doubt clear");
            
                if(request.equals("User SignUp")){
                UserDetail user=(UserDetail)OIS.readObject();
                UserSignupRequest userr=new UserSignupRequest(user);
                if(userr.adduser()){
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("valid check\n");
                }
                else{
                    OOS.writeObject("Error: may be username already exist,try another one");
                    OOS.flush();
                }
            
            }
            if(request.equals("User Login")) {
                System.out.println("reached client handler for login");
                UserDetail userl=(UserDetail)OIS.readObject();
                
                UserLoginRequest userlr=new UserLoginRequest(userl);
                if(userlr.checklogininfo()){
                    OOS.writeObject("validlogindetails");
                    OOS.flush();
                    this.setUserMainID(userl);
                    System.out.println("Valid USer Login");
                }
                else{
                    OOS.writeObject("Wrong credentials");
                    OOS.flush();
                }
            }
            if(request.equals("Admin Login")) {
                System.out.println("reached client handler for adminlogin");
                Admindetail adminl=(Admindetail)OIS.readObject();
                AdminLoginRequest userlr=new AdminLoginRequest(adminl);
                if(userlr.checkadminlogininfo()){
                    {OOS.writeObject("validlogindetailsforadmin");
                    OOS.flush();
                    System.out.println("Valid Admin Login");
                    }
                }
                else{
                    OOS.writeObject("Wrong credentials");
                    OOS.flush();
                    //DOS.writeUTF("Error: this username already exists,try using another one");   
                }
            }
            if(request.equals("Add Train")){
                //OOS.reset();
                ViewTrain train=(ViewTrain)OIS.readObject();
                AddTrainRequest trainn=new AddTrainRequest(train);
                if(trainn.addtrain()){
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("valid check\n");
                }
                else{
                    OOS.writeObject("Error:this train num already exist");
                    OOS.flush();
                    System.out.println("unvalid check\n");
                }
            }
            if(request.equals("Cancel Train")){
                ViewTrain train=(ViewTrain)OIS.readObject();
                CancelTrainRequest trainn=new CancelTrainRequest(train);
                if(trainn.canceltrain()){
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("valid check\n");
                }
                else{
                    OOS.writeObject("Error:this train is already cancelled or does not exist");
                    OOS.flush();
                    System.out.println("unvalid check\n");
                }
            }
            if(request.equals("Reserve Seat")){
                System.out.println("1\n");
                PassDetail pass=(PassDetail)OIS.readObject();
                int discount=(int) OIS.readObject();
                TrainStatusRequest tss=new TrainStatusRequest(pass);
                int check=tss.getTrainStatus();
                
                if(check==0)
                {
                    OOS.writeObject("Sorry ! Train is Cancelled");
                    OOS.flush();
                    
                }
                else if(check==2)
                {
                    OOS.writeObject("Sorry ! Train is not available on this day");
                    OOS.flush();
                    
                }
                else if(check==1){
                    ReserveSeatsRequest rss=new ReserveSeatsRequest(pass,discount);
                    boolean b=rss.bookticket(this.getUserMainID());
                    if(b){ System.out.println("9\n");
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("seat booked\n");
                    }
                    else{
                    OOS.writeObject("waiting");
                    OOS.flush();
                    System.out.println("Seats ! Not Available\n");
                    }
                    }
                
                else if(check==-1){
                    OOS.writeObject("Train does not exist !");
                    OOS.flush();
                }
            }
            if(request.equals("Reserve Waiting")){
                PassDetail pass=(PassDetail)OIS.readObject();
                int discount=(int) OIS.readObject();
                new WaitingRequest(pass,discount);
            }
            if(request.equals("Uncancel Train")){
                ViewTrain train=(ViewTrain)OIS.readObject();
                CancelTrainRequest trainn=new CancelTrainRequest(train);
                if(trainn.uncanceltrain()){
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("valid check\n");
                }
                else{
                    OOS.writeObject("Error:this train is not cancelled or it does not exist");
                    OOS.flush();
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
                    OOS.flush();
                   
                }
            
            else {
                OOS.writeObject("Invalid");
                    OOS.flush();
                    System.out.println("Invalid\n");
                }
            }
            if(request.equals("Search Train")){
                String startstn=(String) OIS.readObject();
                String stopstn=(String) OIS.readObject();
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
                    //OOS.writeObject(res);
                    OOS.flush();
                }
            
                else {
                        OOS.writeObject("Invalid");
                        OOS.flush();
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
                    OOS.flush();
                    
                }
            
            else {
                OOS.writeObject("Invalid");
                OOS.flush();
                    System.out.println("Invalid\n");
                }
            }
            
            if(request.equals("Remove Train")){
                ViewTrain train=(ViewTrain)OIS.readObject();
                RemoveTrainRequest cncl=new RemoveTrainRequest(train);
                String Res=cncl.removetrain();
                OOS.writeObject(Res);
                OOS.flush();
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
                String pnr=(String) OIS.readObject();
                System.out.println(pnr);
                CancelBookingRequest ccn=new CancelBookingRequest();
                if(ccn.cancel(pnr)){
                    OOS.writeObject("valid");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Error:Wrong Credentials");
                    OOS.flush();
                }
            }
            if(request.equals("Reroute Train")){
                ViewTrain train=(ViewTrain) OIS.readObject();
                RerouteTrainRequest rer=new RerouteTrainRequest();
                if(rer.reroute(train)==1){
                    System.out.println("valid");
                    OOS.writeObject("valid");
                    OOS.flush();
                }
                else if(rer.reroute(train)==0){
                    OOS.writeObject("Train number does not exist");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("This train has tickets reserved,can not re-route");
                    OOS.flush();
                }
                    
            }
            if(request.equals("Add FC")){
                AddCoachesRequest add=new AddCoachesRequest();
                ViewTrain train=(ViewTrain) OIS.readObject();
                if(add.addfc(train)){
                    OOS.writeObject("valid");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Train does not exist");
                    OOS.flush();
                    System.out.println("error2");
                }
            }
            if(request.equals("Add SC")){
                AddCoachesRequest add=new AddCoachesRequest();
                ViewTrain train=(ViewTrain) OIS.readObject();
                if(add.addsc(train)){
                    OOS.writeObject("valid");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Train does not exist");
                    OOS.flush();
                    System.out.println("error2");
                }
            }
            if(request.equals("Add SLC")){
                AddCoachesRequest add=new AddCoachesRequest();
                ViewTrain train=(ViewTrain) OIS.readObject();
                if(add.addslc(train)){
                    OOS.writeObject("valid");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Train does not exist");
                    OOS.flush();
                    System.out.println("error2");
                }
            }
            if(request.equals("Seat Avail")){
                String tnum=(String) OIS.readObject();
                Date date=(Date) OIS.readObject();
                SeatsAvailRequest av=new SeatsAvailRequest();
                if(av.checktrainname(tnum)){
                    int fc=av.getseatsfc(tnum,date);
                    int sc=av.getseatssc(tnum,date);
                    int slc=av.getseatsslc(tnum,date);
                    if(fc==-1 || sc==-1 || slc==-1)
                        OOS.writeObject("Train is not running on this day!");
                    else{   
                    OOS.writeObject("valid");
                    OOS.writeObject(fc);
                    OOS.writeObject(sc);
                    OOS.writeObject(slc);}
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Train Number does not exist");
                    OOS.flush();
                    System.out.println("No Train");
                }
            }
            if(request.equals("Discounts")){
                System.out.println("checkDiscount");
                int limit=(int) OIS.readObject();
                int discount=(int) OIS.readObject();
                DiscountsRequest dr=new DiscountsRequest();
                if(dr.giveDiscounts(limit, discount)){
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("valid discounts\n");
                }
                else{
                    OOS.writeObject("empty");
                    OOS.flush();
                    System.out.println("kuch galti h discounts m\n");
                }
            }
            if(request.equals("Check Discount")){
                String userid=(String) OIS.readObject();
                DiscountsRequest dr=new DiscountsRequest();
                 dr.checkDiscount(userid,OOS);
            }
            
            if(request.equals("Send Query")){
                Queries query=(Queries) OIS.readObject();
                SendQueryRequest req=new SendQueryRequest();
                if(req.sendquery(query)){
                    OOS.writeObject("valid");
                    System.out.println("valid check");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Sorry! You already have a pending query");
                    OOS.flush();
                    System.out.println("invalid check");
                }
            }
            if(request.equals("View Queries"))
            { 
                SendQueryRequest v=new SendQueryRequest();
                String res=v.getqry();
                if(res.equals("valid"))
                {   ArrayList<Queries> vt1;
                    vt1=(ArrayList<Queries>)v.getList();
                    OOS.writeObject("valid");
                    OOS.writeObject(vt1);
                    OOS.flush();
                   
                }
            
            else {
                OOS.writeObject("Invalid");
                    OOS.flush();
                    System.out.println("Invalid\n");
                }
            }
            if(request.equals("Send Reply")){
                System.out.println("replying");
                Queries q=(Queries) OIS.readObject();
                SendQueryRequest v=new SendQueryRequest();
                if(v.sendreply(q)){
                    OOS.writeObject("valid");
                    OOS.flush();
                    System.out.println("valid check");
                }
                else{
                    OOS.writeObject("invalid");
                    OOS.flush();
                    System.out.println("invalid check");
                }
            }
            if(request.equals("Show Reply")){
                String uid=(String) OIS.readObject();
                SendQueryRequest que= new SendQueryRequest();
                if(que.showreply(uid)==0){
                     OOS.writeObject("valid");
                     String rep=que.reply();
                     OOS.writeObject(rep);
                     OOS.flush();
                     que.deleterep(uid);
                }
                else if(que.showreply(uid)==1){
                    OOS.writeObject("No query from your side");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Sorry! No reply from admin");
                    OOS.flush();
                }
            }
            if(request.equals("Notify")){
                System.out.println("reached notification");
                String uid=(String) OIS.readObject();
                NotificationRequest not=new NotificationRequest();
                if(not.getrecent(uid)){
                    ArrayList<String> vt1;
                    vt1=(ArrayList<String>)not.getList();
                    OOS.writeObject("valid");
                    OOS.writeObject(vt1);
                    OOS.flush();
                    System.out.println("valid check");
                }
                else{
                    OOS.writeObject("invalid");
                    OOS.flush();
                    System.out.println("invalid check");
                }
            }
            if(request.equals("Change Password")){
                String uid=(String) OIS.readObject();
                String opass=(String) OIS.readObject();
                String npass=(String) OIS.readObject();
                ChangePasswordRequest cp=new ChangePasswordRequest();
                if(cp.changepass(uid, opass, npass)){
                    OOS.writeObject("valid");
                    OOS.flush();
                }
                else{
                    OOS.writeObject("Incorrect Current Password");
                    OOS.flush();
                }
            }
            if(request.equals("User Profile")){
                System.out.println("reached client handler\n");
                String uid=(String) OIS.readObject();
                ShowUserProfile u=new ShowUserProfile();
                UserDetail user=u.showdetail(uid);
                OOS.writeObject(user);
            }
           }
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
         
    }
    
    
    
}
