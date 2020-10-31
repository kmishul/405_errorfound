/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
import Server.DBConnect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author kmish
 */
public class TrainStatusRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    private static Statement stmt;
    private String tnum;
    private int j;

    public TrainStatusRequest(PassDetail pass)  {
         con = DBConnect.con;
        tnum=pass.gettrainNum();
        Date date=pass.getdate();
         j=date.getDay(); //to get day number
    }

    //Method returning 
    public int getTrainStatus() throws SQLException{
        try{
        String query1="Select * From traininfo where trainNum='"+(tnum)+"';"; //to fetch current train details
        st = con.prepareStatement(query1);
        ResultSet rs=st.executeQuery(query1);
        int c=-1;
        if(rs.next())
        { c=rs.getInt("cancel");
        if(c==0){
            String s="";
            String query2="Select days From traininfo where trainNum='"+(tnum)+"';"; //fetch running days of this train
            st = con.prepareStatement(query2);
            ResultSet rs1=st.executeQuery(query2);
            if(rs1.next())
                 s=rs1.getString("days");
            
            int temp[]=new int[7]; //this array used to contain running days of this train according to their indexes
            if(s.charAt(6)=='1') temp[0]=1;
            else temp[0]=0;
            
            
            for(int i=0;i<6;i++) 
             if(s.charAt(i)=='1') temp[i+1]=1;
            else temp[i+1]=0;
               
         if(temp[j]==1)
             return 1;
         else return 2; //Train is not running on this day
            
        }
        else return 0;  //Train is Cancelled
            
        }
        return -1;//No such Train Found
        
    }catch(SQLException e)
        {
            System.out.println("Server Error"+e.getMessage());
            return -1;
        }
  
    }
}

