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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Add Train");

        jLabel2.setText("Train Num:");

        txttrainnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttrainnumActionPerformed(evt);
            }
        });
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

        jLabel4.setText("Start Station:");

        txtstartstn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstartstnKeyReleased(evt);
            }
        });

        jLabel5.setText("Stop Station:");

        txtstopstn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstopstnKeyReleased(evt);
            }
        });

        jLabel6.setText("Start Time:");

        jLabel7.setText("Stop Time:");

        txtstarttm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstarttmActionPerformed(evt);
            }
        });
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

        txtfareslc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfareslcActionPerformed(evt);
            }
        });
        txtfareslc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfareslcKeyReleased(evt);
            }
        });

        MonRB.setText("Monday");

        TueRB.setText("Tuesday");

        WedRB.setText("Wednesday");

        ThuRB.setText("Thursday");

        FriRB.setText("Friday");
        FriRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FriRBActionPerformed(evt);
            }
        });

        SatRB.setText("Saturday");

        SunRB.setText("Sunday");

        jLabel14.setText("Select days train will be running:");

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

        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttrainnum, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtstarttm, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(txtstartstn))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel5)
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttrainname, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtstopstn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(630, 630, 630)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(txtstoptm, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel8)
                        .addGap(45, 45, 45)
                        .addComponent(txtNOSfc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel11)
                        .addGap(37, 37, 37)
                        .addComponent(txtfarefc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel9)
                        .addGap(32, 32, 32)
                        .addComponent(txtNOSsc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel12)
                        .addGap(24, 24, 24)
                        .addComponent(txtfaresc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(SunRB, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(WedRB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtNOSslc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(25, 25, 25)
                                .addComponent(txtfareslc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(MonRB, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(ThuRB, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TueRB, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(FriRB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SatRB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jRadioButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(btnaddtrain)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(txttrainnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(3, 3, 3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txttrainname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstopstn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtstartstn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)))))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtstoptm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtstarttm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtNOSfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtfarefc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtNOSsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtfaresc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SunRB)
                    .addComponent(WedRB))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MonRB)
                            .addComponent(ThuRB))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TueRB)
                            .addComponent(FriRB))
                        .addGap(7, 7, 7)
                        .addComponent(SatRB))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel10))
                                    .addComponent(txtNOSslc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel13))
                                    .addComponent(txtfareslc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(btnaddtrain)
                .addGap(27, 27, 27))
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
        
        String PATTERN="^([01]?[0-9]2[0-3]):[0-5][0-9]$";
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

        String PATTERN="^([01]?[0-9]2[0-3]):[0-5][0-9]$";
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

    private void txtstarttmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstarttmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstarttmActionPerformed

    private void txtfareslcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfareslcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfareslcActionPerformed

    private void FriRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FriRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FriRBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        new AdminMainInterface(rr, adminid).show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txttrainnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttrainnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttrainnumActionPerformed
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
        if (jLabel15.getText().equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkfields2(){
        if (jLabel16.getText().equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields3(){
        if (jLabel17.getText().equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields4(){
        if (jLabel18.getText().equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields5(){
        if (jLabel19.getText().equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }public boolean checkfields6(){
        if (jLabel20.getText().equals("invalid")){
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
    private javax.swing.JButton jButton1;
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