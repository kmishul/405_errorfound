/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javax.swing.JOptionPane;
import Req_Res.Req_Res;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kmish
 */
public class CancelTrain extends javax.swing.JFrame implements Serializable{
   
    private final Req_Res rr;
     private final String adminid;
    /**
     * Creates new form CancelTrain
     */
    public CancelTrain(Req_Res rr,String adminid) {
        initComponents();
        this.rr=rr;
        this.adminid=adminid;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txttrainnum = new javax.swing.JTextField();
        tncancel = new javax.swing.JButton();
        btnuncancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cancel/Uncancel Train");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Stencil", 0, 18)); // NOI18N
        jLabel1.setText("CANCEL/UNCANCEL TRAINS");

        jLabel2.setText("Train Number:");

        txttrainnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttrainnumKeyReleased(evt);
            }
        });

        tncancel.setText("Cancel Train");
        tncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tncancelActionPerformed(evt);
            }
        });

        btnuncancel.setText("Uncancel Train");
        btnuncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuncancelActionPerformed(evt);
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
                .addGap(149, 149, 149)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(95, 95, 95)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(tncancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(131, 131, 131)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnuncancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(164, 164, 164))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txttrainnum)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttrainnum, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addGap(74, 74, 74)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tncancel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnuncancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tncancelActionPerformed
        // TODO add your handling code here:
    if(checkfields()){
        String tNum=txttrainnum.getText();
        if(tNum.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Train Number");
        }
        else{
            try {
                //Req_Res res=new Req_Res();
                ViewTrain train=new ViewTrain();
                train.settrainNum(tNum);
                String Res=rr.cancelTrain(train);
                if(Res.equals("valid")){
                    System.out.println(Res+"2\n");
                    JOptionPane.showMessageDialog(this,"Train Successfully Cancelled");
                }
                else{
                    JOptionPane.showMessageDialog(this,Res);
                    System.out.println("\n"+Res);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CancelTrain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    else{
        JOptionPane.showMessageDialog(this, "check if u have entered valic entries");
    }
    }//GEN-LAST:event_tncancelActionPerformed

    private void btnuncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuncancelActionPerformed
        // TODO add your handling code here:
    if(checkfields()){    
        String tNum=txttrainnum.getText();
        if(tNum.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Train Number");
        }
        else{
            try {
                //Req_Res res=new Req_Res();
                ViewTrain train=new ViewTrain();
                train.settrainNum(tNum);
                String Res=rr.uncancelTrain(train);
                if(Res.equals("valid")){
                    System.out.println(Res+"3\n");
                    JOptionPane.showMessageDialog(this,"Train Successfully Uncancelled");
                }
                else{
                    JOptionPane.showMessageDialog(this,Res);
                    System.out.println("\n"+Res);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CancelTrain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    else{
        JOptionPane.showMessageDialog(this, "check if u have entered valic entries");
    }
    }//GEN-LAST:event_btnuncancelActionPerformed

    private void txttrainnumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttrainnumKeyReleased
        // TODO add your handling code here:
        
       String PATTERN="^[0-9]{2,5}$";
       Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(txttrainnum.getText());
        if(!match.matches()){
           jLabel3.setText("invalid password");        }
       else{
            jLabel3.setText(null);
        }
    }//GEN-LAST:event_txttrainnumKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        new AdminMainInterface(rr, adminid).show();
    }//GEN-LAST:event_jButton1ActionPerformed
   public boolean checkfields(){
        if (jLabel3.getText()=="invalid"){
         return false;
        }
        else{
            return true;
       }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnuncancel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton tncancel;
    private javax.swing.JTextField txttrainnum;
    // End of variables declaration//GEN-END:variables
}
