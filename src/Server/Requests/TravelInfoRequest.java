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
public class TravelInfoRequest {
     private final Connection con;
    private PreparedStatement stmt1;
    private PreparedStatement stmt2;
    private PreparedStatement stmt3;
    private ArrayList<PassDetail> pd=new ArrayList();
    private ArrayList<ViewTrain> vt=new ArrayList();
    public TravelInfoRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    
    

    public String getInfo(UserDetail uid) {
        String Response="";
        try{ 
            String q1="SELECT * FROM passengerdetail WHERE userId=?";
            stmt1=con.prepareStatement(q1);
            stmt1.setString(1,uid.getUserid());
            ResultSet rs1=stmt1.executeQuery();
                String tnum=rs1.getString("trainNum");
            String q2="SELECT * FROM traininfo WHERE trainNum=?";
            stmt2=con.prepareStatement(q2);
            stmt2.setString(1,tnum);
            ResultSet rs2=stmt2.executeQuery();
            
            while(rs1.next() && rs2.next()){
                PassDetail p=new PassDetail();
                ViewTrain v=new ViewTrain();
                
                p.settrainNum(rs1.getString("trainNum"));
                p.setuserId(rs1.getString("userId"));
                p.setpassclass(rs1.getString("passclass"));
                p.setseatno(rs1.getInt("passseatNo"));
                p.setticketid(rs1.getString("passengerTicketId"));
                p.setfname(rs1.getString("passengerFirstName"));
                p.setlname(rs1.getString("passengerLastName"));
                p.setage(rs1.getInt("passengerAge"));
                p.setgender(rs1.getString("passengergender"));
                p.setdate(rs1.getString("travdate"));
                
                v.settrainNum(rs2.getString("trainNum"));
                v.settrainName(rs2.getString("trainName"));
                v.setfstation(rs2.getString("firstStation"));
                v.setlstation(rs2.getString("lastStation"));
                v.setdtime(rs2.getString("departureTime"));
                v.setatime(rs2.getString("arrivalTime"));
                v.setfee1(rs2.getInt("feeFirstClass"));
                v.setfee2(rs2.getInt("feeSecondClass"));
                v.setfee3(rs2.getInt("feeSleeperClass"));
                v.setdays(rs2.getString("days"));
                v.setcancel(rs2.getInt("cancel"));
                
                 
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

    
   public ArrayList<PassDetail> getPassList() {
        return pd;
        }
   public ArrayList<ViewTrain> getTrainList() {
        return vt;
        }
   
}
