/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AM;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANSHUL M
 */
public class Connection {
        public static void main(String [] args){
        try {
            //Statement st = null;
            //PreparedStatement pst = null;
            //ResultSet rs = null;
            //java.sql.Connection con = null;
            
            //System.out.print("connecting");
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testalong", "root", global.password );
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            //pst = con.prepareStatement("Select * from test1 where ID = ?");
            //pst.setString(1,"1");
            //rs = pst.executeQuery();
             
            //if(rs.next()){
            //JOptionPane.showMessageDialog(this,"Record Found.");
            //jLabel1.setText(rs.getString("name"));
            System.out.print(con);
//         }

// }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
    }
}
