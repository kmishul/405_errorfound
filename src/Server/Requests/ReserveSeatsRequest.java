/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
    public ReserveSeatsRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        //String trainNum=pass.trainNum;
    }
    public void bookticket(PassDetail pass) throws SQLException{
        passclass=pass.getpassclass();
        berth=pass.getberth();
        if(passclass.equalsIgnoreCase("First AC")){
            String q1="SELECT * FROM firstclass";
            st=con.prepareStatement(q1);
            Resultset rs=(Resultset) st.executeQuery();
           if(berth.equalsIgnoreCase("Lower")){
               
           }
        }
    }
    
}
