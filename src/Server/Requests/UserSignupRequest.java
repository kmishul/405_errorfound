/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Server.DBConnect;
import Server.Server;
import User.UserDetail;
import User.UserSignup;
import java.awt.HeadlessException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

/**
 *
 * @author kmish
 */
public class UserSignupRequest implements Serializable{
    private final Connection con;
    private static Statement stmt;
    private PreparedStatement st;
    private String userid,fname,lname,emailid,pass,contact,gender;
    public UserSignupRequest(UserDetail user) throws SQLException{
        this.con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        userid=user.userid;
        fname=user.fname;
        lname=user.lname;
        emailid=user.emailid;
        pass=getEncoded(user.pass);
        contact=user.contact;
        gender=user.gender;
    }
    public boolean adduser(){
        System.out.println("Receiving details");
        //String[] data=new String[7];
        //data=details.split("~");
        System.out.println(userid+fname+lname+emailid+pass+contact+gender);
        //PreparedStatement pss;
        try
        {
           // if(!checkUsername(user_name)){
           
            if(!checkUsername(userid)){
                //String q1="INSERT INTO `userlogin`(`userId`, `userFirstName`, `userLastName`, `userEmailid`, `userPass`, `userContactNo`, `userGender`) VALUES (?,?,?,?,?,?,?)";
                st=con.prepareStatement("INSERT INTO userlogin "
                        + "(`userId`, `userFirstName`, `userLastName`, `userEmailid`, `userPass`,"
                        + " `userContactNo`, `userGender`) VALUES (?,?,?,?,?,?,?)");
                st.setString(1, userid);
                st.setString(2, fname);
                st.setString(3, lname);
                st.setString(4, emailid);
                st.setString(5, pass);
                st.setString(6, contact);
                st.setString(7, gender);
                st.execute();
                return true;
            }
            else
                return false;
        }
        catch(SQLException e)
        {
            System.out.println("Server Error"+e.getMessage());
            return false;
        }
    }
    private boolean checkUsername(String username){
        
        //PreparedStatement st;
        //ResultSet rs;
        boolean username_exist = false;
        
        //String query = "SELECT * FROM `users` WHERE `userId` = ?";
        
        try {
            String query = "SELECT * FROM `userlogin` WHERE `userId` = ?";
            //con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            st = con.prepareStatement(query);
            //stmt.setString(1,username);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                username_exist = true;
               // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }
            
        } catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("checkusername\n");
            System.out.println(ex);
        }catch(Exception e){
            //System.out.println(e);
            System.out.println(e);
            System.out.println("\n 2");
        }
        
        return username_exist;
    }
    private String getEncoded(String valueOf) {
        
        return Base64.getEncoder().encodeToString(valueOf.getBytes());
    }
}
