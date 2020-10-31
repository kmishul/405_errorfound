/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commmon_LockdownTraveller;
import java.io.Serializable;

/**
 *
 * @author kmish
 */


public class AdminDetail implements Serializable {
    public String adminid, adminpass;
    public AdminDetail(String Adminid, String Adminpass){
        this.adminid=Adminid;
        this.adminpass=Adminpass;
    }
    public void setadminid(String adminid){
        this.adminid=adminid;
    }
    public void setadminpass(String adminpass){
        this.adminpass=adminpass;
    }

    public String getAdminid() {
        return adminid;
    }

    public String getAdminpass() {
        return adminpass;
    }
    
}
