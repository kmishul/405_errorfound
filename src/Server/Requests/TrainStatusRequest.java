/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.PassDetail;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public TrainStatusRequest(PassDetail pass) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        tnum=pass.gettrainNum();
        Date date=pass.getdate();
         j=date.getDay();
    }

    //Method returning 
    public int getTrainStatus() throws SQLException{
        try{
        String query1="Select * From traininfo where trainNum='"+(tnum)+"';";
        st = con.prepareStatement(query1);
        ResultSet rs=st.executeQuery(query1);
        int c=-1;
        if(rs.next())
        { c=rs.getInt("cancel");
        if(c==0){
            String s="";
            String query2="Select days From traininfo where trainNum='"+(tnum)+"';";
            st = con.prepareStatement(query2);
            ResultSet rs1=st.executeQuery(query2);
            if(rs1.next())
                 s=rs1.getString("days");
            
            int temp[]=new int[7];
            if(s.charAt(6)=='1') temp[0]=1;
            else temp[0]=0;
            
            
            for(int i=0;i<6;i++)
             if(s.charAt(i)=='1') temp[i+1]=1;
            else temp[i+1]=0;
               
         if(temp[j]==1)
             return 1;
         else return 2;
            
        }
        else return 0;  //cancelled
            
        } return -1;
        
    }catch(SQLException e)
        {
            System.out.println("Server Error"+e.getMessage());
            return -1;
        }
  
    }
}

