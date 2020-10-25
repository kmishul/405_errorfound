/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kmish
 */
public class PassDetail implements Serializable{
    public String trainNum,userId,passclass,ticketid,fname,lname,gender,berth;
        public int seatno,age;
        public Date date;
        
    public PassDetail() {
      
    }
    
    public PassDetail(String trainNum,String userId,String passclass,int seatno,String ticketid,String fname,String lname,int age,String gender,Date date) {
       this.trainNum=trainNum;
       this.userId=userId;
       this.passclass=passclass;
       this.seatno=seatno;
       this.ticketid=ticketid;
       this.fname=fname;
       this.lname=lname;
       this.age=age;
       this.gender=gender;
       this.date=date;
    }
    //getter functions
    public void settrainNum(String s)
    {
        trainNum=s;
    }
    public void setuserId(String s)
    {
        userId=s;
    }
    public void setpassclass(String s)
    {
        passclass=s;
    }
    public void setticketid(String s)
    {
        ticketid=s;
    }
    public void setfname(String s)
    {
        fname=s;
    }
   public void setlname(String s)
    {
        lname=s;
    }
    public void setgender(String s)
    {
        gender=s;
    }
    public void setdate(Date s)
    {
        date=s;
    }
    public void setseatno(int i)
    {
        seatno=i;
    }
    public void setage(int i)
    {
        age=i;
    }
    public void setberth(String s)
    {
        berth=s;
    }
    
    
    //getter functions
    
    public String gettrainNum()
    {
        return trainNum;
    }
    public String getuserId()
    {
        return userId;
    }
    public String getpassclass()
    {
        return passclass;
    }
    public String getticketid()
    {
        return ticketid;
    }
    public String getfname()
    {
        return fname;
    }
   public String getlname()
    {
        return lname;
    }
    public String getgender()
    {
        return gender;
    }
    public Date getdate()
    {
        return date;
    }
    public int getseatno()
    {
        return seatno;
    }
    public int getage()
    {
        return age;
    }
    public String getberth()
    {
        return berth;
    }

}
