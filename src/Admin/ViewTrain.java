/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.Serializable;

/**
 *
 * @author kmish
 */

public class ViewTrain implements Serializable{
    public String trainNum,trainName,fstation,lstation,dtime,atime,days,status;
    public int Nosfc,Nossc,Nosslc,fee1,fee2,fee3,cancel,dmc;

    public ViewTrain() {
    
    }
    
    public void settrainName(String s)
    {
        trainName=s;
    }
    public void setfstation(String s)
    {
        fstation=s;
    }
    public void setlstation(String s)
    {
        lstation=s;
    }
    public void setdtime(String s)
    {
        dtime=s;
    }
    public void setatime(String s)
    {
        atime=s;
    }
    public void settrainNum(String s)
    {
        trainNum=s;
    }
    public void setdays(String s)
    {
        days=s;
    }
    public void setNOSfc(int Nosfc){
        this.Nosfc=Nosfc;
    }
    public void setNOSsc(int Nossc){
        this.Nossc=Nossc;
    }
    public void setNOSslc(int Nosslc){
        this.Nosslc=Nosslc;
    }
    public void setfee1(int i)
    {
        fee1=i;
    }
    public void setfee2(int i)
    {
        fee2=i;
    }
    public void setfee3(int i)
    {
        fee3=i;
    }
    
    public void setcancel(int i)
    {
        cancel=i;
    }
    public void setstatus(String s) {
         status=s;
        }

    public void setdmc(int dmc) {
        this.dmc = dmc;
    }
    
    //getter functions
    
    public String gettrainName()
    {
        return trainName;
    }
    public String getfstation()
    {
        return fstation;
    }
    public String getlstation()
    {
        return lstation;
    }
    public String getdtime()
    {
        return dtime;
    }
    public String getatime()
    {
        return atime;
    }
    public String gettrainNum()
    {
        return trainNum;
    }
    public String getdays()
    {
        return days;
    }
     public int getNosfc()
    {
        return Nosfc;
    }
    public int getNossc()
    {
        return Nossc;
    }
    public int getNosslc()
    {
        return Nosslc;
    }
    public int getfee1()
    {
        return fee1;
    }
    public int getfee2()
    {
        return fee2;
    }
    public int getfee3()
    {
        return fee3;
    }
     
    public int getcancel()
    {
        return cancel;
    }
    String getstatus() {
        return status;
        }

    public int getdmc() {
        return dmc;
    }

    
    
    public ViewTrain(String trainNum,String trainName,String fstation,String lstation,String dtime,String atime,int Nosfc,int Nossc,int Nosslc,int fee1,int fee2,int fee3,String days,int dmc) {
       this.trainNum=trainNum;
       this.trainName=trainName;
       this.fstation=fstation;
       this.lstation=lstation;
       this.dtime=dtime;
       this.atime=atime;
       this.Nosfc=Nosfc;
       this.Nossc=Nossc;
       this.Nosslc=Nosslc;
       this.fee1=fee1;
       this.fee2=fee2;
       this.fee3=fee3;
       this.days=days;
       this.cancel=cancel;
       this.dmc=dmc;
    }
//    public ViewTrain(String trainNum,String trainName,String fstation,String lstation,String dtime,String atime,int fee1,int fee2,int fee3,String days,int cancel,int dmc) {
//       this.trainNum=trainNum;
//       this.trainName=trainName;
//       this.fstation=fstation;
//       this.lstation=lstation;
//       this.dtime=dtime;
//       this.atime=atime;
//       this.fee1=fee1;
//       this.fee2=fee2;
//       this.fee3=fee3;
//       this.days=days;
//       this.cancel=cancel;
//       this.dmc=dmc;
//    }

    

}
