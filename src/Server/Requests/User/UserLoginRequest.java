/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.User;
import Server.DBConnect;
import Commmon_LockdownTraveller.*;
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
public class UserLoginRequest implements Serializable{
    private final Connection con;
    private static Statement stmt;
    private PreparedStatement st;
    private String userid, password;
    public UserLoginRequest(UserDetail userl){
         con = DBConnect.con;
        userid=userl.getUserid();
        password=userl.getPass();
    }
     //Method returning true if password entered by user is correct else returns false
    public boolean checklogininfo() {
        
        try{
            System.out.println("reached checkloginifo");
            System.out.println(userid +password);
            String query="Select userPass From userlogin where userId='"+(userid)+"';";
            st = con.prepareStatement(query);
    
            
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String actual_password=getDecoded(rs.getString("userPass"));
            rs.close();
            if (actual_password.equals(password)){
                
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
