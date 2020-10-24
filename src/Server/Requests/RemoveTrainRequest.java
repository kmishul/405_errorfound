/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.RemoveTrain;
import java.awt.HeadlessException;
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
public class RemoveTrainRequest {
    private final Connection con;
    private PreparedStatement st;
    private PreparedStatement stt;
    private PreparedStatement sttt;
    private PreparedStatement stttt;
    private PreparedStatement sttttt;
    private static Statement stmt;
    String tnum;
    public RemoveTrainRequest(RemoveTrain train) throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        tnum=train.tnum;
    }
    public boolean removetrain() throws SQLException{
        try{
            if(checktrainname(tnum)){
                System.out.println("removing..");
                System.out.println(tnum);
                st=con.prepareStatement("DELETE FROM `traininfo` WHERE trainNum=?");
                st.setString(1, tnum);
                stt=con.prepareStatement("DELETE FROM `firstclass` WHERE trainNum=?");
                stt.setString(1, tnum);
                sttt=con.prepareStatement("DELETE FROM `secondclass` WHERE trainNum=?");
                sttt.setString(1, tnum);
                stttt=con.prepareStatement("DELETE FROM `sleeperclass` WHERE trainNum=?");
                stttt.setString(1, tnum);
                if(checktrainpass(tnum)){
                    sttttt=con.prepareStatement("DELETE FROM `passengerdetail` WHERE trainNum=?");
                    sttttt.setString(1, tnum);
                    sttttt.execute();
                }
                else{
                    System.out.println("not in passdetail");
                }
                stt.execute();
                sttt.execute();
                stttt.execute();
                //sttttt.execute();
                st.execute();
                return true;
            }
            else{
                System.out.println("1.Train number does not exist\n");
                return false;
            }
        }catch(SQLException e){
            System.out.println("error in query\n");
            System.out.println(e);
            return false;
        }
    }
    private boolean checktrainname(String trainname){
        
        //PreparedStatement st;
        //ResultSet rs;
        boolean tname_exist = false;
        
        //String query = "SELECT * FROM `users` WHERE `userId` = ?";
        
        try {
            String query = "SELECT * FROM `traininfo` WHERE `trainNum` = ?";
            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            st = con.prepareStatement(query);
            st.setString(1,trainname);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                tname_exist = true;
                System.out.println("train name true\n");
                return true;
               // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }
            else{
                System.out.println("train name false\n");
                return false;
            }
            
        } catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("error in checkTrainname\n");
            return false;
        }
        
        //return tname_exist;
    }
    private boolean checktrainpass(String trainname){
        
        //PreparedStatement st;
        //ResultSet rs;
        boolean tname_exist = false;
        
        //String query = "SELECT * FROM `users` WHERE `userId` = ?";
        
        try {
            String query = "SELECT * FROM `passengerdetail` WHERE `trainNum` = ?";
            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            st = con.prepareStatement(query);
            st.setString(1,trainname);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                tname_exist = true;
                System.out.println("train name passenger true\n");
                return true;
               // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
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
