/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AM;

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ANSHUL M
 */
public class Likes extends javax.swing.JFrame {
    
    int rank = 1;
    int dispose_flag = 0;
    int this_user_id = global.user_id;
    int this_post_id = global.post_id;
    int current_user_id = 10000000;
    String curr_user_fname ;
    String curr_user_lname ;
    
    //int current_post_id = 10000000;
    int max_rank = 0;
    HashMap<Integer, Integer> mymap = new HashMap<>();
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
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void prehash() throws SQLException{
            
            prequery();
            pst = con.prepareStatement("with cte1 as (select Liked_by from travelalong.likes where Post_ID = ?),\n" +
"cte2 as (Select * from user natural join preferences natural join posts natural join post),\n" +
"cte3 as (Select * from cte1 join cte2 on cte1.liked_by = cte2.user_id ),\n" +
"cte4 as \n" +
"(select *,case when Budget = (select Budget from travelalong.preferences where User_ID = ?) then 1 else 0 end\n" +
"+  case when eating_pref = (select eating_pref from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Travel_activities = (select Travel_activities from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when AccomodationPref = (select AccomodationPref from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Cuisine = (select Cuisine from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Drinker = (select Drinker from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when personality_type = (select personality_type from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when question = (select question from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
"as score from cte3),\n" +
"cte5 as (select *,row_number() over (ORDER BY score DESC) as ranking from cte4 order by ranking),\n" +
"cte6 as (select user_id_1 as matched_id from travelalong.matches where user_id_2 = ?),\n" +
"cte7 as (select user_id_2 as matched_id from travelalong.matches where user_id_1 = ?),\n" +
"cte8 as (select * from cte6 union all select * from cte7) \n" +
"select ranking from cte5 where liked_by in (select matched_id from cte8);");
        
            pst.setInt(1,this_post_id);
            for(int i = 2 ; i < 10 ; i++ ){
                pst.setInt(i,this_user_id);
            }
            pst.setInt(10,this_user_id);
            pst.setInt(11,this_user_id);
            
            rs = pst.executeQuery();
        while(rs.next()){
            int ranker = rs.getInt("ranking");
            mymap.put(ranker,1);
            //rs = rs.next();
        }
        
        
        }
    public void doasloads3() throws SQLException{
        prehash();
        rank = 1;
        
        System.out.println(this_post_id);
        
        if(mymap.containsKey(rank) && mymap.get(rank) == 1){
            jButton2.setText("Unmatch");
        }
        else{
            jButton2.setText("Match");
        }
        
        try {
            prequery();
            pst = con.prepareStatement("select count(*) as maxirank from travelalong.likes where Post_ID = ?;");
            pst.setInt(1,this_post_id);
            rs = pst.executeQuery();
            if(rs.next()){
                max_rank = rs.getInt("maxirank");
            }
            pst = con.prepareStatement("with cte1 as (select Liked_by from travelalong.likes where Post_ID = ?),\n" +
"cte2 as (Select * from user natural join preferences natural join posts natural join post),\n" +
"cte3 as (Select * from cte1 join cte2 on cte1.liked_by = cte2.user_id ),\n" +
"cte4 as \n" +
"(select *,case when Budget = (select Budget from travelalong.preferences where User_ID = ?) then 1 else 0 end\n" +
"+  case when eating_pref = (select eating_pref from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Travel_activities = (select Travel_activities from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when AccomodationPref = (select AccomodationPref from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Cuisine = (select Cuisine from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Drinker = (select Drinker from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when personality_type = (select personality_type from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when question = (select question from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
"as score from cte3),\n" +
"cte5 as (select *,row_number() over (ORDER BY score DESC) as ranking from cte4 order by ranking)\n" +
"select * from cte5 where ranking = ?;");
            pst.setInt(1,this_post_id);
            for(int i = 2 ; i < 10 ; i++ ){
                pst.setInt(i,this_user_id);
            }
            pst.setInt(10,1);
            rs = pst.executeQuery();
            if(rs.next()){
                global.rs = rs;
                this.setVisible(true);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
                String date1 = df.format(rs.getDate("Date_from")); 
                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");  
                String date2 = df2.format(rs.getDate("Date_to")); 
                Integer a = rs.getInt("Number_of_people");
                String toput = a.toString();
                current_user_id = rs.getInt("User_ID");
                curr_user_fname = rs.getString("F_Name");
                curr_user_lname = rs.getString("L_Name");
                
            //JOptionPane.showMessageDialog(this,"Record Found.");
            //jLabel1.setText("Hey "+rs.getString("name")+" !");
            //System.out.print(con);
            //budget_pref_label.setText("Budget : " + rs.getString("Budget"));
            date_from_label.setText("From Date : " + date1);
            date_to_label.setText("To Date : " + date2);
            details_label.setText("Description : " + rs.getString("description"));
            //eating_pref_label.setText("Eating Pref : " + rs.getString("eating_pref"));
            destination_label.setText("Destination : " + rs.getString("Destination"));
            name_label.setText(rs.getString("F_Name")+" "+rs.getString("L_Name"));
            nopa_label.setText("Number of Accomp. People : " + toput);
            jLabel7.setText("User Rating : " + rs.getInt("Rating"));
            //rating_pref_label.setText(rs.getString("Rating_pref"));
//            travel_activities_label.setText("They Like " + rs.getString("Travel_activities"));
//            Question.setText("They Like " + rs.getString("question"));
//            Drinker.setText("Drinking Pref : " + rs.getString("Drinker"));
//            Personality.setText("Personality :" + rs.getString("personality_type"));
//            Cuisine.setText("Cuisine Pref : " + rs.getString("Cuisine"));
//            AccomodationPref.setText("Accomodation Pref : " + rs.getString("AccomodationPref"));
            score.setText("Comapatibility Score : " + rs.getInt("score"));
            
            
         }
            else{
               JOptionPane.showMessageDialog(this,"Sorry ! Nobody Liked Your Post");   
               //this.dispose();
               dispose_flag = 1;
           }
            
        }
        
        catch(Exception ex){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void doasloadsmod2(int modind) throws SQLException{
        
        //prequery();
        
        rank = modind;
        
        if(mymap.containsKey(rank) && mymap.get(rank) == 1){
            jButton2.setText("Unmatch");
        }
        else{
            jButton2.setText("Match");
        }
        
        try {
            prequery();
            
pst = con.prepareStatement("with cte1 as (select Liked_by from travelalong.likes where Post_ID = ?),\n" +
"cte2 as (Select * from user natural join preferences natural join posts natural join post),\n" +
"cte3 as (Select * from cte1 join cte2 on cte1.liked_by = cte2.user_id ),\n" +
"cte4 as \n" +
"(select *,case when Budget = (select Budget from travelalong.preferences where User_ID = ?) then 1 else 0 end\n" +
"+  case when eating_pref = (select eating_pref from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Travel_activities = (select Travel_activities from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when AccomodationPref = (select AccomodationPref from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Cuisine = (select Cuisine from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when Drinker = (select Drinker from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when personality_type = (select personality_type from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
" +  case when question = (select question from travelalong.preferences where User_ID = ?) then 1 else 0 end \n" +
"as score from cte3),\n" +
"cte5 as (select *,row_number() over (ORDER BY score DESC) as ranking from cte4 order by ranking)\n" +
"select * from cte5 where ranking = ?;");
            pst.setInt(1,this_post_id);
            for(int i = 2 ; i < 10 ; i++ ){
                pst.setInt(i,this_user_id);
            }
            pst.setInt(10,rank);
            rs = pst.executeQuery();
            if(rs.next()){
                global.rs = rs;
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
                String date1 = df.format(rs.getDate("Date_from")); 
                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");  
                String date2 = df2.format(rs.getDate("Date_to")); 
                Integer a = rs.getInt("Number_of_people");
                String toput = a.toString();
                current_user_id = rs.getInt("User_ID");
                //current_user_id = rs.getInt("User_ID");
                curr_user_fname = rs.getString("F_Name");
                curr_user_lname = rs.getString("L_Name");
                
            //JOptionPane.showMessageDialog(this,"Record Found.");
            //jLabel1.setText("Hey "+rs.getString("name")+" !");
            //budget_pref_label.setText("Budget : " + rs.getString("Budget"));
            date_from_label.setText("From Date : " + date1);
            date_to_label.setText("To Date : " + date2);
            details_label.setText("Description : " + rs.getString("description"));
            //eating_pref_label.setText("Eating Pref : " + rs.getString("eating_pref"));
            destination_label.setText("Destination : " + rs.getString("Destination"));
            name_label.setText(rs.getString("F_Name")+" "+rs.getString("L_Name"));
            nopa_label.setText("Number of Accomp. People : " + toput);
            //rating_pref_label.setText(rs.getString("Rating_pref"));
            //travel_activities_label.setText("They Like " + rs.getString("Travel_activities"));
            //Question.setText("They Like " + rs.getString("question"));
            //Drinker.setText("Drinking Pref : " + rs.getString("Drinker"));
            //Personality.setText("Personality :" + rs.getString("personality_type"));
            //Cuisine.setText("Cuisine Pref : " + rs.getString("Cuisine"));
            //AccomodationPref.setText("Accomodation Pref : " + rs.getString("AccomodationPref"));
            score.setText("Comapatibility Score : " + rs.getInt("score"));
            jLabel7.setText("User Rating : " + rs.getInt("Rating"));
            
            
         }
            else{
               JOptionPane.showMessageDialog(this,"Sorry ! No more people liked your post");  
               rank--;
           }
            
        }
        
        catch(Exception ex){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    /**
     * Creates new form Likes
     */
    public Likes() throws SQLException {
        
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,size.height/2-getHeight()/2);
        doasloads3();
        if(dispose_flag == 1){
            //this.setVisible(false);
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
        name_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        destination_label = new javax.swing.JLabel();
        date_from_label = new javax.swing.JLabel();
        date_to_label = new javax.swing.JLabel();
        nopa_label = new javax.swing.JLabel();
        details_label = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        score = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jButton3.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jButton3.setText("Next");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        name_label.setFont(new java.awt.Font("Garamond", 0, 48)); // NOI18N
        name_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name_label.setText("Name");

        jButton1.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jButton1.setText("Previous");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jButton2.setText("Match");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Details of Trip");

        destination_label.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        destination_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        destination_label.setText("jLabel10");

        date_from_label.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        date_from_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date_from_label.setText("jLabel11");

        date_to_label.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        date_to_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date_to_label.setText("jLabel12");

        nopa_label.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        nopa_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nopa_label.setText("jLabel13");

        details_label.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        details_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        details_label.setText("jLabel14");

        jButton5.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        jButton5.setText("Show Their Preferences");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        score.setFont(new java.awt.Font("Garamond", 0, 24)); // NOI18N
        score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score.setText("comp");

        jLabel7.setFont(new java.awt.Font("Garamond", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 351, Short.MAX_VALUE)
                        .addComponent(name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(353, 353, 353))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(details_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nopa_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(destination_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_from_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_to_label, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(96, 96, 96)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(181, 181, 181)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(96, 96, 96)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(28, 28, 28)
                .addComponent(name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destination_label, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(date_from_label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(date_to_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nopa_label, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(details_label, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(233, 466, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(83, 83, 83)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(mymap.containsKey(rank) && mymap.get(rank) == 1){

            int i = JOptionPane.showConfirmDialog(this,  "Do you want to Unmatch ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                // do nothing
                mymap.put(rank,0);
                jButton2.setText("Match");
                try{
                    prequery();

                    pst = con.prepareStatement("DELETE FROM `travelalong`.`matches` WHERE (`User_ID_1` = ?) and (`User_ID_2` = ?);");

                    pst.setInt(1,current_user_id);
                    pst.setInt(2,this_user_id);

                    pst.executeUpdate();

                    // mymap.put(rank,1);
                    JOptionPane.showMessageDialog(this,"You have Unmatched with"+" "+ curr_user_fname + " " + curr_user_lname);

                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(this,"Gadbad hai Daya");
                }
                if (i == JOptionPane.NO_OPTION) {
                    //System.exit(0);
                    //do nothing
                }

            }
        }
        else{
            try {

                prequery();
                pst = con.prepareStatement("Select * from travelalong.matches where (user_id_1 = ? and user_id_2 = ?) or (user_id_1 = ? and user_id_2 = ?);");
                pst.setInt(1,current_user_id);
                pst.setInt(2,this_user_id);
                pst.setInt(4,current_user_id);
                pst.setInt(3,this_user_id);
                rs = pst.executeQuery();
                if(rs.next()){
                    mymap.put(rank,1);
                jButton2.setText("Unmatch");
                JOptionPane.showMessageDialog(this,"You have been Already Matched with"+ " " + curr_user_fname + " " + curr_user_lname);
                }
                
                
                else{
                pst = con.prepareStatement("INSERT INTO travelalong.matches (`User_ID_1`,`User_ID_2`,`Post_ID`) VALUES (?,?,?)");

                pst.setInt(1,current_user_id);
                pst.setInt(2,this_user_id);
                pst.setInt(3,this_post_id);

                pst.executeUpdate();

                mymap.put(rank,1);
                jButton2.setText("Unmatch");
                JOptionPane.showMessageDialog(this,"You have been Matched with"+ " " + curr_user_fname + " " + curr_user_lname);}

                // }

        } catch (SQLException ex) {
            Logger.getLogger(SeePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        rank++;
        if(rank>max_rank){
            JOptionPane.showMessageDialog(this,"Sorry ! No more people liked your post. ");
            rank--;
        }
        else{
            try {
                doasloadsmod2(rank);
            } catch (SQLException ex) {
                Logger.getLogger(SeePost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        rank --;
        if(rank <= 0){
            JOptionPane.showMessageDialog(this,"Can't Go back more");
            rank = 1;
        }
        else{
            try {
                doasloadsmod2(rank);
            }
            catch (SQLException ex) {
                Logger.getLogger(SeePost.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            new prefshow().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(SeePost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Likes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Likes();
                } catch (SQLException ex) {
                    Logger.getLogger(Likes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date_from_label;
    private javax.swing.JLabel date_to_label;
    private javax.swing.JLabel destination_label;
    private javax.swing.JLabel details_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel name_label;
    private javax.swing.JLabel nopa_label;
    private javax.swing.JLabel score;
    // End of variables declaration//GEN-END:variables
}
