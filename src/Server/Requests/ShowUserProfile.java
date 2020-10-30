/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests;

import User.UserDetail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Folio
 */
public class ShowUserProfile {
    private final Connection con;
    private PreparedStatement st;
    public ShowUserProfile() throws SQLException{
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
    }
    public UserDetail showdetail(String uid) throws SQLException{
        System.out.println("showdetail method\n");
        UserDetail user=new UserDetail();
        String q="SELECT * FROM userlogin WHERE userId=?";
        st=con.prepareStatement(q);
        st.setString(1, uid);
        ResultSet rs=st.executeQuery();
        System.out.println("after execution\n");
        rs.next();
        user.setFname(rs.getString("userFirstName"));
        user.setLname(rs.getString("userLastName"));
        user.setEmailid(rs.getString("userEmailid"));
        user.setContact(rs.getString("userContactNo"));
        user.setGender(rs.getString("userGender"));
        return user;
    }
}
