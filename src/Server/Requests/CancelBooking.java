/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

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
public class CancelBooking {
    private final Connection con;
    private PreparedStatement st;
    private PreparedStatement st2;
    private PreparedStatement st3;
    public CancelBooking() throws SQLException{
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
                            String q1="INSERT INTO `firstclasscancel`(`trainNum`, `lowers`) VALUES (?,?)";
                            st=con.prepareStatement(q1);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                        case "Upper":
                            String q2="INSERT INTO `firstclasscancel`(`trainNum`, `uppers`) VALUES (?,?)";
                            st=con.prepareStatement(q2);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                            
                        case "Side Upper":
                            String q3="INSERT INTO `firstclasscancel`(`trainNum`, `sideuppers`) VALUES (?,?)";
                            st=con.prepareStatement(q3);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                        case "Side Lower":
                            String q4="INSERT INTO `firstclasscancel`(`trainNum`, `sidelowers`) VALUES (?,?)";
                            st=con.prepareStatement(q4);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                    }
                    case "secondClass":
                        switch(berth){
                            case "Lower":
                                String q1="INSERT INTO `secondclasscancel`(`trainNum`, `lowers`) VALUES (?,?)";
                                st=con.prepareStatement(q1);
                                st.setString(1, tnum);
                                st.setInt(2, seatno);
                                st.execute();
                                break;
                            case "Upper":
                                String q2="INSERT INTO `secondclasscancel`(`trainNum`, `uppers`) VALUES (?,?)";
                                st=con.prepareStatement(q2);
                                st.setString(1, tnum);
                                st.setInt(2, seatno);
                                st.execute();
                                break;
                            case "Side Upper":
                                String q3="INSERT INTO `secondclasscancel`(`trainNum`, `sideuppers`) VALUES (?,?)";
                                st=con.prepareStatement(q3);
                                st.setString(1, tnum);
                                st.setInt(2, seatno);
                                st.execute();
                                break;
                            case "Side Lower":
                                String q4="INSERT INTO `secondclasscancel`(`trainNum`, `sidelowers`) VALUES (?,?)";
                                st=con.prepareStatement(q4);
                                st.setString(1, tnum);
                                st.setInt(2, seatno);
                                st.execute();
                                break;
                }
                case "sleeperClass":
                    switch(berth){
                        case "Lower":
                            String q1="INSERT INTO `sleeperclasscancel`(`trainNum`, `lowers`) VALUES (?,?)";
                            st=con.prepareStatement(q1);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                        case "Upper":
                            String q2="INSERT INTO `sleeperclasscancel`(`trainNum`, `uppers`) VALUES (?,?)";
                            st=con.prepareStatement(q2);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                        case "Middle":
                            String q5="INSERT INTO `sleeperclasscancel`(`trainNum`, `middles`) VALUES (?,?)";
                            st=con.prepareStatement(q5);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                        case "Side Upper":
                            String q3="INSERT INTO `sleeperclasscancel`(`trainNum`, `sideuppers`) VALUES (?,?)";
                            st=con.prepareStatement(q3);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
                            st.execute();
                            break;
                        case "Side Lower":
                            String q4="INSERT INTO `sleeperclasscancel`(`trainNum`, `sidelowers`) VALUES (?,?)";
                            st=con.prepareStatement(q4);
                            st.setString(1, tnum);
                            st.setInt(2, seatno);
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