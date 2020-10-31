/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.User;

import Server.DBConnect;
import Commmon_LockdownTraveller.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;  

/**
 *
 * @author kmish
 */
public class TicketsRequest implements Serializable{
    private final Connection con;
    private PreparedStatement stmt1;
    private PreparedStatement stmt2;
    private ArrayList<PassDetail> pd=new ArrayList();
    private ArrayList<ViewTrain> vt=new ArrayList();
    
    public TicketsRequest(){
         con = DBConnect.con;
    }
    
    

    public String getTickets(UserDetail uid) {
        
        String Response="";
        try{ 
            String q1="SELECT * FROM passengerdetail WHERE userId=?";//we have to fetch passenger details for tickets
            stmt1=con.prepareStatement(q1);
            stmt1.setString(1,uid.getUserid());
            ResultSet rs1=stmt1.executeQuery();
 
            while(rs1.next())   { 
                int flag=0;
            PassDetail p=new PassDetail();
                ViewTrain v=new ViewTrain();
                String tnum=rs1.getString("trainNum");
                String q2="SELECT * FROM traininfo WHERE trainNum=?";//we have to fetch train details for tickets
                stmt2=con.prepareStatement(q2);
                stmt2.setString(1,tnum);
                ResultSet rs2=stmt2.executeQuery();
                rs2.next();
                p.settrainNum(rs1.getString("trainNum"));
                p.setpassclass(rs1.getString("passclass"));
                p.setberth(rs1.getString("berth"));
                p.setticketid(rs1.getString("passengerTicketId"));
                p.setfname(rs1.getString("passengerFirstName"));
                p.setlname(rs1.getString("passengerLastName"));
                p.setage(rs1.getInt("passengerAge"));
                p.setdate(rs1.getDate("travdate"));
                p.setfare(rs1.getInt("fare"));
                
                v.settrainName(rs2.getString("trainName"));
                v.setfstation(rs2.getString("firstStation"));
                v.setlstation(rs2.getString("lastStation"));
                v.setdtime(rs2.getString("departureTime"));
                v.setatime(rs2.getString("arrivalTime"));
                v.setfee1(rs2.getInt("feeFirstClass"));
                v.setfee2(rs2.getInt("feeSecondClass"));
                v.setfee3(rs2.getInt("feeSleeperClass"));
                
                Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
                if(p.getdate().after(date)) //only upcoming travels would be shown in tickets table on user profile
                    flag=1;
                
                 if(flag==1)
                 {pd.add(p);
                vt.add(v);
                
                 Response="valid";//no problem
                 }
            
            }
  
    return Response;
        
    }
        catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
            }
        }

   public ArrayList<PassDetail> getPassList() { //return array of passenger list
        return pd;
        }
   public ArrayList<ViewTrain> getTrainList() { //return array of train list
        return vt;
        }
   
}
