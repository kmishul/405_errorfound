/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;
import Server.DBConnect;
import Server.Server;
import User.UserDetail;
import User.UserLogin;
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
public class UserLoginRequest implements Serializable{
    private final Connection con;
    private static Statement stmt;
    private PreparedStatement st;
    String userid, password;
    public UserLoginRequest(UserDetail userl) throws SQLException{
        this.con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
        userid=userl.getUserid();
        password=userl.getPass();
    }
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
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println(ex);
            return false;
            
        }catch(Exception e){
            //System.out.println(e);
            System.out.println(e);
            return false;
            
        }
        
    }
    private static String getDecoded(String hashed){
        return new String(Base64.getMimeDecoder().decode(hashed));
    }
}
