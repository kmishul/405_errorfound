/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.Admin;

import Commmon_LockdownTraveller.*;
import Server.DBConnect;
import java.awt.HeadlessException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

/**
 *
 * @author kmish
 */
public class AdminLoginRequest implements Serializable{
    private final Connection con;
    private static Statement stmt;
    private PreparedStatement st;
    String Adminid, Adminpass;

    public AdminLoginRequest(AdminDetail adminl){
         con = DBConnect.con;
         Adminid=adminl.adminid;
        Adminpass=adminl.adminpass;  
    }
    //Method returning true if password entered by user is correct else returns false
    public boolean checkadminlogininfo() {
        
        try{
            System.out.println("reached checkadminloginifo");
            System.out.println(Adminid +Adminpass);
            String query="Select AdminPass From adminaccount where AdminId='"+(Adminid)+"';";
            st = con.prepareStatement(query);
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String actual_password=getDecoded(rs.getString("adminPass"));
            rs.close();
            if (actual_password.equals(Adminpass)){   
                return true;
            }
            else{
                return false;
            }
           
        }catch (HeadlessException | SQLException ex) {
            System.out.println(ex);
            return false;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
            
        }
        
    }
    private static String getDecoded(String hashed){
        return new String(Base64.getMimeDecoder().decode(hashed));
    }
}
