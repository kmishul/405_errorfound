/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmish
 */
public class DBConnect {
    protected Connection con;
    protected static Statement stmt;
    DBConnect()
    {
        try
        {
            Class.forName("java.sql.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            stmt=con.createStatement();
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
