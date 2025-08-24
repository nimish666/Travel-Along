/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AM;

//import AM.Connection;
import AM.Connection;
import AM.Connection;
import AM.SeePost;
import AM.SeePost;
import AM.global;
import AM.global;
import com.mysql.cj.xdevapi.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author karan
 */

public class Restaurants extends javax.swing.JFrame {
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
"cte2 as (select *,row_number() over (order by Stars DESC) as ranking  from restaurant where Address_city = (Select destination from cte1)\n" +
"and Cuisine = (select cuisine from cte1))\n" +
"select COUNT(*) as maxrank from cte2 ;");
            pst.setInt(1,this.user_id);
            rs = pst.executeQuery();
            if(rs.next()){
            max_rank = rs.getInt("maxrank");}
             pst = con.prepareStatement("with cte1 as (Select * from user natural join preferences natural join posts natural join post where User_id = ?),\n" +
"cte2 as (select *,row_number() over (order by Stars DESC) as ranking  from restaurant where Address_city = (Select destination from cte1)\n" +
"and Cuisine = (select cuisine from cte1))\n" +
"select * from cte2 where ranking = 1;");
            pst.setInt(1,this.user_id);
            rs = pst.executeQuery();
            if(rs.next())
            {
                this.setVisible(true);
                String tostring = rs.getString("Contact");
                Integer star = rs.getInt("Stars");
                String tostring1 = star.toString();
                name.setText(rs.getString(("name")));
                address1.setText(rs.getString(("Address_gen_text")));
                address2.setText(rs.getString(("Address_city")));
                cuisine.setText(rs.getString(("Cuisine")));
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
"cte2 as (select *,row_number() over (order by Stars DESC) as ranking  from restaurant where Address_city = (Select destination from cte1)\n" +
"and Cuisine = (select cuisine from cte1))\n" +
"select * from cte2 where ranking = ?;");
            
            pst.setInt(1,this.user_id);
            pst.setInt(2,rank);
            
            
            rs = pst.executeQuery();
             
            if(rs.next()){
             
                 String tostring = rs.getString("Contact");
                Integer star = rs.getInt("Stars");
                String tostring1 = star.toString();
                name.setText(rs.getString(("name")));
                address1.setText(rs.getString(("Address_gen_text")));
                address2.setText(rs.getString(("Address_city")));
                cuisine.setText(rs.getString(("Cuisine")));
                contact.setText(tostring); 
                stars.setText(tostring1);
                
            }

// }

        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    /**
     * Creates new form Restaurants
     */
    public Restaurants() throws SQLException {
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
        jLabel2 = new javax.swing.JLabel();
        address1 = new javax.swing.JLabel();
        address2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cuisine = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        stars = new javax.swing.JLabel();
        previous = new javax.swing.JButton();
        next = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Garamond", 0, 48)); // NOI18N
        jLabel1.setText("Restaurants");

        jLabel2.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel2.setText("Address:");

        address1.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        address1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address1.setText("jLabel3");

        address2.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        address2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        address2.setText("jLabel4");

        jLabel3.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel3.setText("Name:");

        name.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel5.setText("Cuisine:");

        jLabel6.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel6.setText("Contact:");

        jLabel7.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel7.setText("Stars:");

        cuisine.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        cuisine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cuisine.setText("jLabel8");

        contact.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        contact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contact.setText("jLabel9");

        stars.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        stars.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stars.setText("jLabel10");

        previous.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        previous.setText("Previous");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        next.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

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
                .addContainerGap()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(337, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cuisine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stars, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(254, 254, 254))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(name))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(address1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(address2)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cuisine))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contact)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(stars))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
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
        }        
        // TODO add your handling code here:
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
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
        }// TODO add your handling code here:
    }//GEN-LAST:event_nextActionPerformed

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
            java.util.logging.Logger.getLogger(Restaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restaurants.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Restaurants().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Restaurants.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address1;
    private javax.swing.JLabel address2;
    private javax.swing.JLabel contact;
    private javax.swing.JLabel cuisine;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel name;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    private javax.swing.JLabel stars;
    // End of variables declaration//GEN-END:variables
}
