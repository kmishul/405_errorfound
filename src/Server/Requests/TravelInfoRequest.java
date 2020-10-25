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
    private ArrayList<UserDetail> ud=new ArrayList();
    private ArrayList<ViewTrain> vt=new ArrayList();
    public TravelInfoRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    
    

    public String getInfo() {
        String Response="";
        try{
            String q1="SELECT * FROM passengerdetail;";
            String q2="SELECT * FROM passengerdetail;";
            String q3="SELECT * FROM passengerdetail;";
            stmt1=con.prepareStatement(q1);
            stmt2=con.prepareStatement(q2);
            stmt3=con.prepareStatement(q3);
            ResultSet rs1=stmt1.executeQuery(q1);
            ResultSet rs2=stmt2.executeQuery(q2);
            ResultSet rs3=stmt3.executeQuery(q3);
            while(rs1.next() && rs2.next() && rs3.next()){
                PassDetail p=new PassDetail();
                UserDetail u=new UserDetail();
                ViewTrain v=new ViewTrain();
                
                
                
                pd.add(p);
                ud.add(u);
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

    public ArrayList<UserDetail> getUserList() {
        return ud;
        }
   public ArrayList<PassDetail> getPassList() {
        return pd;
        }
   public ArrayList<ViewTrain> getTrainList() {
        return vt;
        }
   
}
