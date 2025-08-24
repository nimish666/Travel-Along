/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AM;

import AM.Connection;
import AM.Connection;
import AM.SeePost;
import AM.SeePost;
import AM.global;
import com.mysql.cj.xdevapi.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author karan
 */
public class Rentals extends javax.swing.JFrame {
    int rank = 1;
    int user_id = global.user_id;
    int max_rank = 0;
    public void doasloads() throws SQLException
    {
        rank = 1;
        try {
            Statement st = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            java.sql.Connection con = null;
            
            System.out.print("connecting");
            Class.forName("com.mysql.cj.jdbc.Driver");
            //java.sql.Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelalong", "root", global.password);
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            pst = con.prepareStatement("with cte1 as (Select * from user natural join preferences natural join posts natural join post where User_id = ?),\n" +
"cte2 as (select *,row_number() over (order by Stars DESC) as ranking  from rental where Address_city = (Select destination from cte1))\n" +
"select COUNT(*) as maxrank from cte2 ;");
            pst.setInt(1,this.user_id);
            rs = pst.executeQuery();
            if(rs.next()){
            max_rank = rs.getInt("maxrank");}
             pst = con.prepareStatement("with cte1 as (Select * from user natural join preferences natural join posts natural join post where User_id = ?),\n" +
"cte2 as (select *,row_number() over (order by Stars DESC) as ranking  from rental where Address_city = (Select destination from cte1))\n" +
"select * from cte2 where ranking = 1");
            pst.setInt(1,this.user_id);
            rs = pst.executeQuery();
            if(rs.next())
            {   
                this.setVisible(true);
                String tostring = rs.getString("Contact");
                //String tostring = contacts.toString();
                Integer star = rs.getInt("Stars");
                String tostring1 = star.toString();
                name.setText(rs.getString(("Name")));
                address1.setText(rs.getString(("Address_general_text")));
                address2.setText(rs.getString(("Address_city")));
                
                contact.setText(tostring); 
                stars.setText(tostring1);
                
            }
            else 
            {JOptionPane.showMessageDialog(this, "Sorry, no recommendations found");
            }    
            
            
            
        
    }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
public void doasloadsmod(int i) throws SQLException{
        rank = i;
        
        try {
            Statement st = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            java.sql.Connection con = null;
            
            System.out.print("connecting");
            Class.forName("com.mysql.cj.jdbc.Driver");
            //java.sql.Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelalong", "root", global.password);
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            pst = con.prepareStatement("with cte1 as (Select * from user natural join preferences natural join posts natural join post where User_id = ?),\n" +
"cte2 as (select *,row_number() over (order by Stars DESC) as ranking  from rental where Address_city = (Select destination from cte1))\n" +
"select * from cte2 where ranking = ?");
            
            pst.setInt(1,this.user_id);
            pst.setInt(2,rank);
            
            
            rs = pst.executeQuery();
             
            if(rs.next()){
             
                 String tostring = rs.getString("Contact");
                Integer star = rs.getInt("Stars");
                String tostring1 = star.toString();
                name.setText(rs.getString(("Name")));
                address1.setText(rs.getString(("Address_general_text")));
                address2.setText(rs.getString(("Address_city")));
                
                contact.setText(tostring); 
                stars.setText(tostring1);
                
            }   

// }

        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    /**
     * Creates new form Rentals
     */
    public Rentals() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        try {
            doasloads();
        } catch (SQLException ex) {
            Logger.getLogger(Rentals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        stars = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        address2 = new javax.swing.JLabel();
        address1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jButton3.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jButton3.setText("Next");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jButton1.setText("Previous");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        stars.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        stars.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stars.setText("jLabel10");

        jLabel7.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel7.setText("Stars:");

        contact.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        contact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contact.setText("jLabel9");

        jLabel6.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel6.setText("Contact:");

        address2.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        address2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address2.setText("jLabel4");

        address1.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        address1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address1.setText("jLabel3");

        jLabel2.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel2.setText("Address:");

        jLabel3.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel3.setText("Name:");

        name.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("jLabel4");

        jLabel1.setFont(new java.awt.Font("Garamond", 0, 48)); // NOI18N
        jLabel1.setText("Rentals");

        jButton7.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jButton7.setText("Back");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stars, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(243, 243, 243))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7)))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name)
                        .addGap(18, 18, 18)
                        .addComponent(address1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(address2)
                        .addGap(18, 18, 18)
                        .addComponent(contact)
                        .addGap(18, 18, 18)
                        .addComponent(stars)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
rank --;
        if(rank <= 0){
            JOptionPane.showMessageDialog(this,"Can't Go back more");
            rank = 1;
        }
        else{
            try {
            doasloadsmod(rank);
        } catch (SQLException ex) {
            Logger.getLogger(SeePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        rank++;
        if(rank>max_rank){
           JOptionPane.showMessageDialog(this,"That's all for the recommendations. ");
           rank--;
        }
        else{
        try {
            doasloadsmod(rank);
        } catch (SQLException ex) {
            Logger.getLogger(SeePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Rentals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rentals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rentals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rentals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rentals().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address1;
    private javax.swing.JLabel address2;
    private javax.swing.JLabel contact;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel name;
    private javax.swing.JLabel stars;
    // End of variables declaration//GEN-END:variables
}
