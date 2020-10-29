/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
import User.UserDetail;
import com.mysql.cj.protocol.Resultset;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.sql.ResultSet;
import java.text.DateFormat;

/**
 *
 * @author kmish
 */
public class ReserveSeatsRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    private static Statement stmt;
    public String trainNum,userId,passclass,ticketid,fname,lname,gender,berth;
    public int seatno,age;
     public Date date;
     PassDetail pass;
     String dbpassclass;
    java.sql.Date sqldate;
    //To initialize datamembers
     public ReserveSeatsRequest(PassDetail pass) throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
       this.pass=pass;
        this.trainNum = pass.trainNum;
        this.userId = pass.userId;
        this.passclass = pass.passclass;
        this.ticketid = pass.ticketid;
        this.fname = pass.fname;
        this.lname = pass.lname;
        this.gender = pass.gender;
        this.berth = pass.berth;
        this.seatno = pass.seatno;
        this.age = pass.age;
        this.date = pass.date;
        java.util.Date utilObj = date;
            sqldate = new java.sql.Date(utilObj.getTime());
        
    }
    
     //To check if seats are available 
     public boolean availableSeat(ResultSet r,String berth,int total) throws SQLException
    {   int remaining=0;
       if(passclass.equalsIgnoreCase("First AC") || passclass.equalsIgnoreCase("Second AC")){
            if(berth.equalsIgnoreCase("lowers") || berth.equalsIgnoreCase("uppers"))
            remaining=(total/3)-r.getInt(berth);
            else if(!(berth.equalsIgnoreCase("middles"))) remaining=(total/6)-r.getInt(berth);
            System.out.println("6\n");
            if(remaining>0) return true;
            else return false;
       }
       else {
            if(berth.equalsIgnoreCase("lowers") || berth.equalsIgnoreCase("uppers") || berth.equalsIgnoreCase("middles"))
            remaining=(total/4)-r.getInt(berth);
            else remaining=(total/8)-r.getInt(berth);
            if(remaining>0) return true;
            else return false;
       }
    }
     
     //To check if cancelled seats are available 
     public int availableSeatfromcancelled(String berth) throws SQLException
    {   //int remaining=0;
        String query="";
            if(passclass.equalsIgnoreCase("First AC"))
            query="SELECT * FROM firstClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query="SELECT * FROM secondClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query="SELECT * FROM sleeperClasscancel WHERE berth=? AND rundate=? AND trainNum='"+(trainNum)+"';";
               
            
            st=con.prepareStatement(query);
            st.setString(1, berth);
            st.setDate(2, sqldate);
            ResultSet rs=(ResultSet) st.executeQuery();
            if(rs.next()){
            int seat=rs.getInt("passseatno");
            return seat;
       }
       else return 0;
    }
     
    
    //To update Queries in Database
    public boolean updateQueries(int seatno,String ticketid,String userid) throws SQLException
    {   int check=0;
            
            
            System.out.println("7\n");
            
            
            String query2="INSERT INTO `passengerdetail`(`trainNum`, `userId`, `passclass`,`berth`,`passseatNo`, `passengerTicketId`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
            st.execute();
            System.out.println("8\n");
            check++;
            if(check==1)
            return true;
            else return false;
    }
    
    
    
    //To book seat
    public boolean bookSeat(ResultSet rs,String berth,String userid) throws SQLException
    {   int seatno,bookedseats,n,nos;
        String ticketid;
        String d=Integer.toString(date.getDay())+Integer.toString(date.getMonth())+Integer.toString(date.getYear());
        if(passclass.equalsIgnoreCase("Sleeper")) nos=8;
        else nos=6;
        
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
            query1="UPDATE firstClass SET sideuppers=sideuppers+1 WHERE rundate=? AND trainNum=?";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET sideuppers=sideuppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET sideuppers=sideuppers+1 WHERE rundate=? AND trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.setDate(1, sqldate);
            st.setString(2, trainNum);
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
    



    //Method Called By ClientHandler
    public boolean bookticket(UserDetail userid) throws SQLException{
//        passclass=pass.getpassclass();
//        berth=pass.getberth();
        System.out.println("2\n");
        String tnum=trainNum;
        System.out.println(tnum);
       // if(passclass.equalsIgnoreCase("First AC") || passclass.equalsIgnoreCase("Second AC")){
            System.out.println("3\n");
            String q1="";
            if(passclass.equalsIgnoreCase("First AC"))
           { q1="SELECT * FROM firstClass WHERE trainNum=? AND rundate=?"; dbpassclass="firstClass";}
            else if(passclass.equalsIgnoreCase("Second AC"))
            {q1="SELECT * FROM secondClass WHERE trainNum=? AND rundate=?";dbpassclass="secondClass";}
            else if(passclass.equalsIgnoreCase("Sleeper"))
            { q1="SELECT * FROM sleeperClass WHERE trainNum=? AND rundate=?";dbpassclass="sleeperClass";}
            
            st=con.prepareStatement(q1);
           st.setString(1, tnum);
           st.setDate(2, sqldate);
            ResultSet rs=(ResultSet) st.executeQuery();
            if(rs.next()){
            System.out.println("4\n");
            int totalseats=rs.getInt("totalseats");
            int flag1=-1;
            int flag2=-1;
            String[] arr=new String[]{"Lower","Middle","Upper","Side Lower","Side Upper"};
            String[] dbarr=new String[]{"lowers","middles","uppers","sidelowers","sideuppers"};
            
            int cancelledseatno=0;  //to store cancelled seat number if any
            switch(berth)
            {
                case "Lower": if(availableSeat(rs,dbarr[0],totalseats))
                                flag1=0;
                              else {cancelledseatno=availableSeatfromcancelled(dbarr[0]);
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
            
          if(flag1!=-1)
          { //preference mil chuki hai !!
              System.out.println("5\n");
              boolean b=bookSeat(rs,dbarr[flag1],userid.getUserid());
              return b;
          }
          else if(flag2!=-1)
          { //cancelled tickets me se preference mil chuki hai !!
              boolean b=bookSeatfromcancelled(cancelledseatno,dbarr[flag1],userid.getUserid());
              return b;
          }
          else {
               
              for(int i=0;i<5;i++) //To check cancelled seats
                 { if(i==1 && !(passclass.equalsIgnoreCase("Sleeper")))
                   continue;
                    cancelledseatno=availableSeatfromcancelled(dbarr[i]);
                   if(cancelledseatno!=0) 
                      {boolean b=bookSeatfromcancelled(cancelledseatno,dbarr[i],userid.getUserid());
                        return b;}  
                  }
              
              
              for(int i=0;i<5;i++) //To check new seats
                 { if(i==1 && !(passclass.equalsIgnoreCase("Sleeper")))
                   continue;
                   if(availableSeat(rs,dbarr[i],totalseats))
                      {boolean b=bookSeat(rs,dbarr[i],userid.getUserid());
                        return b;}  
                  }
                
          }
        
        }
        
    return false;
    
    }
    
}
