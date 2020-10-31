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

public class Queries implements Serializable {
    public String userid,query,reply;
    public Queries(String userid,String query,String reply){
        this.userid=userid;
        this.query=query;
    }

    public Queries() {
        }
    public void setuserid(String userid){
        this.userid=userid;
    }
    public void setquery(String query){
        this.query=query;
    }
    public void setreply(String reply){
        this.reply=reply;
    }
    public String getuserid(){
        return userid;
    }
    public String getquery(){
        return query;
    }
    public String getreply(){
        return reply;
    }
}
