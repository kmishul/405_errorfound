/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.User;
import Commmon_LockdownTraveller.*;
import Server.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kmish
 */
public class WaitingRequest {
 private final Connection con;
    private PreparedStatement st,st2;
   
    //Constructor
    public WaitingRequest(PassDetail pass ,int discount) throws SQLException {
         con = DBConnect.con;
        
        String tnum=pass.gettrainNum();   //Train Number
            String qq="";
            qq="SELECT * FROM traininfo WHERE trainNum=?";//To get Train Info
            st2=con.prepareStatement(qq);
            st2.setString(1, tnum);
            ResultSet rs0=(ResultSet) st2.executeQuery();
            rs0.next();
           
            int fare=0;
            //Now check in which class user want to travel
            //then store fare and passclass in respective variables
            if(pass.getpassclass().equalsIgnoreCase("First AC")) 
           fare=rs0.getInt("feeFirstClass");
            else if(pass.getpassclass().equalsIgnoreCase("Second AC"))
           fare=rs0.getInt("feeSecondClass");
            else if(pass.getpassclass().equalsIgnoreCase("Sleeper"))
            fare=rs0.getInt("feeSleeperClass");
            
         
        
        if(fare-discount>=0) //To check if there any discount applied
            {
                String query="DELETE FROM discounts where userId=?";
            st=con.prepareStatement(query);
            st.setString(1,pass.getuserId());
            st.execute();
            fare=fare-discount;
            }
            else {//if still discount left update discount in table
                String query="UPDATE discounts SET discount=? where userId=?";
            st=con.prepareStatement(query);
            st.setInt(1,discount-fare);
            st.execute();
            }
        
        String dbpassclass="";  //It contains class of train in which passenger will travel
            if(pass.getpassclass().equalsIgnoreCase("First AC"))
            dbpassclass="firstClass";
            else if(pass.getpassclass().equalsIgnoreCase("Second AC"))
            dbpassclass="secondClass";
            else if(pass.getpassclass().equalsIgnoreCase("Sleeper"))
            dbpassclass="sleeperClass";
            
            java.util.Date udate = pass.getdate(); // booking date
            java.sql.Date date = new java.sql.Date(udate.getTime());//typecast to sql date
       
           //Insert into waiting passenger table
            String query1="INSERT INTO `waitingpassengers`(`trainNum`, `userId`, `passclass`, `passengerFirstName`, `passengerLastName`, `passengerAge`, `passengergender`, `travdate`,`fare`) VALUES (?,?,?,?,?,?,?,?,?)";
            st=con.prepareStatement(query1);
            st.setString(1,pass.gettrainNum());
            st.setString(2,pass.getuserId());
            st.setString(3,dbpassclass);
            st.setString(4,pass.getfname());
            st.setString(5,pass.getlname());
            st.setInt(6,pass.getage());
            st.setString(7,pass.getgender());
            st.setDate(8,date);
            st.setInt(9,fare);
            st.execute();
            
        
    }
    
    
}
