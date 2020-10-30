/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Admin.AdminLogin;
import Admin.Admindetail;
import User.UserLogin;
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
public class AdminLoginRequest implements Serializable{
    private final Connection con;
    private static Statement stmt;
    private PreparedStatement st;
    String Adminid, Adminpass;
    public AdminLoginRequest(Admindetail adminl) throws SQLException{ //Contructor passing object of AdminDetail class
        this.con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        Adminid=adminl.adminid;
        Adminpass=adminl.adminpass;
        
    }
    public boolean checkadminlogininfo() {
        
        try{
            System.out.println("reached checkadminloginifo");
            System.out.println(Adminid +Adminpass);
            String query="Select AdminPass From adminaccount where AdminId='"+(Adminid)+"';";
            st = con.prepareStatement(query);
    
            
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String actual_password=rs.getString("adminPass");
            rs.close();
            if (actual_password.equals(Adminpass)){
                
                return true;
            }
            else{
                return false;
            }
           
        }catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println(ex);
            return false;
            
        }catch(Exception e){
            //System.out.println(e);
            System.out.println(e);
            return false;
            
        }
        
    }
}
