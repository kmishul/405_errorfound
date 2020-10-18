/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_side;
import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JOptionPane;

/**
 *
 * @author Folio
 */
public class Client_handler implements Runnable {
    private final Socket client;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String user_name;
    private Connection con;
    private static Statement stmt;
    private PreparedStatement st;
    
    Client_handler(Socket client){
        this.client=client;
        try
        {
            Class.forName("java.sql.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            stmt=con.createStatement();
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(Client_handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try
        {
            // OPENING STREAMS WITH THE CLIENT SOCKET
            dis=new DataInputStream(client.getInputStream());
            dos=new DataOutputStream(client.getOutputStream());
            user_name=dis.readUTF();
            
            
            
            // FOR A NEW CUSTOMER SIGN UP
            if(user_name.equals("New Customer"))
            {
                System.out.println("Connected To a new Customer Signup Initiated");
                boolean done=addCustomer();
                if(done)
                {
                    dos.writeUTF("Sign Up done");
                    System.out.println("New Customer Successfully Added");
                }
                else
                {
                    dos.writeUTF("Sign Up failed,may be the username is already taken, try using another");
                }
            }
            
            
            //FOR AN EXISTING CUSTOMER LOG IN
            else if(user_name.equals("Admin login")){
                String Adminid=dis.readUTF();
                String pass2=dis.readUTF();
                String actual_pass=getPasswordadmin(Adminid); //password fetched from database
                if(actual_pass.equals(pass2))
                {
                    dos.writeUTF("Valid"); 
                    System.out.println("Connection Successfully made");
                    //updateLoginStatus(1);
                }
                else
                {
                    dos.writeUTF("Invalid");
                }
                
            }
            else
            {
                String pass=dis.readUTF(); //password entered by user
                String actual_pass=getPassword(); //password fetched from database
                if(actual_pass.equals(pass))
                {
                    dos.writeUTF("Valid"); 
                    System.out.println("Connection Successfully made");
                    //updateLoginStatus(1);
                }
                else
                {
                    dos.writeUTF("Invalid");
                }
            } 
             while(client.isClosed()==false) // Connection Closes on log_out
            {
                String request=dis.readUTF();
                System.out.println(request);
                String response=getResponse(request);
                System.out.println(response);
                dos.writeUTF(response);
            }
        }
        catch (IOException ex) 
        {
            System.out.println("Error IO "+ex.getMessage());
        } 
    }
    private String getResponse(String request ) throws IOException{
        String Response="";
        if(request.equals("Add Train")){
            if(addtrain())
                Response="done";
            else;
                Response="Not Done";  
        }
        else if(request.equals("View passengerDetails")){
             try{
            String q1="SELECT * FROM passengerdetail";
            ResultSet rs=stmt.executeQuery(q1);
            while(rs.next()){
                dos.writeUTF(rs.getString("trainNum"));
                dos.writeUTF(rs.getString("userId"));
                dos.writeUTF(rs.getString("passclass"));
                dos.writeInt(rs.getInt("passseatNo"));
                dos.writeUTF(rs.getString("passengerTicketId"));
                dos.writeUTF(rs.getString("passengerFirstName"));
                dos.writeUTF(rs.getString("passengerLastName"));
                dos.writeInt(rs.getInt("passengerAge"));
                dos.writeUTF(rs.getString("passengergender"));
                dos.writeUTF(rs.getString("travdate"));
            }
            Response="viewpassdone";
        }
        catch(IOException | SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
            }        
        }
        else if(request.equals("View Trains")){
             try{
            String q1="SELECT * FROM traininfo";
            ResultSet rs=stmt.executeQuery(q1);
            while(rs.next()){
                dos.writeUTF(rs.getString("trainNum"));
                dos.writeUTF(rs.getString("trainName"));
                dos.writeUTF(rs.getString("firstStation"));
                dos.writeUTF(rs.getString("lastStation"));
                dos.writeUTF(rs.getString("departureTime"));
                dos.writeUTF(rs.getString("arrivalTime"));
                dos.writeInt(rs.getInt("feeFirstClass"));
                dos.writeInt(rs.getInt("feeSecondClass"));
                dos.writeInt(rs.getInt("feeSleeperClass"));
                dos.writeUTF(rs.getString("days"));
                dos.writeInt(rs.getInt("cancel"));
            }
            Response="viewtraindone";
        }
        catch(IOException | SQLException e){
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
            }        
        }
        else if(request.equals("Remove Train")){
            String train_num=dis.readUTF();
            if(removetrain(train_num))
                Response="Done";
            else
                Response="Not Done";
        }
        return Response;
    }
    private String getPassword()
    {
        try
        {
            String q1="Select userPass From userlogin where userId='"+(user_name)+"';";
            ResultSet rs=stmt.executeQuery(q1);
            rs.next();
            String actual_pass=rs.getString("userPass");
            rs.close();
            return actual_pass;            
        }
        catch(Exception e)
        {
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
        }        
    }
    private String getPasswordadmin(String name)
    {
        try
        {
            String q1="Select adminPass From adminaccount where adminId='"+(name)+"';";
            ResultSet rs=stmt.executeQuery(q1);
            rs.next();
            String actual_pass=rs.getString("adminpass");
            rs.close();
            return actual_pass;            
        }
        catch(SQLException e)
        {
            System.out.println("Server ERROR "+e.getMessage());
            return "error";
        }        
    }
    private boolean addCustomer() throws IOException
    {
        System.out.println("Receiving details");
        String details=dis.readUTF();
        String[] data=new String[7];
        data=details.split("~");
        System.out.println(data[0]+data[1]+data[2]+data[3]+data[4]+data[5]+data[6]);
        PreparedStatement pss;
        try
        {
           // if(!checkUsername(user_name)){
           
            if(!checkUsername(data[0])){
                //String q1="INSERT INTO `userlogin`(`userId`, `userFirstName`, `userLastName`, `userEmailid`, `userPass`, `userContactNo`, `userGender`) VALUES (?,?,?,?,?,?,?)";
                pss=con.prepareStatement("INSERT INTO userlogin "
                        + "(`userId`, `userFirstName`, `userLastName`, `userEmailid`, `userPass`,"
                        + " `userContactNo`, `userGender`) VALUES (?,?,?,?,?,?,?)");
                pss.setString(1, data[0]);
                pss.setString(2, data[1]);
                pss.setString(3, data[2]);
                pss.setString(4, data[3]);
                pss.setString(5, data[4]);
                pss.setString(6, data[5]);
                pss.setString(7, data[6]);
                pss.execute();
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
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            st = con.prepareStatement(query);
            st.setString(1,username);
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
        }
        
        return username_exist;
    }
    private boolean addtrain() throws IOException{
        System.out.println("Recieving details");
        String details=dis.readUTF();
        try{
            String data[];
            data=details.split("~");
            if(!checktrainname(data[0])){
            String q1="INSERT INTO traininfo(`trainNum`, `trainName`, `firstStation`, `lastStation`, `departureTime`, `arrivalTime`, `feeFirstClass`, `feeSecondClass`, `feeSleeperClass`, `days`) VALUES ('"+(data[0])+"','"+(data[1])+"','"+(data[2])+"','"+(data[3])+"','"+(data[4])+"','"+(data[5])+"','"+(data[6])+"','"+(data[7])+"','"+(data[8])+"','"+(data[12])+"')";
            String q2="INSERT INTO firstclass(`trainNum`, `totalseats`) VALUES ('"+(data[0])+"','"+(data[9])+"')";
            String q3="INSERT INTO secondclass(`trainNum`, `totalseats`) VALUES ('"+(data[0])+"','"+(data[10])+"')";
            String q4="INSERT INTO sleeperclass(`trainNum`, `totalseats`) VALUES ('"+(data[0])+"','"+(data[11])+"')";
            stmt.executeQuery(q1);
            stmt.executeQuery(q2);
            stmt.executeQuery(q3);
            stmt.executeQuery(q4);
            return true;
            }
            else
                return false;
        }
        catch(SQLException e){
            System.out.println("Server Error"+e.getMessage());
            return false;
        }
    }
    private boolean checktrainname(String trainname){
        
        //PreparedStatement st;
        //ResultSet rs;
        boolean tname_exist = false;
        
        //String query = "SELECT * FROM `users` WHERE `userId` = ?";
        
        try {
            String query = "SELECT * FROM `traininfo` WHERE `trainNum` = ?";
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mms","root","");
            st = con.prepareStatement(query);
            st.setString(1,trainname);
            ResultSet rs = st.executeQuery();
            System.out.println("checkk\n");
        
            if(rs.next())
            {
                tname_exist = true;
               // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }
            
        } catch (HeadlessException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("checkTrainname\n");
        }
        
        return tname_exist;
    }
    private boolean removetrain(String train_num){
        try
        {
            if(!checktrainname(train_num)){
                String query="DELETE FROM traininfo WHERE trainNUM=train_num";
                stmt.executeQuery(query);
                return true;
            }
            else
                return false;
        }
        catch(SQLException e)
        {
            System.out.println("Server ERROR "+e.getMessage());
            return false;
        }        
    }
 }

    

