/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.ViewTrain;
import Admin.ViewTrains;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kmish
 */
public class ViewTrainsRequest implements Serializable {
    private final Connection con;
    private PreparedStatement stmt;
    private ArrayList<ViewTrain> vt=new ArrayList<>();
    public ViewTrainsRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    
    public String getTrain()
    {   System.out.println("checkgettrain\n");
        String Response="";
        try{
            String q1="SELECT * FROM traininfo;";
            stmt=con.prepareStatement(q1);
            ResultSet rs=stmt.executeQuery();
            System.out.println("checexecute1\n");
            String[] arr=new String[]{"M ","T ","W ","TH ","F ","SAT ","SUN "} ;
            while(rs.next()){
                ViewTrain v=new ViewTrain();
                String status,d1,days="";
                if(rs.getInt("cancel")==1)
                status="Cancelled";
                else status="Running";
                
                 d1=rs.getString("days");
                for(int i=0;i<7;i++)
                    if(d1.charAt(i)=='1') days+=arr[i];
      
                
                
                v.settrainNum(rs.getString("trainNum"));
                v.settrainName(rs.getString("trainName"));
                v.setfstation(rs.getString("firstStation"));
                v.setlstation(rs.getString("lastStation"));
                v.setdtime(rs.getString("departureTime"));
                v.setatime(rs.getString("arrivalTime"));
                v.setfee1(rs.getInt("feeFirstClass"));
                v.setfee2(rs.getInt("feeSecondClass"));
                v.setfee3(rs.getInt("feeSleeperClass"));
                v.setdays(days);
                v.setstatus(status);
                
                
                    vt.add(v);
              
           Response="valid";
            }
           
               
           
    
    
   return Response;
        
    }
        catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            System.out.println("checexecutecatch\n");
            return "invalid";
            
            }        
        
        }

    public ArrayList<ViewTrain> getList() {
    return vt;
    }
}
