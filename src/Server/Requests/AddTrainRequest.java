/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.AddTrain;
import Admin.ViewTrain;
import java.awt.HeadlessException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Folio
 */
public class AddTrainRequest implements Serializable{
    private final Connection con;
    private PreparedStatement st;
    private PreparedStatement stt;
    private PreparedStatement sttt;
    private PreparedStatement stttt;
    private static Statement stmt;
    private String tnum,tname,startstn,stopstn,starttm,stoptm,days;
    private int NOSfc,NOSsc,NOSslc,farefc,faresc,fareslc,dmc;
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
        dmc=train.getdmc();
        
    }
    
    private ArrayList<Date> getDates(String rundays){
    ArrayList<Date> dates=new ArrayList();
    int i,arr[]=new int[7];
    if(rundays.charAt(6)=='1') arr[0]=1;
    else arr[0]=0;
    for(i=0;i<6;i++)
        if(rundays.charAt(i)=='1') arr[i+1]=1;
        else arr[i+1]=0;
    
    for(i=0;i<30;i++)
    {   Date date=java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(i));
         if(arr[date.getDay()]==1)
             dates.add(date);
    }
    
    for(i=0;i<dates.size();i++)
            System.out.print(dates.get(i));
            
    return dates;
    }
    public boolean addtrain() throws SQLException{
        System.out.println("Receiving Train details");
        System.out.println(tnum+tname+startstn+stopstn+starttm+stoptm+NOSfc+NOSsc+NOSslc+farefc+faresc+fareslc+days);
        try{
        if(!checktrainname(tname)){
            System.out.println("after queries");
            st=con.prepareStatement("INSERT INTO traininfo(`trainNum`, `trainName`, `firstStation`, `lastStation`, `departureTime`, `arrivalTime`, `feeFirstClass`, `feeSecondClass`, `feeSleeperClass`, `days`,`dmc`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
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
            st.setInt(11, dmc);
            st.execute();
            
            ArrayList<Date> dates=getDates(days);
            
            for(int i=0;i<dates.size();i++){
               java.util.Date utilObj = dates.get(i);
            java.sql.Date sqldate = new java.sql.Date(utilObj.getTime());
            stt=con.prepareStatement("INSERT INTO firstClass(`trainNum`, `totalseats`,`rundate`) VALUES (?,?,?)");
            stt.setString(1,tnum);
            stt.setInt(2,NOSfc);
            stt.setDate(3,sqldate);
            stt.execute();
            sttt=con.prepareStatement("INSERT INTO secondClass(`trainNum`, `totalseats`,`rundate`) VALUES (?,?,?)");
            sttt.setString(1,tnum);
            sttt.setInt(2,NOSsc);
            sttt.setDate(3,sqldate);
            sttt.execute();
            stttt=con.prepareStatement("INSERT INTO sleeperClass(`trainNum`, `totalseats`,`rundate`) VALUES (?,?,?)");
            stttt.setString(1,tnum);
            stttt.setInt(2,NOSslc);
            stttt.setDate(3,sqldate);
            stttt.execute();
            }
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
