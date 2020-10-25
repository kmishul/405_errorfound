/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
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
    public boolean bookticket(PassDetail pass){
        passclass=pass.getpassclass();
        if(passclass.equalsIgnoreCase("First AC")){
           
        }
    }
    
}
