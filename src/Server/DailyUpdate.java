/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kmish
 */


public class DailyUpdate {
    private java.sql.Date newdate,firstdiscountdate,today;
    private PreparedStatement st;
    private ResultSet rs;
    private final Connection con;
    private int day;

    public DailyUpdate() throws SQLException {
        con=DBConnect.con;
        
        //Stored Required Dates in Respective variables
        Date date0=java.sql.Date.valueOf(java.time.LocalDate.now());
        java.util.Date utilObj0 = date0;
            today = new java.sql.Date(utilObj0.getTime());
        
        Date date=java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(30));
        java.util.Date utilObj = date;
            newdate = new java.sql.Date(utilObj.getTime());
            
        Date date1=java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(30));
        java.util.Date utilObj1 = date1;
            firstdiscountdate = new java.sql.Date(utilObj1.getTime());
            
            day=newdate.getDay();
            if(day==0) day=6;
            else day--;
            
            updateDiscounts();
            updateTrains();
            updatefirstclasscancel();
            updatesecondclasscancel();
            updatesleeperclasscancel();
            
    }
    
    //Delete expired discounts of users given by admin as validity only 30 days
    private void updateDiscounts() throws SQLException {
            String query="DELETE FROM discounts where discountdate<?";
            st=con.prepareStatement(query);
            st.setDate(1,firstdiscountdate);
            st.execute();
    }

   
   //Daily Delete expired records of trains
   //and Insert new records for next day in those trains which are running on this day
    private void updateTrains() throws SQLException {
            String query="SELECT * FROM traininfo;";
            st=con.prepareStatement(query);
            rs=st.executeQuery();
            while(rs.next())
            {   
                String s=rs.getString("days");
                if(s.charAt(day)=='1')
                {   int tfirst=0,tsecond=0,tsleeper=0;//total seats in respective class
                    String tnum=rs.getString("trainNum");
                    
                    String query1="SELECT * FROM firstClass WHERE trainNum=?";
                    st=con.prepareStatement(query1);
                    st.setString(1,tnum);
                    ResultSet rs1=st.executeQuery();
                    if(rs1.next())
                    tfirst=rs1.getInt("totalseats");
                    
                    String query2="SELECT * FROM secondClass WHERE trainNum=?";
                    st=con.prepareStatement(query2);
                    st.setString(1,tnum);
                    ResultSet rs2=st.executeQuery();
                    if(rs2.next())
                    tsecond=rs2.getInt("totalseats");
                    
                    String query3="SELECT * FROM sleeperClass WHERE trainNum=?";
                    st=con.prepareStatement(query3);
                    st.setString(1,tnum);
                    ResultSet rs3=st.executeQuery();
                    if(rs3.next())
                    tsleeper=rs3.getInt("totalseats");
                    
                    //Now insert rows in respective class for today's date
                    
                    st=con.prepareStatement("INSERT INTO firstClass(`trainNum`, `totalseats`,`rundate`) VALUES (?,?,?)");
                    st.setString(1,tnum);
                    st.setInt(2,tfirst);
                    st.setDate(3,newdate);
                    st.execute();
                    st=con.prepareStatement("INSERT INTO secondClass(`trainNum`, `totalseats`,`rundate`) VALUES (?,?,?)");
                    st.setString(1,tnum);
                    st.setInt(2,tsecond);
                    st.setDate(3,newdate);
                    st.execute();
                    st=con.prepareStatement("INSERT INTO sleeperClass(`trainNum`, `totalseats`,`rundate`) VALUES (?,?,?)");
                    st.setString(1,tnum);
                    st.setInt(2,tsleeper);
                    st.setDate(3,newdate);
                    st.execute();
                    }
                
            }

        //Now delete rows which are expired
            String query1="DELETE FROM firstClass where rundate<?";
            st=con.prepareStatement(query1);
            st.setDate(1,today);
            st.execute();
            String query2="DELETE FROM secondClass where rundate<?";
            st=con.prepareStatement(query2);
            st.setDate(1,today);
            st.execute();
            String query3="DELETE FROM sleeperClass where rundate<?";
            st=con.prepareStatement(query3);
            st.setDate(1,today);
            st.execute();
    }


        //Delete FirstClassCancelTickets daily which are expired
        //Check if any waiting passenger get any seat before one day
  private void updatefirstclasscancel() throws SQLException{
		Date tomorrow=java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(1));
                java.util.Date tom = tomorrow;
                java.sql.Date date = new java.sql.Date(tom.getTime());
        
		String q="SELECT * FROM firstClasscancel WHERE rundate=?";
		st=con.prepareStatement(q);
		st.setDate(1,date);
		ResultSet rs=st.executeQuery();
		while(rs.next()){
                String tno=rs.getString("trainNum");
                String q2="SELECT * FROM waitingpassengers WHERE trainNum=? AND passclass=? AND rundate=?";
                st=con.prepareStatement(q2);
                st.setString(1,tno);
                st.setString(2,"firstClass");
                st.setDate(3,date);
                ResultSet rs1=st.executeQuery();
                if(rs1.next()){
                        String userid=rs1.getString("userId");
                        String fname=rs1.getString("passengerFirstName");
                        String lname=rs1.getString("passengerLastName");
                    int age=rs1.getInt("passengerAge");
                        String gender=rs1.getString("passengergender");
                        int seatno=rs.getInt("passseatno");
                        String berth=rs.getString("berth");
                        int fare=rs1.getInt("fare");
                        String d=Integer.toString(date.getDay())+Integer.toString(date.getMonth())+Integer.toString(date.getYear());
                        String ticketid=userid+tno+"fi"+Integer.toString(seatno)+d;
                        String query2="INSERT INTO `passengerdetail`(`trainNum`, `userId`, `passclass`,`berth`,`passseatNo`, `passengerTicketId`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`,`fare`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                        st=con.prepareStatement(query2);
                        st.setString(1,tno);
                        st.setString(2,userid);
                        st.setString(3,"firstClass");
                        st.setString(4,berth);
                        st.setInt(5,seatno);
                        st.setString(6,ticketid);
                        st.setString(7,fname);
                        st.setString(8,lname);
                        st.setInt(9,age);
                        st.setString(10,gender);
                        st.setDate(11,date);
                        st.setInt(12,fare);
                        st.execute();
                        String q3="DELETE FROM waitingpassengers WHERE trainNum=? AND passclass=? AND rundate=? AND userId=?";
                        st=con.prepareStatement(q3);
                        st.setString(1,tno);
                        st.setString(2,"firstClass");
                        st.setDate(3,date);
                        st.setString(4,userid);
                        st.execute();
                        String q4="DELETE FROM firstClasscancel WHERE trainNum=? AND berth=? AND passseatno=? AND rundate=?";
                        st=con.prepareStatement(q4);
                        st.setString(1,tno);
                        st.setString(2,berth);
                        st.setInt(3,seatno);
                        st.setDate(4,date);
                        st.execute();
			}
		}
                String q7="SELECT * FROM waitingpassengers WHERE travdate=?";  //To give Refund whoever don't get any seat
                st=con.prepareStatement(q7);
                st.setDate(1,date);
                ResultSet rs1=st.executeQuery();
                while(rs1.next())
                {
                    int refund=rs1.getInt("fare");
                    String userid=rs1.getString("userId");
                    String query8="UPDATE userlogin SET userwallet=? WHERE userid=?";//To give Refund to this user
                    st=con.prepareStatement(query8);
                    st.setInt(1,refund);
                    st.setString(2,userid);
                    st.execute();

                }

		String q5="DELETE FROM waitingpassengers WHERE travdate=?";//To delete whoever don't get any seat
		st=con.prepareStatement(q5);
		st.setDate(1,date);
		st.execute();
                String q6="DELETE FROM firstClasscancel WHERE rundate=?";//Delete expired records from canceltickets table
		st=con.prepareStatement(q6);
		st.setDate(1,date);
		st.execute();
	}
        
        //Delete SleeperClassCancelTickets daily which are expired
        //Check if any waiting passenger get any seat before one day
  private void updatesleeperclasscancel() throws SQLException{
		Date tomorrow=java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(1));
                java.util.Date tom = tomorrow;
                java.sql.Date date = new java.sql.Date(tom.getTime());
                
                String q="SELECT * FROM sleeperClasscancel WHERE rundate=?";
		st=con.prepareStatement(q);
		st.setDate(1,date);
		ResultSet rs=st.executeQuery();
		while(rs.next()){
                String tno=rs.getString("trainNum");
                String q2="SELECT * FROM waitingpassengers WHERE trainNum=? AND passclass=? AND rundate=?";
                st=con.prepareStatement(q2);
                st.setString(1,tno);
                st.setString(2,"sleeperClass");
                st.setDate(3,date);
                ResultSet rs1=st.executeQuery();
                if(rs1.next()){
                        String userid=rs1.getString("userId");
                        String fname=rs1.getString("passengerFirstName");
                        String lname=rs1.getString("passengerLastName");
                    int age=rs1.getInt("passengerAge");
                        String gender=rs1.getString("passengergender");
                        int seatno=rs.getInt("passseatno");
                        String berth=rs.getString("berth");
                        int fare=rs1.getInt("fare");
                        String d=Integer.toString(date.getDay())+Integer.toString(date.getMonth())+Integer.toString(date.getYear());
                        String ticketid=userid+tno+"sl"+Integer.toString(seatno)+d;
                        String query2="INSERT INTO `passengerdetail`(`trainNum`, `userId`, `passclass`,`berth`,`passseatNo`, `passengerTicketId`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`,`fare`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                        st=con.prepareStatement(query2);
                        st.setString(1,tno);
                        st.setString(2,userid);
                        st.setString(3,"sleeperClass");
                        st.setString(4,berth);
                        st.setInt(5,seatno);
                        st.setString(6,ticketid);
                        st.setString(7,fname);
                        st.setString(8,lname);
                        st.setInt(9,age);
                        st.setString(10,gender);
                        st.setDate(11,date);
                        st.setInt(12,fare);
                        st.execute();
                        String q3="DELETE FROM waitingpassengers WHERE trainNum=? AND passclass=? AND rundate=? AND userId=?";
                        st=con.prepareStatement(q3);
                        st.setString(1,tno);
                        st.setString(2,"sleeperClass");
                        st.setDate(3,date);
                        st.setString(4,userid);
                        st.execute();
                        String q4="DELETE FROM sleeperClasscancel WHERE trainNum=? AND berth=? AND passseatno=? AND rundate=?";
                        st=con.prepareStatement(q4);
                        st.setString(1,tno);
                        st.setString(2,berth);
                        st.setInt(3,seatno);
                        st.setDate(4,date);
                        st.execute();
			}
		}
                String q7="SELECT * FROM waitingpassengers WHERE travdate=?";  //To give Refund whoever don't get any seat
                st=con.prepareStatement(q7);
                st.setDate(1,date);
                ResultSet rs1=st.executeQuery();
                while(rs1.next())
                {
                    int refund=rs1.getInt("fare");
                    String userid=rs1.getString("userId");
                    String query8="UPDATE userlogin SET userwallet=? WHERE userid=?";//To give Refund to this user
                    st=con.prepareStatement(query8);
                    st.setInt(1,refund);
                    st.setString(2,userid);
                    st.execute();

                
                }

		String q5="DELETE FROM waitingpassengers WHERE travdate=?";//To delete whoever don't get any seat
		st=con.prepareStatement(q5);
		st.setDate(1,date);
		st.execute();
                String q6="DELETE FROM sleeperClasscancel WHERE rundate=?";//Delete expired records from canceltickets table
		st=con.prepareStatement(q6);
		st.setDate(1,date);
		st.execute();
	}
  
        //Delete SecondClassCancelTickets daily which are expired
        //Check if any waiting passenger get any seat before one day
    private void updatesecondclasscancel() throws SQLException{
		Date tomorrow=java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(1));
                java.util.Date tom = tomorrow;
                java.sql.Date date = new java.sql.Date(tom.getTime());
                
                String q="SELECT * FROM secondClasscancel WHERE rundate=?";
		st=con.prepareStatement(q);
		st.setDate(1,date);
		ResultSet rs=st.executeQuery();
		while(rs.next()){
                String tno=rs.getString("trainNum");
                
                String q2="SELECT * FROM waitingpassengers WHERE trainNum=? AND passclass=? AND rundate=?";
                st=con.prepareStatement(q2);
                st.setString(1,tno);
                st.setString(2,"secondClass");
                st.setDate(3,date);
                ResultSet rs1=st.executeQuery();
                if(rs1.next()){
                        String userid=rs1.getString("userId");
                        String fname=rs1.getString("passengerFirstName");
                        String lname=rs1.getString("passengerLastName");
                        int age=rs1.getInt("passengerAge");
                        String gender=rs1.getString("passengergender");
                        int seatno=rs.getInt("passseatno");
                        String berth=rs.getString("berth");
                        int fare=rs1.getInt("fare");
                        String d=Integer.toString(date.getDay())+Integer.toString(date.getMonth())+Integer.toString(date.getYear());
                        String ticketid=userid+tno+"se"+Integer.toString(seatno)+d;
                        String query2="INSERT INTO `passengerdetail`(`trainNum`, `userId`, `passclass`,`berth`,`passseatNo`, `passengerTicketId`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`,`fare`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                        st=con.prepareStatement(query2);
                        st.setString(1,tno);
                        st.setString(2,userid);
                        st.setString(3,"secondClass");
                        st.setString(4,berth);
                        st.setInt(5,seatno);
                        st.setString(6,ticketid);
                        st.setString(7,fname);
                        st.setString(8,lname);
                        st.setInt(9,age);
                        st.setString(10,gender);
                        st.setDate(11,date);
                        st.setInt(12,fare);
                        st.execute();
                        String q3="DELETE FROM waitingpassengers WHERE trainNum=? AND passclass=? AND rundate=? AND userId=?";
                        st=con.prepareStatement(q3);
                        st.setString(1,tno);
                        st.setString(2,"secondClass");
                        st.setDate(3,date);
                        st.setString(4,userid);
                        st.execute();
                        String q4="DELETE FROM secondClasscancel WHERE trainNum=? AND berth=? AND passseatno=? AND rundate=?";
                        st=con.prepareStatement(q4);
                        st.setString(1,tno);
                        st.setString(2,berth);
                        st.setInt(3,seatno);
                        st.setDate(4,date);
                        st.execute();
			}
		}
                String q7="SELECT * FROM waitingpassengers WHERE travdate=?";  //To give Refund whoever don't get any seat
                st=con.prepareStatement(q7);
                st.setDate(1,date);
                ResultSet rs1=st.executeQuery();
                while(rs1.next())
                {
                    int refund=rs1.getInt("fare");
                    String userid=rs1.getString("userId");
                    String query8="UPDATE userlogin SET userwallet=? WHERE userid=?";//To give Refund to this user
                    st=con.prepareStatement(query8);
                    st.setInt(1,refund);
                    st.setString(2,userid);
                    st.execute();

                }

                String q5="DELETE FROM waitingpassengers WHERE travdate=?";//To delete whoever don't get any seat
		st=con.prepareStatement(q5);
		st.setDate(1,date);
		st.execute();
                
                String q6="DELETE FROM secondClasscancel WHERE rundate=?"; //Delete expired records from canceltickets table
		st=con.prepareStatement(q6);
		st.setDate(1,date);
		st.execute();
	}
       
        
        
}