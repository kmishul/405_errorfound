/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.RemoveTrain;
import Admin.ViewTrain;
import java.awt.HeadlessException;
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
public class RemoveTrainRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    private PreparedStatement st2;
    private PreparedStatement st3;
    private PreparedStatement st4;
    private PreparedStatement st5;
    private PreparedStatement st6;
    private static Statement stmt;
    private String tnum;
    public RemoveTrainRequest(ViewTrain train) throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        tnum=train.gettrainNum();
    }
    //Method to delete train information from traininfo and all classes tables
    public String removetrain() throws SQLException{
        try{
            if(checktrainname(tnum)){  //to check whether that train number exist or not
                if(!checktrainpass(tnum)){  //to check if that train has seats reserved
                    System.out.println("removing..");
                    System.out.println(tnum);
                    st6=con.prepareStatement("SET foreign_key_checks=0;");
                    st6.execute();
                    st=con.prepareStatement("DELETE FROM `traininfo` WHERE trainNum=?");
                    st.setString(1, tnum);
                    st5=con.prepareStatement("DELETE FROM `firstclass` WHERE trainNum=?");
                    st5.setString(1, tnum);
                    st2=con.prepareStatement("DELETE FROM `secondclass` WHERE trainNum=?");
                    st2.setString(1, tnum);
                    st3=con.prepareStatement("DELETE FROM `sleeperclass` WHERE trainNum=?");
                    st3.setString(1, tnum);
                    st3.execute();
                    st2.execute();
                    st5.execute();
                    st.execute();
                    return "Train Removed Successfully";
                }
                else{
                    System.out.println("not in passdetail");
                    return "This train has seats reserved,can't be deleted";
                }
            }
            else{
                System.out.println("1.Train number does not exist\n");
                return "Train Number does not Exist";
            }
        }catch(SQLException e){
            System.out.println("error in query\n");
            System.out.println(e);
            return "Error:Wrong credential";
        }
    }
    private boolean checktrainname(String trainname){
   
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
                return true;

            }
            else{
                System.out.println("train name false\n");
                return false;
            }
            
        } catch (HeadlessException | SQLException ex) {
            System.out.println("error in checkTrainname\n");
            return false;
        }
        
        //return tname_exist;
    }
    private boolean checktrainpass(String trainname){
        
        boolean tname_exist = false;
        try {
            String query = "SELECT * FROM `passengerdetail` WHERE `trainNum` = ?";
            st = con.prepareStatement(query);
            st.setString(1,trainname);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                tname_exist = true;
                System.out.println("train name passenger true\n");
                return true;
            }
            else{
                System.out.println("train name passenger false\n");
                return false;
            }
            
        } catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("error in checkTrainname\n");
            return false;
        }
        
        //return tname_exist;
    }

}
