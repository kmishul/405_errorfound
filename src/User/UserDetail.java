/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.Serializable;

/**
 *
 * @author kmish
 */
public class UserDetail implements Serializable{
    String userid,fname,lname,emailid,pass,contact,gender;

    public UserDetail() {
    }

    public UserDetail(String userid, String fname, String lname, String emailid, String pass, String contact, String gender) {
        this.userid = userid;
        this.fname = fname;
        this.lname = lname;
        this.emailid = emailid;
        this.pass = pass;
        this.contact = contact;
        this.gender = gender;
    }
    //getter functions
    public String getUserid() {
        return userid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPass() {
        return pass;
    }

    public String getContact() {
        return contact;
    }

    public String getGender() {
        return gender;
    }
    
    //setter functions

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
