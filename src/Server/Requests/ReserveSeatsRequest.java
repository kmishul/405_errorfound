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
        //String trainNum=pass.trainNum;
        
    }
    public boolean availableSeat(ResultSet r,String berth,int total) throws SQLException
    {   int remaining;
        if(berth.equalsIgnoreCase("lowers") || berth.equalsIgnoreCase("uppers"))
         remaining=(total/3)-r.getInt(berth);
     else remaining=(total/6)-r.getInt(berth);
        System.out.println("6\n");
    if(remaining>0) return true;
        else return false;
    }
    
    public boolean bookSeat(ResultSet rs,String berth,String tnum,String userid) throws SQLException
    {   int seatno,bookedseats,n;
        String ticketid;
        if(berth.equalsIgnoreCase("lowers"))
        {  
            bookedseats=rs.getInt("lowers");
            n=bookedseats/2;
            if(bookedseats%2==1)
                seatno=(n*6)+3;
            else seatno=(n*6)+1;
            
          String query1="UPDATE firstClass SET lowers=lowers+1 WHERE trainNum='"+(tnum)+"';";
            st=con.prepareStatement(query1);
            st.execute();
            System.out.println("7\n");
            ticketid=userid+tnum+passclass+Integer.toString(seatno);
            
            java.util.Date utilObj = date;
      // sql object
      java.sql.Date sqlObj = new java.sql.Date(utilObj.getTime());
            
            String query2="INSERT INTO `passengerdetail`(`trainNum`, `userId`, `passclass`, `passseatNo`, `passengerTicketId`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            st=con.prepareStatement(query2);
            st.setString(1,tnum);
            st.setString(2,userid);
            st.setString(3,passclass);
            st.setInt(4,seatno);
            st.setString(5,ticketid);
            st.setString(6,fname);
            st.setString(7,lname);
            st.setInt(8,age);
            st.setString(9,gender);
            st.setDate(10,sqlObj);
            //st.setDate(10,new java.sql.Date(date.getTime());),
            st.execute();
            System.out.println("8\n");
            return true;
        }
        else if(berth.equalsIgnoreCase("uppers"))
        {
            bookedseats=rs.getInt("uppers");
            n=bookedseats/2;
            if(bookedseats%2==1)
                seatno=(n*6)+4;
            else seatno=(n*6)+2;
        }
        else if(berth.equalsIgnoreCase("sidelowers"))
        {   
            bookedseats=rs.getInt("sidelowers");
            n=bookedseats;
            seatno=(n*6)+5;
        }
        else if(berth.equalsIgnoreCase("sideuppers"))
        {
            bookedseats=rs.getInt("sideuppers");
            n=bookedseats;
            seatno=(n*6)+6;
        }
        return false;
    }
    
    public boolean bookticket(UserDetail userid) throws SQLException{
//        passclass=pass.getpassclass();
//        berth=pass.getberth();
        System.out.println("2\n");
        String tnum=trainNum;
        System.out.println(tnum);
        if(passclass.equalsIgnoreCase("First AC")){
            System.out.println("3\n");
            String q1="SELECT * FROM firstClass WHERE trainNum=?";
            st=con.prepareStatement(q1);
            st.setString(1, tnum);
            ResultSet rs=(ResultSet) st.executeQuery();
            if(rs.next()){
            System.out.println("4\n");
            int totalseats=rs.getInt("totalseats");
            int flag=-1;
            String[] arr=new String[]{"Lower","Upper","Side Lower","Side Upper"};
            String[] dbarr=new String[]{"lowers","uppers","sidelowers","sideuppers"};
            switch(berth)
            {
                case "Lower": if(availableSeat(rs,dbarr[0],totalseats))
                                {flag=0;break;}
                                    break;
                case "Upper":if(availableSeat(rs,dbarr[1],totalseats))
                                {flag=1;break;}
                                    break;
                case "Side Lower":if(availableSeat(rs,dbarr[2],totalseats))
                                {flag=2;break;}
                                    break;
                case "Side Upper":if(availableSeat(rs,dbarr[3],totalseats))
                                {flag=3;break;}
                                    break;
                        
            }
            
            int i=0;
          if(flag!=-1)
          { //preference mil chuki hai !!
              System.out.println("5\n");
              boolean b=bookSeat(rs,dbarr[flag],tnum,userid.getUserid());
              return b;
          }
          else {
               for(i=0;i<4;i++)
               {
                   if(availableSeat(rs,dbarr[i],totalseats))
                   {
                       boolean b=bookSeat(rs,dbarr[i],tnum,userid.getUserid());
                        return b;
                   }  
               }
              
          }
        
        }
        }
    return false;
    
    }
    
}
