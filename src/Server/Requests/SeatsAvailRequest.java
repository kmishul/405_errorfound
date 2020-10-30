/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import java.awt.HeadlessException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Folio
 */
public class SeatsAvailRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
     private PreparedStatement st1;
      private PreparedStatement st2;
      java.sql.Date sqldate;
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
    //Class wise methods returning remaining seats in that class of given train number and on given date
    public int getseatsfc(String tnum,Date date) throws SQLException{
        int rem;
        java.util.Date utilObj = date;
        sqldate = new java.sql.Date(utilObj.getTime());
        String q1="SELECT * FROM firstClass WHERE trainNum=? AND rundate=?";
        st=con.prepareStatement(q1);
        st.setString(1, tnum);
        st.setDate(2, sqldate);
        ResultSet rs=st.executeQuery();
        rs.next();
        int total=rs.getInt("totalseats");
        int lo=rs.getInt("lowers");
        int up=rs.getInt("uppers");
        int su=rs.getInt("sideuppers");
        int sl=rs.getInt("sidelowers");
        rem=total-(lo+up+su+sl)+getcancelfc(tnum, date);
        return rem;
    }
    public int getseatssc(String tnum,Date date) throws SQLException{
        int rem;
        java.util.Date utilObj = date;
        sqldate = new java.sql.Date(utilObj.getTime());
        String q1="SELECT * FROM secondClass WHERE trainNum=? AND rundate=?";
        st1=con.prepareStatement(q1);
        st1.setString(1, tnum);
        st1.setDate(2, sqldate);
        ResultSet rs=st1.executeQuery();
        rs.next();
        int total=rs.getInt("totalseats");
        int lo=rs.getInt("lowers");
        int up=rs.getInt("uppers");
        int su=rs.getInt("sideuppers");
        int sl=rs.getInt("sidelowers");
        rem=total-(lo+up+su+sl)+getcancelsc(tnum, date);
        return rem;
    }
    public int getseatsslc(String tnum,Date date) throws SQLException{
        int rem;
        java.util.Date utilObj = date;
        sqldate = new java.sql.Date(utilObj.getTime());
        String q1="SELECT * FROM sleeperClass WHERE trainNum=? AND rundate=?";
        st2=con.prepareStatement(q1);
        st2.setString(1, tnum);
        st2.setDate(2, sqldate);
        ResultSet rs=st2.executeQuery();
        rs.next();
        int total=rs.getInt("totalseats");
        int lo=rs.getInt("lowers");
        int up=rs.getInt("uppers");
        int su=rs.getInt("sideuppers");
        int sl=rs.getInt("sidelowers");
        int md=rs.getInt("middles");
        rem=total-(lo+up+su+sl+md)+getcancelslc(tnum, date);
        return rem;
    }
    //Class wise method returning number of seats cancelled in given train number and on given date
    private int getcancelfc(String tnum,Date date) throws SQLException{
        String q1="SELECT * FROM firstClasscancel WHERE trainNum=? AND rundate=?";
        int count=0;
        st=con.prepareStatement(q1);
        st.setString(1, tnum);
        st.setDate(2, sqldate);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            count++;
        }
        return count;
    }
    private int getcancelsc(String tnum,Date date) throws SQLException{
        String q1="SELECT * FROM secondClasscancel WHERE trainNum=? AND rundate=?";
        int count=0;
        st=con.prepareStatement(q1);
        st.setString(1, tnum);
        st.setDate(2, sqldate);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            count++;
        }
        return count;
    }
    private int getcancelslc(String tnum,Date date) throws SQLException{
        String q1="SELECT * FROM sleeperClasscancel WHERE trainNum=? AND rundate=?";
        int count=0;
        st=con.prepareStatement(q1);
        st.setString(1, tnum);
        st.setDate(2, sqldate);
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            count++;
        }
        return count;
    }
}
