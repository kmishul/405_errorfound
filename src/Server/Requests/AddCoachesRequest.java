/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.ViewTrain;
import java.awt.HeadlessException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Folio
 */
public class AddCoachesRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    public AddCoachesRequest() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    //Method to add coaches in first class
    
    public boolean addfc(ViewTrain train) throws SQLException{
       String tnum=train.gettrainNum();
       if(checktrainnum(tnum)){    //if train number is there in database
            int seats=train.getNosfc();
            String q1="UPDATE firstclass SET totalseats=totalseats+? WHERE trainNum=?";
            st=con.prepareStatement(q1);
            st.setInt(1, seats);
            st.setString(2, tnum);
            st.execute();
            return true;
       }
       else{
           System.out.println("Train does not exist");
           return false;
       }
    }
     //Method to add coaches in second class
    public boolean addsc(ViewTrain train) throws SQLException{
       String tnum=train.gettrainNum();
       if(checktrainnum(tnum)){   //if train number is there in database
            int seats=train.getNossc();
            String q1="UPDATE secondclass SET totalseats=totalseats+? WHERE trainNum=?";
            st=con.prepareStatement(q1);
            st.setInt(1, seats);
            st.setString(2, tnum);
            st.execute();
            return true;
       }
       else{
           System.out.println("Train does not exist");
           return false;
       }
    }
     //Method to add coaches in sleeper class
    public boolean addslc(ViewTrain train) throws SQLException{
       String tnum=train.gettrainNum();
       if(checktrainnum(tnum)){  //if train number is there in database
            int seats=train.getNosslc();
            String q1="UPDATE sleeperclass SET totalseats=totalseats+? WHERE trainNum=?";
            st=con.prepareStatement(q1);
            st.setInt(1, seats);
            st.setString(2, tnum);
            st.execute();
            return true;
       }
       else{
           System.out.println("Train does not exist");
           return false;
       }
    }
    //method to check wheher train number sent by client exist or not
    private boolean checktrainnum(String trainname){
        
        boolean tname_exist = false;
        
        try {
            String query = "SELECT * FROM `traininfo` WHERE `trainNum` = ?";
            st = con.prepareStatement(query);
            st.setString(1,trainname);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                tname_exist = true;
                System.out.println("train name true\n");
              
            }
            
        } catch (HeadlessException | SQLException ex) {
            System.out.println("checkTrainname\n");
        }
        
        return tname_exist;
    }
}
