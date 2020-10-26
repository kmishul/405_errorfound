/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.Serializable;

/**
 *
 * @author Folio
 */
public class Admindetail implements Serializable {
    public String adminid, adminpass;
    public Admindetail(String Adminid, String Adminpass){
        this.adminid=Adminid;
        this.adminpass=Adminpass;
    }
    public void setadminid(String adminid){
        this.adminid=adminid;
    }
    public void setadminpass(String adminpass){
        this.adminpass=adminpass;
    }
}
