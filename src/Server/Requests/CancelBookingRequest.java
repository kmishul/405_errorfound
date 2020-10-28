/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
}
    
    public boolean cancel(String pnr) throws SQLException{
        String query="SELECT * FROM passengerdetail WHERE passengerTicketId=?";
        st=con.prepareStatement(query);
        st.setString(1,pnr);
        ResultSet rs=st.executeQuery();
        System.out.println("2\n");
        if(rs.next()){
            System.out.println("3\n");
            String passclass=rs.getString("passclass");
            String tnum=rs.getString("trainNum");
            int seatno=rs.getInt("passseatNo");
            String berth=rs.getString("berth");
            
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
            st3=con.prepareStatement("DELETE FROM `passengerdetail` WHERE passengerTicketId =?");
            st3.setString(1, pnr);
            st3.execute();
            return true;
        }
        return false;
    }
}
