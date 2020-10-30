/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.CancelTrain;
import Admin.RemoveTrain;
import Admin.ViewTrain;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kmish
 */
public class CancelTrainRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    private static Statement stmt;
    private String tnum;

    public CancelTrainRequest(ViewTrain train) throws SQLException {
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        tnum=train.gettrainNum();
    }
    //Method returning true on successfully cancelling train and false if train does not exist or already cancelled
    public boolean canceltrain() throws SQLException{
        try{
        System.out.println("cancelling..");
        System.out.println(tnum);
        String query="Select cancel From traininfo where trainNum='"+(tnum)+"';";
        st = con.prepareStatement(query);
        ResultSet rs=st.executeQuery(query);
        rs.next();
        int can=rs.getInt("cancel");
        if(can==0){
            String query2="UPDATE traininfo SET cancel=1 WHERE trainNum='"+(tnum)+"';";
            st=con.prepareStatement(query2);
            st.execute();
            rs.close();
            return true;
        }
        else{
            System.out.println("error:train already cancelled");
            return false;
        }
    }catch(SQLException e)
        {
            System.out.println("Server Error"+e.getMessage());
            return false;
        }
    }
    public boolean uncanceltrain() throws SQLException{
         try{
        System.out.println("uncancelling..");
        System.out.println(tnum);
        String query="Select cancel From traininfo where trainNum='"+(tnum)+"';";
        st = con.prepareStatement(query);
        ResultSet rs=st.executeQuery(query);
        rs.next();
        int can=rs.getInt("cancel");
        if(can==1){
            String query2="UPDATE traininfo SET cancel=0 WHERE trainNum='"+(tnum)+"';";
            st=con.prepareStatement(query2);
            st.execute();
            rs.close();
            return true;
        }
        else{
            System.out.println("error:train is not cancelled");
            return false;
        }
    }catch(SQLException e)
        {
            System.out.println("Server Error"+e.getMessage());
            return false;
        }
    }
}
