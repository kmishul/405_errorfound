/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Requests.User;

import Server.DBConnect;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author kmish
 */
public class DiscountsRequest implements Serializable {
    private final Connection con;
    private PreparedStatement st,st1,st2;
    
   //Constructor
    public DiscountsRequest() {
         con = DBConnect.con;
    }
    



    //Function to give discounts to selected users who has exceed this limit of booking in last one month
public boolean giveDiscounts(int limit,int dis) throws SQLException
{   boolean b=false;
       
    //Calculating required dates and stored in variables
    Date date=java.sql.Date.valueOf(java.time.LocalDate.now());
        java.util.Date utilObj = date;
            java.sql.Date today = new java.sql.Date(utilObj.getTime());
    
    Date date1=java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(30));
        java.util.Date utilObj1 = date1;
            java.sql.Date d1 = new java.sql.Date(utilObj1.getTime());
           
    Date date2=java.sql.Date.valueOf(java.time.LocalDate.now().minusDays(1));
        java.util.Date utilObj2 = date2;
            java.sql.Date d2 = new java.sql.Date(utilObj2.getTime());
            
            //Find out Distinct userId
            String query1="SELECT DISTINCT userId FROM passengerdetail WHERE travdate BETWEEN ? AND ?";
            String query2="";
            st = con.prepareStatement(query1);
            st.setDate(1,d1);
            st.setDate(2,d2);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {   String userid=rs.getString("userId");
                    //To get total price in last one month
                query2="SELECT SUM(fare) FROM passengerdetail WHERE userId=? AND travdate BETWEEN ? AND ?";
                st1 = con.prepareStatement(query2);
                st1.setString(1,userid);
                st1.setDate(2,d1);
                st1.setDate(3,d2);
                ResultSet rs1 = st1.executeQuery();
                int price=0;
                if(rs1.next())
                    price =  ((Number) rs1.getObject(1)).intValue(); 
                
                
                if(limit<=price)
                {
                    st2=con.prepareStatement("INSERT INTO discounts(`userId`, `discount`,`discountdate`) VALUES (?,?,?)");
                    st2.setString(1,userid);
                    st2.setInt(2,dis);
                    st2.setDate(3,today);
                    st2.execute();
                }
                b=true;
            }
            
          return b;  
    }

    
    //Request by user To check discount(if any)
    public void checkDiscount(String userid,ObjectOutputStream oos) throws SQLException, IOException {
        
        String query1="SELECT * FROM discounts WHERE userId=?";
           st = con.prepareStatement(query1);
            st.setString(1,userid);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                int i=rs.getInt("discount");
                java.sql.Date d=rs.getDate("discountdate");
                oos.writeObject("valid");
                oos.writeObject(i);
                oos.writeObject(d);
                
            }
            
            else oos.writeObject("invalid");
        oos.flush();
        
    }

}