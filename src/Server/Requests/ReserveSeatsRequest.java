/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
import User.UserDetail;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.sql.ResultSet;

/**
 *
 * @author kmish
 */
public class ReserveSeatsRequest {
    private final Connection con;
    private PreparedStatement st;
    private static Statement stmt;
    public String trainNum,userId,passclass,ticketid,fname,lname,gender,berth;
    public int seatno,age;
     public Date date;
     PassDetail pass;
     String dbpassclass;
    
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
    
    //To update Queries in Database
    public boolean updateQueries(int seatno,String ticketid,String userid) throws SQLException
    {   int check=0;
            
            
            System.out.println("7\n");
            
            java.util.Date utilObj = date;
            java.sql.Date sqlObj = new java.sql.Date(utilObj.getTime());
            
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
            st.setDate(11,sqlObj);
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
        if(passclass.equalsIgnoreCase("Sleeper")) nos=8;
        else nos=6;
        
        if(berth.equalsIgnoreCase("lowers"))
        {  bookedseats=rs.getInt("lowers");
            n=bookedseats/2;
            if(bookedseats%2==1)
                seatno=(n*nos)+(nos/2); 
            else seatno=(n*nos)+1;    
            
            ticketid=userid+trainNum+passclass+Integer.toString(seatno);
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET lowers=lowers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET lowers=lowers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET lowers=lowers+1 WHERE trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
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
            
            ticketid=userid+trainNum+passclass+Integer.toString(seatno);
            
            String query1="";
            
            query1="UPDATE sleeperClass SET middles=middles+1 WHERE trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
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
            
            ticketid=userid+trainNum+passclass+Integer.toString(seatno);
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET uppers=uppers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET uppers=uppers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET uppers=uppers+1 WHERE trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        else if(berth.equalsIgnoreCase("sidelowers"))
        {   
            bookedseats=rs.getInt("sidelowers");
            n=bookedseats;
            seatno=(n*nos)+nos-1;
            
            ticketid=userid+trainNum+passclass+Integer.toString(seatno);
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET sidelowers=sidelowers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET sidelowers=sidelowers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET sidelowers=sidelowers+1 WHERE trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        else if(berth.equalsIgnoreCase("sideuppers"))
        {
            bookedseats=rs.getInt("sideuppers");
            n=bookedseats;
            seatno=(n*nos)+nos;
            
            ticketid=userid+trainNum+passclass+Integer.toString(seatno);
            
            String query1="";
            if(passclass.equalsIgnoreCase("First AC"))
            query1="UPDATE firstClass SET sideuppers=sideuppers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Second AC"))
            query1="UPDATE secondClass SET sideuppers=sideuppers+1 WHERE trainNum='"+(trainNum)+"';";
            else if(passclass.equalsIgnoreCase("Sleeper"))
            query1="UPDATE sleeperClass SET sideuppers=sideuppers+1 WHERE trainNum='"+(trainNum)+"';";
            st=con.prepareStatement(query1);
            st.execute();
            return updateQueries(seatno,ticketid, userid);
        }
        
        return false;
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
           { q1="SELECT * FROM firstClass WHERE trainNum=?"; dbpassclass="firstClass";}
            else if(passclass.equalsIgnoreCase("Second AC"))
            {q1="SELECT * FROM secondClass WHERE trainNum=?";dbpassclass="secondClass";}
            else if(passclass.equalsIgnoreCase("Sleeper"))
            { q1="SELECT * FROM sleeperClass WHERE trainNum=?";dbpassclass="sleeperClass";}
            
            st=con.prepareStatement(q1);
           st.setString(1, tnum);
            ResultSet rs=(ResultSet) st.executeQuery();
            if(rs.next()){
            System.out.println("4\n");
            int totalseats=rs.getInt("totalseats");
            int flag=-1;
            String[] arr=new String[]{"Lower","Middle","Upper","Side Lower","Side Upper"};
            String[] dbarr=new String[]{"lowers","middles","uppers","sidelowers","sideuppers"};
            switch(berth)
            {
                case "Lower": if(availableSeat(rs,dbarr[0],totalseats))
                                {flag=0;}
                                    break;
                case "Middle":if(passclass.equalsIgnoreCase("Sleeper") && availableSeat(rs,dbarr[1],totalseats))
                                {flag=1;}
                                    break;
                case "Upper":if(availableSeat(rs,dbarr[2],totalseats))
                                {flag=2;}
                                    break;
                case "Side Lower":if(availableSeat(rs,dbarr[3],totalseats))
                                {flag=3;}
                                    break;
                case "Side Upper":if(availableSeat(rs,dbarr[4],totalseats))
                                {flag=4;}
                                    break;
                        
            }
            
          if(flag!=-1)
          { //preference mil chuki hai !!
              System.out.println("5\n");
              boolean b=bookSeat(rs,dbarr[flag],userid.getUserid());
              return b;
          }
          else {
               for(int i=0;i<5;i++)
               {    if(i==1 && !(passclass.equalsIgnoreCase("Sleeper")))
                   continue;
                   if(availableSeat(rs,dbarr[i],totalseats))
                   {
                       boolean b=bookSeat(rs,dbarr[i],userid.getUserid());
                        return b;
                   }  
               }
              
          }
        
        }
        //}
    return false;
    
    }
    
}
