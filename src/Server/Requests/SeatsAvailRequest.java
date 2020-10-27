/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Folio
 */
public class SeatsAvailRequest {
    private final Connection con;
    private PreparedStatement st;
     private PreparedStatement st1;
      private PreparedStatement st2;
    public SeatsAvailRequest() throws SQLException{
         con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    public boolean checktrainname(String trainname){
        
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
               // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }
            
        } catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("checkTrainname\n");
        }
        
        return tname_exist;
    }
    public int getseatsfc(String tnum) throws SQLException{
        int rem;
        String q1="SELECT * FROM firstclass WHERE trainNum=?";
        st=con.prepareStatement(q1);
        st.setString(1, tnum);
        ResultSet rs=st.executeQuery();
        rs.next();
        int total=rs.getInt("totalseats");
        int lo=rs.getInt("lowers");
        int up=rs.getInt("uppers");
        int su=rs.getInt("sideuppers");
        int sl=rs.getInt("sidelowers");
        rem=total-(lo+up+su+sl);
        return rem;
    }
    public int getseatssc(String tnum) throws SQLException{
        int rem;
        String q1="SELECT * FROM secondclass WHERE trainNum=?";
        st1=con.prepareStatement(q1);
        st1.setString(1, tnum);
        ResultSet rs=st1.executeQuery();
        rs.next();
        int total=rs.getInt("totalseats");
        int lo=rs.getInt("lowers");
        int up=rs.getInt("uppers");
        int su=rs.getInt("sideuppers");
        int sl=rs.getInt("sidelowers");
        rem=total-(lo+up+su+sl);
        return rem;
    }
    public int getseatsslc(String tnum) throws SQLException{
        int rem;
        String q1="SELECT * FROM sleeperclass WHERE trainNum=?";
        st2=con.prepareStatement(q1);
        st2.setString(1, tnum);
        ResultSet rs=st2.executeQuery();
        rs.next();
        int total=rs.getInt("totalseats");
        int lo=rs.getInt("lowers");
        int up=rs.getInt("uppers");
        int su=rs.getInt("sideuppers");
        int sl=rs.getInt("sidelowers");
        int md=rs.getInt("middles");
        rem=total-(lo+up+su+sl+md);
        return rem;
    }
}
