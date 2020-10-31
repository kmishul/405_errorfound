/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.ViewTrain;
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
public class SearchTrainRequest implements Serializable {
    private final Connection con;
    private PreparedStatement stmt;
    private ArrayList<ViewTrain> vt=new ArrayList<>();
    
    public SearchTrainRequest() {
        con = DBConnect.con;
    }
    //Method returning valid if there are trains between the givenm stations and inserting train object in arraylist else false
     public String getTrain(String s1,String s2){   
        System.out.println("checkgettrain\n");
        String Response="";
        try{
            String q1="SELECT * FROM traininfo WHERE firstStation=? AND lastStation=?";
            stmt=con.prepareStatement(q1);
            stmt.setString(1, s1);
            stmt.setString(2, s2);
            ResultSet rs=stmt.executeQuery();
            System.out.println("checexecute1\n");
            while(rs.next()){
                ViewTrain v=new ViewTrain();
                String d=rs.getString("days");
                String f="";
                if(d.charAt(0)=='1')
                    f=f+"Mon/";
                if(d.charAt(1)=='1')
                    f=f+"Tue/";
                if(d.charAt(2)=='1')
                    f=f+"Wed/";
                if(d.charAt(3)=='1')
                    f=f+"Thu/";
                if(d.charAt(4)=='1')
                    f=f+"Fri/";
                if(d.charAt(5)=='1')
                    f=f+"Sat/";
                if(d.charAt(6)=='1')
                    f=f+"Sun/";
                f=f.substring(0,f.length()-1);
                v.settrainNum(rs.getString("trainNum"));
                System.out.println(rs.getString("trainNum"));
                v.settrainName(rs.getString("trainName"));
                v.setfstation(rs.getString("firstStation"));
                v.setlstation(rs.getString("lastStation"));
                v.setdtime(rs.getString("departureTime"));
                v.setatime(rs.getString("arrivalTime"));
                v.setfee1(rs.getInt("feeFirstClass"));
                v.setfee2(rs.getInt("feeSecondClass"));
                v.setfee3(rs.getInt("feeSleeperClass"));
                v.setdays(f);
                v.setcancel(rs.getInt("cancel"));
                v.setdmc(rs.getInt("dmc"));
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
//Method returning final arraylist
    public ArrayList<ViewTrain> getList() {
        return vt;
    }
}
