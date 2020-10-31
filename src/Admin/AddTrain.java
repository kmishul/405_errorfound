/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Admin;

import Req_Res.Req_Res;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author kmish
 */
public class AddTrain extends javax.swing.JFrame implements Serializable{
     private final Req_Res rr;
     private final String adminid;

    /** Creates new form AddTrain */
    public AddTrain(Req_Res rr,String adminid) {
        initComponents();
        this.rr=rr;
        this.adminid=adminid;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txttrainnum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttrainname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtstartstn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstopstn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtstarttm = new javax.swing.JTextField();
        txtstoptm = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNOSfc = new javax.swing.JTextField();
        txtNOSsc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNOSslc = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtfarefc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtfaresc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtfareslc = new javax.swing.JTextField();
        MonRB = new javax.swing.JRadioButton();
        TueRB = new javax.swing.JRadioButton();
        WedRB = new javax.swing.JRadioButton();
        ThuRB = new javax.swing.JRadioButton();
        FriRB = new javax.swing.JRadioButton();
        SatRB = new javax.swing.JRadioButton();
        SunRB = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        btnaddtrain = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Train");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel1.setText("START A NEW TRAIN");

        jLabel2.setText("Train Number:");

        txttrainnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttrainnumKeyReleased(evt);
            }
        });

        jLabel3.setText("Train name:");

        txttrainname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttrainnameKeyReleased(evt);
            }
        });

        jLabel4.setText("Source:");

        txtstartstn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstartstnKeyReleased(evt);
            }
        });

        jLabel5.setText("Destination");

        txtstopstn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstopstnKeyReleased(evt);
            }
        });

        jLabel6.setText("Departure:");

        jLabel7.setText("Arrival:");

        txtstarttm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstarttmKeyReleased(evt);
            }
        });

        txtstoptm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstoptmKeyReleased(evt);
            }
        });

        jLabel8.setText("NOC First Class:");

        jLabel9.setText("NOC Second Class:");

        txtNOSfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNOSfcKeyReleased(evt);
            }
        });

        txtNOSsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOSscActionPerformed(evt);
            }
        });
        txtNOSsc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNOSscKeyReleased(evt);
            }
        });

        jLabel10.setText("NOC Sleeper class:");

        txtNOSslc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNOSslcKeyReleased(evt);
            }
        });

        jLabel11.setText("Fare First Class:");

        txtfarefc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfarefcKeyReleased(evt);
            }
        });

        jLabel12.setText("Fare Second Class:");

        txtfaresc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfarescKeyReleased(evt);
            }
        });

        jLabel13.setText("Fare Sleeper Class:");

        txtfareslc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfareslcKeyReleased(evt);
            }
        });

        MonRB.setText("Monday");

        TueRB.setText("Tuesday");

        WedRB.setText("Wednesday");
        WedRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WedRBActionPerformed(evt);
            }
        });

        ThuRB.setText("Thursday");

        FriRB.setText("Friday");

        SatRB.setText("Saturday");

        SunRB.setText("Sunday");

        jLabel14.setText("Select days when train will be running:");

        btnaddtrain.setText("Add Train");
        btnaddtrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddtrainActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Dynamic");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel8)
                        .addGap(69, 69, 69)
                        .addComponent(txtNOSfc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(jLabel11)
                        .addGap(59, 59, 59)
                        .addComponent(txtfarefc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel9)
                        .addGap(56, 56, 56)
                        .addComponent(txtNOSsc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(jLabel12)
                        .addGap(46, 46, 46)
                        .addComponent(txtfaresc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtNOSslc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(jLabel13)
                        .addGap(45, 45, 45)
                        .addComponent(txtfareslc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(MonRB)
                        .addGap(17, 17, 17)
                        .addComponent(TueRB)
                        .addGap(13, 13, 13)
                        .addComponent(WedRB)
                        .addGap(7, 7, 7)
                        .addComponent(ThuRB)
                        .addGap(19, 19, 19)
                        .addComponent(FriRB)
                        .addGap(25, 25, 25)
                        .addComponent(SatRB)
                        .addGap(21, 21, 21)
                        .addComponent(SunRB))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jRadioButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(btnaddtrain))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(txtstartstn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(280, 280, 280)
                                        .addComponent(txtstopstn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(txttrainnum, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(txttrainname, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(txtstarttm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76)
                                        .addComponent(txtstoptm, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txttrainnum)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txttrainname)
                        .addGap(4, 4, 4))
                    .addComponent(jLabel16))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtstartstn)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtstopstn)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtstarttm)
                    .addComponent(txtstoptm)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNOSfc)
                    .addComponent(txtfarefc)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNOSsc)
                    .addComponent(txtfaresc)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNOSslc)
                    .addComponent(txtfareslc)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MonRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TueRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(WedRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ThuRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FriRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SatRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SunRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(btnaddtrain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddtrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddtrainActionPerformed
        // TODO add your handling code here:
        if(verifyTrains()&&checkfields1()&&checkfields2()&&checkfields3()&&checkfields4()&&checkfields5()&&checkfields6()){
            System.out.println("11\n");
            String tNum=txttrainnum.getText();
            String tName= txttrainname.getText();
            String StartStation=txtstartstn.getText();
            String StopStation=txtstopstn.getText();
            String StartTime=txtstarttm.getText();
            String StopTime=txtstoptm.getText();
            int NOSFirst=(Integer.parseInt(txtNOSfc.getText()))*60;
            int NOSSecond=(Integer.parseInt(txtNOSsc.getText()))*60;
            int NOSSleeper=(Integer.parseInt(txtNOSslc.getText()))*80;
            int FareFirst=Integer.parseInt(txtfarefc.getText());
            int FareSecond=Integer.parseInt(txtfaresc.getText());
            int FareSleeper=Integer.parseInt(txtfareslc.getText());
            int dmc;
            if(jRadioButton1.isSelected())
                dmc=1;
            else dmc=0;
            
            String rundays="";
            System.out.println("12\n");
            if(MonRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(TueRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(WedRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(ThuRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(FriRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(SatRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(SunRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            try {
                //Req_Res addt=new Req_Res();
                ViewTrain train=new ViewTrain(tNum,tName,StartStation,StopStation,StartTime,StopTime,NOSFirst,NOSSecond,NOSSleeper,FareFirst,FareSecond,FareSleeper,rundays,dmc);
                String Res=rr.addTrain(train);
                System.out.println(Res+"2");
                if(Res.equals("valid")){
                    System.out.println(Res+"2\n");
                    JOptionPane.showMessageDialog(this," Train Successfully Added");
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,Res);
                    System.out.println("\n"+Res);
                }
            }catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(AddTrain.class.getName()).log(Level.SEVERE, null, ex);
            }catch(HeadlessException e){
                System.out.println(e);
                System.out.println("error");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "check if u have entered valid entries");
        
        }
    }//GEN-LAST:event_btnaddtrainActionPerformed

    private void txttrainnumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttrainnumKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{2,5}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txttrainnum.getText());
        if(!match.matches()){
            jLabel15.setText("invalid");
        }
        else{
            jLabel15.setText(null);
        }
    }//GEN-LAST:event_txttrainnumKeyReleased

    private void txttrainnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttrainnameKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[a-z A-Z]{3,10}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txttrainname.getText());
        if(!match.matches()){
            jLabel16.setText("invalid");
        }
        else{
            jLabel16.setText(null);
        }
    }//GEN-LAST:event_txttrainnameKeyReleased

    private void txtstartstnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstartstnKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[a-z A-Z]{3,30}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtstartstn.getText());
        if(!match.matches()){
            jLabel17.setText("invalid");
        }
        else{
            jLabel17.setText(null);
        }        
    }//GEN-LAST:event_txtstartstnKeyReleased

    private void txtstopstnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstopstnKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[a-z A-Z]{3,30}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtstopstn.getText());
        if(!match.matches()){
            jLabel17.setText("invalid");
        }
        else{
            jLabel17.setText(null);
        }              
    }//GEN-LAST:event_txtstopstnKeyReleased

    private void txtstarttmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstarttmKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtstarttm.getText());
        if(!match.matches()){
            jLabel18.setText("invalid");
        }
        else{
            jLabel18.setText(null);
        }                      
    }//GEN-LAST:event_txtstarttmKeyReleased

    private void txtstoptmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstoptmKeyReleased
        // TODO add your handling code here:

        String PATTERN="([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtstoptm.getText());
        if(!match.matches()){
            jLabel18.setText("invalid");
        }
        else{
            jLabel18.setText(null);
        }                      
    }//GEN-LAST:event_txtstoptmKeyReleased

    private void txtNOSfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNOSfcKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{1,2}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtNOSfc.getText());
        if(!match.matches()){
            jLabel19.setText("invalid");
        }
        else{
            jLabel19.setText(null);
        }         
    }//GEN-LAST:event_txtNOSfcKeyReleased

    private void txtNOSscKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNOSscKeyReleased
        // TODO add your handling code here:
        String PATTERN="^[0-9]{1,2}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtNOSsc.getText());
        if(!match.matches()){
            jLabel19.setText("invalid");
        }
        else{
            jLabel19.setText(null);
        }         
    }//GEN-LAST:event_txtNOSscKeyReleased

    private void txtNOSslcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNOSslcKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{1,2}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtNOSslc.getText());
        if(!match.matches()){
            jLabel19.setText("invalid");
        }
        else{
            jLabel19.setText(null);
        }         
    }//GEN-LAST:event_txtNOSslcKeyReleased

    private void txtfarefcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfarefcKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{1,10}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtfarefc.getText());
        if(!match.matches()){
            jLabel20.setText("invalid");
        }
        else{
            jLabel20.setText(null);
        }         
    }//GEN-LAST:event_txtfarefcKeyReleased

    private void txtfarescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfarescKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{1,10}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtfaresc.getText());
        if(!match.matches()){
            jLabel20.setText("invalid");
        }
        else{
            jLabel20.setText(null);
        }         
    }//GEN-LAST:event_txtfarescKeyReleased

    private void txtfareslcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfareslcKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{1,10}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txtfareslc.getText());
        if(!match.matches()){
            jLabel20.setText("invalid");
        }
        else{
            jLabel20.setText(null);
        }         
    }//GEN-LAST:event_txtfareslcKeyReleased

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txtNOSscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOSscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNOSscActionPerformed

    private void WedRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WedRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WedRBActionPerformed
public boolean verifyTrains(){ 
            System.out.println("13\n");
            String tNum=txttrainnum.getText();
            String tName= txttrainname.getText();
            String StartStation=txtstartstn.getText();
            String StopStation=txtstopstn.getText();
            String StartTime=txtstarttm.getText();
            String StopTime=txtstoptm.getText();
            String NOSFirst=txtNOSfc.getText();
            String NOSSecond=txtNOSsc.getText();
            String NOSSleeper=txtNOSslc.getText();
            String FareFirst=txtfarefc.getText();
            String FareSecond=txtfaresc.getText();
            String FareSleeper=txtfareslc.getText();
            String rundays="";
            if(MonRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(TueRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(WedRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(ThuRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(FriRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(SatRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(SunRB.isSelected()){
                rundays=rundays+"1";
            }
            else{
                rundays=rundays+"0";
            }
            if(tNum.trim().equals("") ||tName.trim().equals("") || StartStation.trim().equals("") || StopStation.trim().equals("")
               || StartTime.trim().equals("") || StopTime.trim().equals("")||NOSFirst.trim().equals("")||NOSSecond.trim().equals("")||NOSSleeper.trim().equals("")||FareFirst.trim().equals("")||FareSecond.trim().equals("")||FareSleeper.trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "One Or More Fields Are Empty","Empty Fields",2);
                return false;
            }
            else if(rundays.equals("0000000")){
                JOptionPane.showMessageDialog(this, "select one or more days on which train is running");
                return false;
            }
            else {
                return true;
            }
    }
    public boolean checkfields1(){
        if (jLabel15.getText()=="invalid"){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkfields2(){
        if (jLabel16.getText()=="invalid"){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields3(){
        if (jLabel17.getText()=="invalid"){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields4(){
        if (jLabel18.getText()=="invalid"){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields5(){
        if (jLabel19.getText()=="invalid"){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields6(){
        if (jLabel20.getText()=="invalid"){
            return false;
        }
        else{
            return true;
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton FriRB;
    private javax.swing.JRadioButton MonRB;
    private javax.swing.JRadioButton SatRB;
    private javax.swing.JRadioButton SunRB;
    private javax.swing.JRadioButton ThuRB;
    private javax.swing.JRadioButton TueRB;
    private javax.swing.JRadioButton WedRB;
    private javax.swing.JButton btnaddtrain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField txtNOSfc;
    private javax.swing.JTextField txtNOSsc;
    private javax.swing.JTextField txtNOSslc;
    private javax.swing.JTextField txtfarefc;
    private javax.swing.JTextField txtfaresc;
    private javax.swing.JTextField txtfareslc;
    private javax.swing.JTextField txtstartstn;
    private javax.swing.JTextField txtstarttm;
    private javax.swing.JTextField txtstopstn;
    private javax.swing.JTextField txtstoptm;
    private javax.swing.JTextField txttrainname;
    private javax.swing.JTextField txttrainnum;
    // End of variables declaration//GEN-END:variables

}