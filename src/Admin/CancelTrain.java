/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import javax.swing.JOptionPane;
import Req_Res.Req_Res;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmish
 */
public class CancelTrain extends javax.swing.JFrame {
    //public String tnum;
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
//    public CancelTrain(String tnum){
//        this.tnum=tnum;
//    }
//    public void settrainnum(String tnum){
//        this.tnum=tnum;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txttrainnum = new javax.swing.JTextField();
        tncancel = new javax.swing.JButton();
        btnuncancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cancel and Uncancel Trains");

        jLabel2.setText("Train Number:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(242, 242, 242))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(txttrainnum, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(tncancel)
                        .addGap(164, 164, 164)
                        .addComponent(btnuncancel)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttrainnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tncancel)
                    .addComponent(btnuncancel))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tncancelActionPerformed
        // TODO add your handling code here:
        String tNum=txttrainnum.getText();
        if(tNum.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Train Number");
        }
        else{
            try {
                //Req_Res res=new Req_Res();
                ViewTrain train=new ViewTrain();
                train.settrainNum(tNum);
                String Res=rr.canceltrain(train);
                if(Res.equals("valid")){
                    System.out.println(Res+"2\n");
                    JOptionPane.showMessageDialog(this,"Train Successfully Cancelled");
                }
                else{
                    JOptionPane.showMessageDialog(this,Res);
                    System.out.println("\n"+Res);
                }
            } catch (IOException ex) {
                Logger.getLogger(CancelTrain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_tncancelActionPerformed

    private void btnuncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuncancelActionPerformed
        // TODO add your handling code here:
        String tNum=txttrainnum.getText();
        if(tNum.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Train Number");
        }
        else{
            try {
                //Req_Res res=new Req_Res();
                ViewTrain train=new ViewTrain();
                train.settrainNum(tNum);
                String Res=rr.uncanceltrain(train);
                if(Res.equals("valid")){
                    System.out.println(Res+"3\n");
                    JOptionPane.showMessageDialog(this,"Train Successfully Uncancelled");
                }
                else{
                    JOptionPane.showMessageDialog(this,Res);
                    System.out.println("\n"+Res);
                }
            } catch (IOException ex) {
                Logger.getLogger(CancelTrain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnuncancelActionPerformed

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
//            java.util.logging.Logger.getLogger(CancelTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CancelTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CancelTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CancelTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CancelTrain().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnuncancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton tncancel;
    private javax.swing.JTextField txttrainnum;
    // End of variables declaration//GEN-END:variables
}
