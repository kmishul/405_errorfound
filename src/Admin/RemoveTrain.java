/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Admin;
import Req_Res.Req_Res;
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
public class RemoveTrain extends javax.swing.JFrame implements Serializable{
     private final Req_Res rr;
     private final String adminid;
//    public String tnum;
//    /** Creates new form RemoveTrain */
    public RemoveTrain(Req_Res rr,String adminid) {
       initComponents();
       this.rr=rr;
       this.adminid=adminid;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        RemovetrainTF = new javax.swing.JTextField();
        RemoveTrainBT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Train Number");

        RemovetrainTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovetrainTFActionPerformed(evt);
            }
        });
        RemovetrainTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RemovetrainTFKeyReleased(evt);
            }
        });

        RemoveTrainBT.setText("isko nikalo ");
        RemoveTrainBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveTrainBTActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("REMOVE TRAINS");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(RemoveTrainBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(138, 138, 138))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(69, 69, 69)
                                .addComponent(RemovetrainTF))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(36, 36, 36)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(13, 13, 13)))))
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemovetrainTF)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60)
                .addComponent(RemoveTrainBT, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RemovetrainTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemovetrainTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemovetrainTFActionPerformed

    private void RemoveTrainBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveTrainBTActionPerformed
        if(verifyRemTrNo()&&checkfields()){
            try{
                String tNum=RemovetrainTF.getText();
                ViewTrain train= new ViewTrain();
                train.settrainNum(tNum);
                
                String Res=rr.removeTrain(train);
                    JOptionPane.showMessageDialog(this, Res);
                    System.out.println("\n"+Res);
                
            } catch (IOException ex) {
                Logger.getLogger(RemoveTrain.class.getName()).log(Level.SEVERE, null, ex);
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Error in removing");
            }
        }
        
        else{
        JOptionPane.showMessageDialog(this, "Check if u have entered valid entries");
    }
        
    }//GEN-LAST:event_RemoveTrainBTActionPerformed

    private void RemovetrainTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RemovetrainTFKeyReleased
        // TODO add your handling code here:
        
        String PATTERN="^[0-9]{2,5}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(RemovetrainTF.getText());
        if(!match.matches()){
           jLabel3.setText("invalid password");
        }
        else{
            jLabel3.setText(null);
        }
    }//GEN-LAST:event_RemovetrainTFKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        new AdminMainInterface(rr, adminid).show();
    }//GEN-LAST:event_jButton1ActionPerformed
    public boolean verifyRemTrNo(){
        String tum=RemovetrainTF.getText();
        if(tum.trim().equals("")){
            JOptionPane.showMessageDialog(this,"Abe train number to daal yrr!!");
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkfields(){
        if (jLabel3.getText().equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RemoveTrainBT;
    private javax.swing.JTextField RemovetrainTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

}
