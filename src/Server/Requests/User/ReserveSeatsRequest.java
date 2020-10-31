/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.User;

import Commmon_LockdownTraveller.*;
import Server.DBConnect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author kmish
 */
public class ReserveSeatsRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st,st1,st2,st3;
    private static Statement stmt;
    private String trainNum,userId,passclass,ticketid,fname,lname,gender,berth;
    private int seatno,age,fare,dynamicfareinc=0;
     private Date date;
     private PassDetail pass;
     private String dbpassclass;
    java.sql.Date sqldate;
    int discountt=0;
    
    //To initialize datamembers
     public ReserveSeatsRequest(PassDetail pass,int dis) throws SQLException{
         con = DBConnect.con;
        
        this.pass=pass;
        this.trainNum = pass.gettrainNum();
        this.userId = pass.getuserId();
        this.passclass = pass.getpassclass();
        this.ticketid = pass.getticketid();
        this.fname = pass.getfname();
        this.lname = pass.getlname();
        this.gender = pass.getgender();
        this.berth = pass.getberth();
        this.seatno = pass.getseatno();
        this.age = pass.getage();
        this.date = pass.getdate();
            java.util.Date utilObj = date;
         this.sqldate = new java.sql.Date(utilObj.getTime());
         this.discountt=dis;
         
         dynamicfareinc=getDynamiciChange(trainNum,date,passclass);
        
        
    }
    
     //To check if seats are available 
     public boolean availableSeat(ResultSet r,String berth,int total) throws SQLException
    {   int remaining=0;
       if(passclass.equalsIgnoreCase("First AC") || passclass.equalsIgnoreCase("Second AC")){
            if(berth.equalsIgnoreCase("lowers"))
            remaining=(total/3)-r.getInt("lowers");
            else if (berth.equalsIgnoreCase("uppers"))
            remaining=(total/3)-r.getInt("uppers");
            else if (berth.equalsIgnoreCase("sideuppers"))
            remaining=(total/6)-r.getInt("sideuppers");
            else if (berth.equalsIgnoreCase("sidelowers"))
            remaining=(total/6)-r.getInt("sidelowers");
       
            System.out.println("remaining"+remaining);
            if(remaining>0) return true;
            else return false;
       }
       else {
           
            if(berth.equalsIgnoreCase("lowers"))
            remaining=(total/4)-r.getInt("lowers");
            else if (berth.equalsIgnoreCase("uppers"))
            remaining=(total/4)-r.getInt("uppers");
            else if (berth.equalsIgnoreCase("sideuppers"))
            remaining=(total/8)-r.getInt("sideuppers");
            else if (berth.equalsIgnoreCase("sidelowers"))
            remaining=(total/8)-r.getInt("sidelowers");
            else if(berth.equalsIgnoreCase("middles")) 
            remaining=(total/4)-r.getInt(berth);
       
            System.out.println("remaining"+remaining);
            if(remaining>0) return true;
            else return false;
       }
    }
     
     //To check if cancelled seats are available 
     private int availableSeatfromcancelled(String berth) throws SQLException
    {   //int remaining=0;
        String query1="";
        String query2="";
            if(passclass.equalsIgnoreCase("First AC"))
            {query1="SELECT * FROM firstClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";
             query2="DELETE FROM firstClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";}
            else if(passclass.equalsIgnoreCase("Second AC"))
            {query1="SELECT * FROM secondClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";
             query2="DELETE FROM secondClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";}
            else if(passclass.equalsIgnoreCase("Sleeper"))
            {query1="SELECT * FROM sleeperClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";
             query2="DELETE FROM sleeperClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";}  
            
            st=con.prepareStatement(query1);
            st.setString(1, berth);
            st.setDate(2, sqldate);
            ResultSet rs=(ResultSet) st.executeQuery();
            if(rs.next()){
            int seat=rs.getInt("passseatno");
            
            st1=con.prepareStatement(query2);
            st1.setString(1, berth);
            st1.setDate(2, sqldate);
            st1.executeQuery();
            
            return seat;
       }
       else return 0;
    }
     
    
    //To update Queries in Database
    private boolean updateQueries(int seatno,String ticketid,String userid) throws SQLException
    {   int check=0;
            
            if(fare-discountt>=0) //To check if fare is more than discount
            {
                String query="DELETE FROM discounts where userId=?";
            st=con.prepareStatement(query);
            st.setString(1,userid);
            st.execute();
            fare=fare-discountt;
            }
            else {  //Still Some Discount left
                String query="UPDATE discounts SET discount=? where userId=?";
            st=con.prepareStatement(query);
            st.setInt(1,discountt-fare);
            st.execute();
            }  
            
            
            
            String query2="INSERT INTO `passengerdetail`(`trainNum`, `userId`, `passclass`,`berth`,`passseatNo`, `passengerTicketId`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`,`fare`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            st=con.prepareStatement(query2);
            st.setString(1,trainNum);
            st.setString(2,userid);
            st.setString(3,dbpassclass);
            st.setString(4,berth);
            st.setInt(5,seatno);
            st.setString(6,ticketid);
            st.setString(7,fname);
            st.setString(8,lname);
            st.setInt(9,age);
            st.setString(10,gender);
            st.setDate(11,sqldate);
            st.setInt(12,fare+dynamicfareinc);
            
            st.execute();
            System.out.println("8\n");
            check++;
            if(check==1)
            return true;
            else return false;
    }
    
    
    
    //Method to book seat
    private boolean bookSeat(ResultSet rs,String berth,String userid) throws SQLException
    {   int seatno,bookedseats,n,nos;
        String ticketid;
        String d=Integer.toString(date.getDay())+Integer.toString(date.getMonth())+Integer.toString(date.getYear());
        if(passclass.equalsIgnoreCase("Sleeper")) nos=8;
        else nos=6;
        
        //Check berth and get seat number according to that
        if(berth.equalsIgnoreCase("lowers"))
        {  bookedseats=rs.getInt("lowers");
            n=bookedseats/2;
            if(bookedseats%2==1)
                seatno=(n*nos)+(nos/2); 
            else seatno=(n*nos)+1;    
            
            ticketid=userid+trainNum+dbpassclass.charAt(0)+dbpassclass.charAt(1)+Integer.toString(seatno)+d;
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET lowers=lowers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET lowers=lowers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET lowers=lowers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.setDate(1, sqldate);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        else if(passclass.equalsIgnoreCase("Sleeper") && berth.equalsIgnoreCase("middles"))
        {
            bookedseats=rs.getInt("middles");
            n=bookedseats/2;
            if(bookedseats%2==1)
                seatno=(n*8)+5;
            else seatno=(n*8)+2;
            
           ticketid=userid+trainNum+dbpassclass.charAt(0)+dbpassclass.charAt(1)+Integer.toString(seatno)+d;
            
            String query1="";
            
            query1="UPDATE sleeperClass SET middles=middles+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.setDate(1, sqldate);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        else if(berth.equalsIgnoreCase("uppers"))
        {
            bookedseats=rs.getInt("uppers");
            n=bookedseats/2;
            if(bookedseats%2==1)
                seatno=(n*nos)+nos-2;    
            else seatno=(n*nos)+((nos-2)/2); 
            
            ticketid=userid+trainNum+dbpassclass.charAt(0)+dbpassclass.charAt(1)+Integer.toString(seatno)+d;
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET uppers=uppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET uppers=uppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET uppers=uppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.setDate(1, sqldate);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        else if(berth.equalsIgnoreCase("sidelowers"))
        {   
            bookedseats=rs.getInt("sidelowers");
            n=bookedseats;
            seatno=(n*nos)+nos-1;
            
            ticketid=userid+trainNum+dbpassclass.charAt(0)+dbpassclass.charAt(1)+Integer.toString(seatno)+d;
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET sidelowers=sidelowers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET sidelowers=sidelowers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET sidelowers=sidelowers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.setDate(1, sqldate);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        else if(berth.equalsIgnoreCase("sideuppers"))
        {
            bookedseats=rs.getInt("sideuppers");
            n=bookedseats;
            seatno=(n*nos)+nos;
            
            ticketid=userid+trainNum+dbpassclass.charAt(0)+dbpassclass.charAt(1)+Integer.toString(seatno)+d;
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET sideuppers=sideuppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET sideuppers=sideuppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET sideuppers=sideuppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.setDate(1, sqldate);
            //st.setString(2, trainNum);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        
        return false;
    }
    
    //To book seat from cancelledseats
    public boolean bookSeatfromcancelled(int seatno,String berth,String userid) throws SQLException
    {   
        String d=Integer.toString(date.getDay())+Integer.toString(date.getMonth())+Integer.toString(date.getYear());
        ticketid=userid+trainNum+dbpassclass.charAt(0)+dbpassclass.charAt(1)+Integer.toString(seatno)+d;
        return updateQueries(seatno,ticketid, userid);
        
    }
    
   
    //Check if train si half empty or not
    //If half empty charges decreased
    private boolean isTrainEmpty() throws SQLException
    {   String query;
        if(passclass.equalsIgnoreCase("First AC"))
        {
            query="SELECT * FROM firstClass where trainNum=? AND rundate=?";
            st=con.prepareStatement(query);
                    st.setString(1, trainNum);
                    st.setDate(2,sqldate);
                    ResultSet rs=st.executeQuery();
                    if(rs.next()){
                    int totalseats=rs.getInt("totalseats");
                    int bookedseats=rs.getInt("lowers")+rs.getInt("uppers")+rs.getInt("sideuppers")+rs.getInt("sidelowers");
                    if(bookedseats<totalseats/2)
                    return true;
                    else return false;
                }
                    
        }
        else if(passclass.equalsIgnoreCase("Second AC"))
        {
            query="SELECT * FROM secondClass where trainNum=? AND rundate=?";
            st=con.prepareStatement(query);
                    st.setString(1, trainNum);
                    st.setDate(2,sqldate);
                    ResultSet rs=st.executeQuery();
                    if(rs.next()){
                    int totalseats=rs.getInt("totalseats");
                    int bookedseats=rs.getInt("lowers")+rs.getInt("uppers")+rs.getInt("sideuppers")+rs.getInt("sidelowers");
                    if(bookedseats<totalseats/2)
                    return true;
                    else return false;
                }
          
        }
        else
        {
            query="SELECT * FROM sleeperClass where trainNum=? AND rundate=?";
            st=con.prepareStatement(query);
                    st.setString(1, trainNum);
                    st.setDate(2,sqldate);
                    ResultSet rs=st.executeQuery();
                    if(rs.next()){
                    int totalseats=rs.getInt("totalseats");
                    int bookedseats=rs.getInt("lowers")+rs.getInt("uppers")+rs.getInt("middles")+rs.getInt("sideuppers")+rs.getInt("sidelowers");
                    if(bookedseats<totalseats/2)
                    return true;
                    else return false;
                }
            
        }
        return false;
    }


    //To get Dynamic price which user has to pay on selected trains
    private int getDynamiciChange(String trainNum, Date date,String pclass) throws SQLException {
    
        int check=0,inc,dec;
        LocalDate ld1 = java.time.LocalDate.now();//today's date
             LocalDate ld2 = new java.sql.Date( date.getTime() ).toLocalDate();//util to local
              int diff=(int) DAYS.between(ld1,ld2); //diff bw two local dates
             
              
                if(diff<6)
                {   
                    String queryz="SELECT * FROM traininfo WHERE trainNum=?";
                    PreparedStatement stz=con.prepareStatement(queryz);
                    stz.setString(1, trainNum);
                    ResultSet rsz=stz.executeQuery();
                    if(rsz.next())
                    { check=rsz.getInt("dmc");
                    if(check==1)
                    {if(pclass.equalsIgnoreCase("First AC"))
                    {
                        inc=(int) (rsz.getInt("feeFirstClass")*0.2);    //charges increment
                        dec=(int) (rsz.getInt("feeFirstClass")*0.1);    //charges decrement
                    }
                    else if(pclass.equalsIgnoreCase("Second AC"))
                    {
                        inc=(int) (rsz.getInt("feeSecondClass")*0.2);
                        dec=(int) (rsz.getInt("feeSecondClass")*0.1);
                    }
                    else
                    {
                      inc=(int) (rsz.getInt("feeSleeperClass")*0.2);
                      dec=(int) (rsz.getInt("feeSleeperClass")*0.1);
                    } 
                    if(isTrainEmpty()==true)
                        return dec;
                    else return inc;
                    }
                    }
                }
                    
    return 0; //if booking is done on more than 5 days ago then no extra charge
    }
    
    
  
    
    //Method Called By ClientHandler
    public boolean bookticket(UserDetail userid) throws SQLException{
            String tnum=trainNum;   //Train Number
            String q1="",qq="";
            qq="SELECT * FROM traininfo WHERE trainNum=?";//To get Train Info
            st2=con.prepareStatement(qq);
            st2.setString(1, tnum);
            ResultSet rs0=(ResultSet) st2.executeQuery();
            rs0.next();
           
            //Now check in which class user want to travel
            //then store fare and passclass in respective variables
            if(passclass.equalsIgnoreCase("First AC")) 
           { q1="SELECT * FROM firstClass WHERE trainNum=? AND rundate=?"; 
           fare=rs0.getInt("feeFirstClass");dbpassclass="firstClass";}
            else if(passclass.equalsIgnoreCase("Second AC"))
            {q1="SELECT * FROM secondClass WHERE trainNum=? AND rundate=?";
            fare=rs0.getInt("feeSecondClass");dbpassclass="secondClass";}
            else if(passclass.equalsIgnoreCase("Sleeper"))
            { q1="SELECT * FROM sleeperClass WHERE trainNum=? AND rundate=?";
            fare=rs0.getInt("feeSleeperClass");dbpassclass="sleeperClass";}
            
            st=con.prepareStatement(q1);
            st.setString(1, tnum);
            st.setDate(2, sqldate);
            ResultSet rs=(ResultSet) st.executeQuery();
            if(rs.next()){
            int totalseats=rs.getInt("totalseats");
            int flag1=-1;
            int flag2=-1;
            
            String[] arr=new String[]{"Lower","Middle","Upper","Side Lower","Side Upper"};//Class Syntax of user Side
            String[] dbarr=new String[]{"lowers","middles","uppers","sidelowers","sideuppers"};//Class Syntax in Mysql table
            
            int cancelledseatno=0;  //variable to store cancelled seat number if any(by default 0(no sense))
        
         switch(berth)
          {
            case "Lower": if(availableSeat(rs,dbarr[0],totalseats)) //To check availablitity from new seats
                           flag1=0;
                           else {cancelledseatno=availableSeatfromcancelled(dbarr[0]);//To check availablitity from cancelled seats
                           if(cancelledseatno!=0) 
                           flag2=0;}  
                           break;
            case "Middle":if(passclass.equalsIgnoreCase("Sleeper") && availableSeat(rs,dbarr[1],totalseats))
                           flag1=1;
                           else {cancelledseatno=availableSeatfromcancelled(dbarr[1]);
                           if(cancelledseatno!=0) 
                           flag2=1;}  
                           break;
            case "Upper":if(availableSeat(rs,dbarr[2],totalseats))
                           flag1=2;
                           else {cancelledseatno=availableSeatfromcancelled(dbarr[2]);
                           if(cancelledseatno!=0) 
                           flag2=2;}  
                           break;
            case "Side Lower":if(availableSeat(rs,dbarr[3],totalseats))
                           flag1=3;
                           else {cancelledseatno=availableSeatfromcancelled(dbarr[3]);
                           if(cancelledseatno!=0) 
                           flag2=3;}  
                           break;
            case "Side Upper":if(availableSeat(rs,dbarr[4],totalseats))
                           flag1=4;
                           else {cancelledseatno=availableSeatfromcancelled(dbarr[4]);
                           if(cancelledseatno!=0) 
                           flag2=4;}  
                           break;

         }
            
          if(flag1!=-1) //preference mil chuki hai !!
          { 
              boolean b=bookSeat(rs,dbarr[flag1],userid.getUserid()); //call book seat method to book in preferred berth
              return b;
          }
          else if(flag2!=-1)//cancelled tickets me se preference mil chuki hai !!
          {   
              boolean b=bookSeatfromcancelled(cancelledseatno,dbarr[flag2],userid.getUserid());
              return b;             //call book seat from cancelled method to book in preferred berth
          }
          else {//Preference Nahi Mili
            
               for(int i=0;i<5;i++) //To check other births 
                 { if(i==1 && !(passclass.equalsIgnoreCase("Sleeper"))) //Middle And Non Sleeper-impossible 
                   continue;
                   if(availableSeat(rs,dbarr[i],totalseats))
                   {boolean b=bookSeat(rs,dbarr[i],userid.getUserid());
                   return b;}  
                  }
                
               for(int i=0;i<5;i++) //To check other births from cancelled seats
                { if(i==1 && !(passclass.equalsIgnoreCase("Sleeper")))//Middle And Non Sleeper-impossible
                 continue;
                 cancelledseatno=availableSeatfromcancelled(dbarr[i]);
                 if(cancelledseatno!=0) 
                 {boolean b=bookSeatfromcancelled(cancelledseatno,dbarr[i],userid.getUserid());
                    return b;}  
                }

                }
        
        }
        
    return false;
    
    }
}
