/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetails;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmish
 */
public class PassDetailsRequest {
    private final Connection con;
    private PreparedStatement stmt;
    private ArrayList<PassDetails> pd=new ArrayList();
    public PassDetailsRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    
    

    public String getPassengers() {
        String Response="";
        try{
            String q2="SELECT * FROM passengerdetail";
            ResultSet rs=stmt.executeQuery(q2);
            while(rs.next()){
                PassDetails p=new PassDetails();
                p.settrainNum(rs.getString("trainNum"));
                p.setuserId(rs.getString("userId"));
                p.setpassclass(rs.getString("passclass"));
                p.setseatno(rs.getInt("passseatNo"));
                p.setticketid(rs.getString("passengerTicketId"));
                p.setfname(rs.getString("passengerFirstName"));
                p.setlname(rs.getString("passengerLastName"));
                p.setage(rs.getInt("passengerAge"));
                p.setgender(rs.getString("passengergender"));
                p.setdate(rs.getString("travdate"));
                pd.add(p);
            }
            Response="valid";
    
    
    return Response;
        
    }
        catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
            }
        }

    public ArrayList<PassDetails> getList() {
        return pd;
        }
    
}
