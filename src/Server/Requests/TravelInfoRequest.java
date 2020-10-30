/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
import Admin.PassDetails;
import Admin.ViewTrain;
import User.UserDetail;
import java.io.Serializable;
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
public class TravelInfoRequest implements Serializable{
     private final Connection con;
    private PreparedStatement stmt1;
    private PreparedStatement stmt2;
    private ArrayList<PassDetail> pd=new ArrayList();
    private ArrayList<ViewTrain> vt=new ArrayList();
    public TravelInfoRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    //Method returning valid if user has travel details and fetching all the data from database
    public String getInfo(UserDetail uid) {
        String Response="";
        try{ 
            String q1="SELECT * FROM passengerdetail WHERE userId=?";
            stmt1=con.prepareStatement(q1);
            stmt1.setString(1,uid.getUserid());
            ResultSet rs1=stmt1.executeQuery();
            
            while(rs1.next())   { 
            PassDetail p=new PassDetail();
                ViewTrain v=new ViewTrain();
                String tnum=rs1.getString("trainNum");
                String q2="SELECT * FROM traininfo WHERE trainNum=?";
                stmt2=con.prepareStatement(q2);
                stmt2.setString(1,tnum);
                ResultSet rs2=stmt2.executeQuery();
                rs2.next();
                p.settrainNum(rs1.getString("trainNum"));
                p.setuserId(rs1.getString("userId"));
                p.setpassclass(rs1.getString("passclass"));
                p.setseatno(rs1.getInt("passseatNo"));
                p.setberth(rs1.getString("berth"));
                p.setticketid(rs1.getString("passengerTicketId"));
                p.setfname(rs1.getString("passengerFirstName"));
                p.setlname(rs1.getString("passengerLastName"));
                p.setage(rs1.getInt("passengerAge"));
                p.setgender(rs1.getString("passengergender"));
                p.setdate(rs1.getDate("travdate"));
                p.setfare(rs1.getInt("fare"));
                v.settrainName(rs2.getString("trainName"));
                 
                pd.add(p);
                vt.add(v);
            Response="valid";
            }
  
    return Response;
        
    }
        catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
            }
        }

    //Method returning passenger list
   public ArrayList<PassDetail> getPassList() {
        return pd;
        }
   public ArrayList<ViewTrain> getTrainList() {
        return vt;
        }
   
}
