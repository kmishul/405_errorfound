/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Server.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Folio
 */
public class NotificationRequest {
    private final Connection con;
    private PreparedStatement st;
    private ArrayList<String> vt=new ArrayList<>();
    
    public NotificationRequest() {
         con = DBConnect.con;
    }
    //Method returning true if user has tickets on next day and fetching details from database about tickets else false
    public boolean getrecent(String uid) throws SQLException{
        System.out.println("get recent method");
        boolean r=false;
        Date date=java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(1));
        String q1="SELECT * FROM passengerdetail WHERE userId=? AND travdate=?";
        st=con.prepareStatement(q1);
        st.setString(1, uid);
        st.setDate(2, (java.sql.Date) date);
        ResultSet rs=st.executeQuery();
        System.out.println("after query");
        while(rs.next()){
            String s="";
            String tnum=rs.getString("trainNum");
            String ticketid=rs.getString("passengerTicketId");
            String q2="SELECT * FROM traininfo WHERE trainNum=?";
            st=con.prepareStatement(q2);
            st.setString(1, tnum);
            ResultSet rs1=st.executeQuery();
            rs1.next();
            String starttm=rs1.getString("departureTime");
            s=s+tnum+"~"+ticketid+"~"+starttm;
            vt.add(s);
            r=true;
            System.out.println("return true");
        }
        return r;
    }
    //method returning array list of strings with all details of ticket
    public ArrayList<String> getList() {
    return vt;
    }
}
