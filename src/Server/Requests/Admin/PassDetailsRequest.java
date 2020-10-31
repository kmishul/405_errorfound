/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.Admin;

import Commmon_LockdownTraveller.*;
import Server.DBConnect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmish
 */
public class PassDetailsRequest implements Serializable{
    private final Connection con;
    private PreparedStatement stmt;
    private ArrayList<PassDetail> pd=new ArrayList();
    
    public PassDetailsRequest(){
         con = DBConnect.con;
    }
    //Method to fetch all the details of passengers and storing the object in arraylist
    public String getPassengers() {
        String Response="";
        try{
            String q2="SELECT * FROM passengerdetail;";
            stmt=con.prepareStatement(q2);
            ResultSet rs=stmt.executeQuery(q2);
            while(rs.next()){
                PassDetail p=new PassDetail();
                p.settrainNum(rs.getString("trainNum"));
                p.setuserId(rs.getString("userId"));
                p.setpassclass(rs.getString("passclass"));
                p.setberth(rs.getString("berth"));
                p.setseatno(rs.getInt("passseatNo"));
                p.setticketid(rs.getString("passengerTicketId"));
                p.setfname(rs.getString("passengerFirstName"));
                p.setlname(rs.getString("passengerLastName"));
                p.setage(rs.getInt("passengerAge"));
                p.setgender(rs.getString("passengergender"));
                p.setdate(rs.getDate("travdate"));
                p.setfare(rs.getInt("fare"));
                pd.add(p);
            Response="valid";
            }
           
    
    
    return Response;
        
    }
        catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
            }
        }
//Method returning final arraylist of all objects of passengerdetails
    public ArrayList<PassDetail> getList() {
        return pd;
        }
    
}
