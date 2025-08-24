/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AM;

import com.mysql.cj.xdevapi.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ANSHUL M
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
            int this_user_id = global.user_id;
           // int this_post_id = global.post_id;
            Statement st = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            java.sql.Connection con = null;
    
             public void prequery(){
            
            
            System.out.print("connecting");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Likes.class.getName()).log(Level.SEVERE, null, ex);
        }
            //java.sql.Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelalong", "root", global.password);
            } catch (SQLException ex) {
                Logger.getLogger(com.sun.jdi.connect.spi.Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void doasloads() throws SQLException{
        prequery();
        pst = con.prepareStatement("Select F_Name from user where User_ID = ?");
        pst.setInt(1,this_user_id);
        rs = pst.executeQuery();
        if(rs.next()){
            //JOptionPane.showMessageDialog(this,"Record Found.");
            jLabel1.setText("Hey "+rs.getString("F_name")+" !");
            //System.out.print(con);
//         }

// }
        }
    }
        public void isexpired() throws SQLException{
            prequery();
            pst=con.prepareStatement("SELECT * from post natural join posts where Date_from between '1990-07-31' and date_sub(current_timestamp(),INTERVAL 1 DAY) and (user_id = ? );");
            //pst.setDate(yo,date);
            pst.setInt(1,this_user_id);
            
            rs = pst.executeQuery();
            if(rs.next()){
                eraselikematch();
                pst=con.prepareStatement("DELETE FROM `travelalong`.`posts` WHERE `post_id` in (Select post_id from post where post.Date_from between '1990-07-31' and date_sub(current_timestamp(),INTERVAL 1 DAY));");
                //pst.setDate(1,date);
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this,"Your Post expired please enter a new one! ");
            }
            else{
                eraseanother();
                pst=con.prepareStatement("DELETE FROM `travelalong`.`posts` WHERE `post_id` in (Select post_id from post where post.Date_from between '1990-07-31' and date_sub(current_timestamp(),INTERVAL 1 DAY));");
                //pst.setDate(1,date);
                
                pst.executeUpdate();
                   
            }
               
        }
        
                public void ispref() throws SQLException{
        
        prequery();
        pst=con.prepareStatement("select * from travelalong.preferences where user_id = ?");
        pst.setInt(1,this_user_id);
        rs = pst.executeQuery();
        if(rs.next()){
            int i = JOptionPane.showConfirmDialog(this,  "Preferences Already Exists do you want to update preferences?", "Confirm", JOptionPane.YES_NO_OPTION);
             if (i == JOptionPane.YES_OPTION) {
                pst=con.prepareStatement("DELETE FROM `travelalong`.`preferences` WHERE (`User_ID` = ?);");
                pst.setInt(1,this_user_id);
                pst.executeUpdate();
                //JOptionPane.showMessageDialog(this,"Deleted the previous preferences , now you can enter new one ! ");
                new Preferences().setVisible(true);
             }
              if (i == JOptionPane.NO_OPTION) {
                  //this.dispose();
                        //System.exit(0);
                        //do nothing
                        //flag2 = 1;
             }
        }
        else{
            new Preferences().setVisible(true);
        }
        
        
        
    }
                
                       public void ispref1() throws SQLException{
        
        prequery();
        pst=con.prepareStatement("select * from travelalong.preferences where user_id = ?");
        pst.setInt(1,this_user_id);
        rs = pst.executeQuery();
        if(rs.next()){
            new Reccomendations().setVisible(true);
            
        }
        else{
            int i = JOptionPane.showConfirmDialog(this,  "Preferences DO NOT Exist. Enter preferences to view recommendations", "Confirm", JOptionPane.YES_NO_OPTION);
             if (i == JOptionPane.YES_OPTION) {
                
                new Preferences().setVisible(true);
             }
              if (i == JOptionPane.NO_OPTION) {
                  //this.dispose();
                        //System.exit(0);
                        //do nothing
                        //flag2 = 1;
             }
            
        }
        
        
        
    }
        
        public void eraseanother() throws SQLException{
            prequery();
            pst = con.prepareStatement("DELETE FROM `travelalong`.`likes` WHERE `Liked_by` in (Select user_id from post natural join posts where post.Date_from between '1990-07-31' and date_sub(current_timestamp(),INTERVAL 1 DAY));");
            pst.executeUpdate();
            pst = con.prepareStatement("DELETE FROM `travelalong`.`matches` WHERE (`User_ID_1` in (Select user_id from post natural join posts where post.Date_from between '1990-07-31' and date_sub(current_timestamp(),INTERVAL 1 DAY))) or (`User_ID_2` = (Select user_id from post natural join posts where post.Date_from between '1990-07-31' and date_sub(current_timestamp(),INTERVAL 1 DAY)));");
            pst.executeUpdate();
        }
        
        public void eraselikematch() throws SQLException{
            
            prequery();
            pst = con.prepareStatement("DELETE FROM `travelalong`.`likes` WHERE (`Liked_by` = ?);");
            pst.setInt(1,this_user_id);
            pst.executeUpdate();
            pst = con.prepareStatement("DELETE FROM `travelalong`.`matches` WHERE (`User_ID_1` = ?) or (`User_ID_2` = ?);");
            pst.setInt(1,this_user_id);
            pst.setInt(2,this_user_id);
            pst.executeUpdate();            
            
        }
    
        public void ispost() throws SQLException{
        
        prequery();
        pst=con.prepareStatement("select post_id from travelalong.posts where user_id = ?");
        pst.setInt(1,this_user_id);
        rs = pst.executeQuery();
        if(rs.next()){
            int i = JOptionPane.showConfirmDialog(this,  "Deatils Already Exists do you want to post new deatils?", "Confirm", JOptionPane.YES_NO_OPTION);
             if (i == JOptionPane.YES_OPTION) {
                 eraselikematch();
                pst=con.prepareStatement("DELETE FROM `travelalong`.`posts` WHERE (`User_ID` = ?);");
                pst.setInt(1,this_user_id);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this,"Deleted the previous details , now you can enter new one ! ");
                new PostTrip().setVisible(true);
             }
              if (i == JOptionPane.NO_OPTION) {
                  //this.dispose();
                        //System.exit(0);
                        //do nothing
                        //flag2 = 1;
             }
        }
        else{
            new PostTrip().setVisible(true);
        }
        
        
        
    }
    
    public Dashboard() throws SQLException {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        doasloads();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Garamond", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        jButton1.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton1.setText("Matches");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton2.setText("Recommendations");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton3.setText("Likes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton4.setText("View Trips");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton5.setText("Update Preferences");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton6.setText("Post Trip");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Logout");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton8.setText("Upgrade Subscription");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton7)))
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            isexpired();
            ispost(); 
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        global.flagger = 1;
        try {
            ispref();       // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }  
       
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            
        try {
            new SeePost();
            //System.out.println("i am here");
                    //global.jfsees = jfsee;
            //System.out.println(jfsee);
            //global.jfsees.setVisible(true);
                    
                    
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            new Likes();        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            new Match();
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int yes=JOptionPane.showConfirmDialog(this, "Are You Sure ?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(yes==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
                try {
                    // TODO add your handling code here:
                    new subscription().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
                try {
            ispref1();       // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Dashboard().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
