/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Server.DBConnect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author kmish
 */
public class CancelBookingRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    private PreparedStatement st2;
    private PreparedStatement st3;
    
    public CancelBookingRequest() throws SQLException{ 
        con = DBConnect.con;
}
    
    public boolean cancel(String pnr) throws SQLException{
        //query to fetch all details of passenger from database whose ticket has to be cancelled
        String query="SELECT * FROM passengerdetail WHERE passengerTicketId=?";  
        st=con.prepareStatement(query);
        st.setString(1,pnr);
        ResultSet rs=st.executeQuery();
        
        if(rs.next()){
            String passclass=rs.getString("passclass");
            String tnum=rs.getString("trainNum");
            int seatno=rs.getInt("passseatNo");
            String berth=rs.getString("berth");
            String userid=rs.getString("userId");
            int refund=rs.getInt("fare");
    
//Inserting all seat details in cancel tables of each respective class of train
    switch(passclass){
        case "firstClass":
            switch(berth){
                case "Lower":
                    String q1="INSERT INTO `firstClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q1);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
                case "Upper":
                    String q2="INSERT INTO `firstClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q2);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;

                case "Side Upper":
                    String q3="INSERT INTO `firstClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q3);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
                case "Side Lower":
                    String q4="INSERT INTO `firstClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q4);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
            }
            case "secondClass":
                switch(berth){
                    case "Lower":
                        String q1="INSERT INTO `secondClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                        st=con.prepareStatement(q1);
                        st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                        break;
                    case "Upper":
                        String q2="INSERT INTO `secondClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                        st=con.prepareStatement(q2);
                        st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                        break;
                    case "Side Upper":
                        String q3="INSERT INTO `secondClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                        st=con.prepareStatement(q3);
                        st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                        break;
                    case "Side Lower":
                        String q4="INSERT INTO `secondClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                        st=con.prepareStatement(q4);
                        st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                        break;
        }
        case "sleeperClass":
            switch(berth){
                case "Lower":
                    String q1="INSERT INTO `sleeperClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q1);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
                case "Upper":
                    String q2="INSERT INTO `sleeperClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q2);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
                case "Middle":
                    String q5="INSERT INTO `sleeperClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q5);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
                case "Side Upper":
                    String q3="INSERT INTO `sleeperClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q3);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
                case "Side Lower":
                    String q4="INSERT INTO `sleeperClasscancel`(`trainNum`, `berth`,`passseatno`,`rundate`) VALUES (?,?,?,?)";
                    st=con.prepareStatement(q4);
                    st.setString(1, tnum);
                    st.setString(2, berth);
                    st.setInt(3, seatno);
                    st.setDate(4, rs.getDate("travdate"));
                    st.execute();
                    break;
            }
    }
            st2=con.prepareStatement("SET foreign_key_checks=0;");
            st2.execute();
            
            String query8="UPDATE userlogin SET userwallet=? WHERE userid=?";//To give Refund to this user
            st=con.prepareStatement(query8);
            st.setInt(1,refund);
            st.setString(2,userid);
            st.execute();

            st3=con.prepareStatement("DELETE FROM `passengerdetail` WHERE passengerTicketId =?");
            st3.setString(1, pnr);
            st3.execute();
            return true;
        }
        return false;
    }
}
