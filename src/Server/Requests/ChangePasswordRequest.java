/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import Server.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Folio
 */
public class ChangePasswordRequest {
    private final Connection con;
    private PreparedStatement st;
    public ChangePasswordRequest() {
          con = DBConnect.con;
    }
    //Method returning false if current password entered by user is wrong else true
    public boolean changepass(String uid,String opass,String npass) throws SQLException{
        String query="Select userPass From userlogin where userId='"+(uid)+"';";
            st = con.prepareStatement(query);
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String actual_password=rs.getString("userPass");
            rs.close();
            if (actual_password.equals(opass)){
                String q="UPDATE userlogin SET userPass=? WHERE userId=?";
                st=con.prepareStatement(q);
                st.setString(1, npass);
                st.setString(2, uid);
                st.execute();
                System.out.println("update done");
                return true;
            }
            else{
                System.out.println("Wrong password");
                return false;
            }
    }
}
