/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Admin;
import Req_Res.Req_Res;
import java.io.IOException;
import java.io.ObjectInputStream;
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
//    public RemoveTrain (String tnum){
//        this.tnum=tnum;
//        
//    }
//    public void setremTrNo(String tnum){
//        this.tnum=tnum;
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        RemovetrainTF = new javax.swing.JTextField();
        RemoveTrainBT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

        jLabel2.setText("remove train");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(RemovetrainTF, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RemoveTrainBT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemovetrainTF, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemoveTrainBT, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RemovetrainTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemovetrainTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemovetrainTFActionPerformed

    private void RemoveTrainBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveTrainBTActionPerformed
        if(verifyRemTrNo()){
            try{
                String tNum=RemovetrainTF.getText();
                //Req_Res remtr=new Req_Res();
                ViewTrain train= new ViewTrain();
                train.settrainNum(tNum);
                
                String Res=rr.removeTrain(train);
//                if(Res.equals("removetrainvalid")){
//                    JOptionPane.showMessageDialog(this, "train removed successfully");
//                    this.dispose();
                //}
                //else{
                    JOptionPane.showMessageDialog(this, Res);
                    System.out.println("\n"+Res);
                //}
            } catch (IOException ex) {
                Logger.getLogger(RemoveTrain.class.getName()).log(Level.SEVERE, null, ex);
            }catch(Exception e){
                System.out.println(e);
                System.out.println("Error in removing");
            }
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
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RemoveTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RemoveTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RemoveTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RemoveTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RemoveTrain().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RemoveTrainBT;
    private javax.swing.JTextField RemovetrainTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

}
