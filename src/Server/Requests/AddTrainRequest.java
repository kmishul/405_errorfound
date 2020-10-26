/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.AddTrain;
import Admin.ViewTrain;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Folio
 */
public class AddTrainRequest {
    private final Connection con;
    private PreparedStatement st;
    private PreparedStatement stt;
    private PreparedStatement sttt;
    private PreparedStatement stttt;
    private static Statement stmt;
    String tnum,tname,startstn,stopstn,starttm,stoptm,days;
    int NOSfc,NOSsc,NOSslc,farefc,faresc,fareslc;
    public AddTrainRequest(ViewTrain train) throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        tnum=train.gettrainNum();
        tname=train.gettrainName();
        startstn=train.getfstation();
        stopstn=train.getlstation();
        starttm=train.getatime();
        stoptm=train.getdtime();
        days=train.getdays();
        NOSfc=train.getNosfc();
        NOSsc=train.getNossc();
        NOSslc=train.getNosslc();
        farefc=train.getfee1();
        faresc=train.getfee2();
        fareslc=train.getfee3();
    }
    public boolean addtrain() throws SQLException{
        System.out.println("Recieving Train details");
        System.out.println(tnum+tname+startstn+stopstn+starttm+stoptm+NOSfc+NOSsc+NOSslc+farefc+faresc+fareslc+days);
        try{
        if(!checktrainname(tname)){
            System.out.println("after queries");
            st=con.prepareStatement("INSERT INTO traininfo(`trainNum`, `trainName`, `firstStation`, `lastStation`, `departureTime`, `arrivalTime`, `feeFirstClass`, `feeSecondClass`, `feeSleeperClass`, `days`) VALUES (?,?,?,?,?,?,?,?,?,?)");
            st.setString(1,tnum);
            st.setString(2,tname);
            st.setString(3,startstn);
            st.setString(4,stopstn);
            st.setString(5,starttm);
            st.setString(6,stoptm);
            st.setInt(7,farefc);
            st.setInt(8,faresc);
            st.setInt(9,fareslc);
            st.setString(10,days);
            st.execute();
            stt=con.prepareStatement("INSERT INTO firstclass(`trainNum`, `totalseats`) VALUES (?,?)");
            stt.setString(1,tnum);
            stt.setInt(2,NOSfc);
            stt.execute();
            sttt=con.prepareStatement("INSERT INTO secondclass(`trainNum`, `totalseats`) VALUES (?,?)");
            sttt.setString(1,tnum);
            sttt.setInt(2,NOSsc);
            sttt.execute();
            stttt=con.prepareStatement("INSERT INTO sleeperclass(`trainNum`, `totalseats`) VALUES (?,?)");
            stttt.setString(1,tnum);
            stttt.setInt(2,NOSslc);
            stttt.execute();
            System.out.println("after execution\n");
            return true;
        }
        else{
            System.out.println("Train name already exists\n");
            return false;
        }
        } catch(SQLException e)
        {
            System.out.println("Server Error"+e.getMessage());
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
               // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }
            
        } catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("checkTrainname\n");
        }
        
        return tname_exist;
    }
}
