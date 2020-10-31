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
import java.util.ArrayList;

/**
 *
 * @author Folio
 */
public class SendQueryRequest implements Serializable {
    private final Connection con;
    private PreparedStatement st;
    private ArrayList<Queries> vt=new ArrayList<>();
    private String rep;
    
    public SendQueryRequest() {
         con = DBConnect.con;
    }
    
    //Method returning true on successfully inserting query in database else false if that userid does not exist
    public boolean sendquery(Queries q) throws SQLException{
        String uid=q.getuserid();
        String query=q.getquery();
        if(!checkusername1(uid)){
           String q1="INSERT INTO `chats`(`userId`, `query`) VALUES (?,?)"; 
           st=con.prepareStatement(q1);
           st.setString(1, uid);
           st.setString(2, query);  
           st.execute();
           return true;
        }
        else{
            System.out.println("Query already exist for this username");
            return false;
        }
    }
    //Method returning valid if table has queries for admin else invalid 
    public String getqry() throws SQLException{
        System.out.println("checkgetqry\n");
        String Response="";
        try{
        String q1="SELECT * FROM chats WHERE reply IS NULL;";
        st=con.prepareStatement(q1);
        ResultSet rs=st.executeQuery();
        System.out.println("checexecute1\n");
        while(rs.next()){
            Queries q=new Queries();
            q.setuserid(rs.getString("userId"));
            q.setquery(rs.getString("query"));
            vt.add(q);
            Response="valid";
        }
        return Response;
        }catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            System.out.println("checexecutecatch\n");
            return "invalid";
            
            }        
    }
    //Method returning list of all the queries
    public ArrayList<Queries> getList() {
        return vt;
    }
    //Method to insert reply for that user query
    public boolean sendreply(Queries q) throws SQLException{
        System.out.println("in send reply");
        String uid=q.getuserid();
        String reply=q.getreply();
        try{
        if(checkusername(uid)){
            String query="UPDATE chats SET reply=? WHERE userId=?";
            st=con.prepareStatement(query);
            st.setString(1, reply);
            st.setString(2, uid);
            st.execute();
            return true;
        }
        else{
            System.out.println("no query for this userid");
            return false;
        }
        }catch( SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            System.out.println("checexecutecatch\n");
            return false;
            
            }        
    }
    //method returning 0 if user has got the reply from admin,2 if user has no queries and 1 if no reply from admin
    public int showreply(String uid) throws SQLException{
        if(checkusername1(uid)){
            if(!checkusername(uid)){
                String q1="SELECT * FROM chats WHERE userId=?";
                st=con.prepareStatement(q1);
                st.setString(1, uid);
                ResultSet rs=st.executeQuery();
                rs.next();
                rep=rs.getString("reply");
                return 0;
            }
            else{
                return 2;
            }
        }
        else{
            return 1;
        }
        
    }
    //Method showing reply from the admin
    public String reply(){
        return rep;
    }
    //method to delete that query from database
    public void deleterep(String u) throws SQLException{
        String q="DELETE FROM chats WHERE userId=?";
        st=con.prepareStatement(q);
        st.setString(1, u);
        st.execute();
        System.out.println("deleted from database");
    }
    private boolean checkusername(String uid){
        
        boolean uname_exist = false;
        
        try {
            String query = "SELECT * FROM `chats` WHERE `userId` = ? AND reply IS NULL";
            st = con.prepareStatement(query);
            st.setString(1,uid);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                uname_exist = true;
                System.out.println("user name true\n");
            }
            
        } catch (HeadlessException | SQLException ex) {
            System.out.println("checkusername\n");
        }
        
        return uname_exist;
    }
    private boolean checkusername1(String uid){
        boolean uname_exist = false;
       
        try {
            String query = "SELECT * FROM `chats` WHERE `userId` = ?";
            st = con.prepareStatement(query);
            st.setString(1,uid);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                uname_exist = true;
                System.out.println("user name true\n");
            }
            
        } catch (HeadlessException | SQLException ex) {
            System.out.println("checkusername\n");
        }
        
        return uname_exist;
    }
}
