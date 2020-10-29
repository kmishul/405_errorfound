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
    public NotificationRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
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
    public ArrayList<String> getList() {
    return vt;
    }
}
