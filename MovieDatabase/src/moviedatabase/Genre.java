/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Sherly's-Notebook
 */
public class Genre extends javax.swing.JFrame {

    /**
     * Creates new form CreateGenre
     */
    
    private int id;
    private boolean check;
    Map<Integer, JButton> btnGenre = new HashMap<Integer, JButton>();
    
    public Genre(boolean check) {
        initComponents();
        this.check = check;
        
        if(Session.getStatus()){
            lblUser.setText(Session.getUsername());
            btnLogout.setVisible(true);
        } else {
            btnLogout.setVisible(false);
        }
        if(check){
            tglDelete.setSelected(true);
            txtAdd.setEditable(false);
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(false);
        } else{
            tglDelete.setSelected(false);
            txtAdd.setEditable(true);
            btnAdd.setEnabled(true);
            btnAdd.setEnabled(true);
        }
        txtEdit.setEditable(false);
        showGenre();
    }
    
    public void showGenre(){
        String sql = "SELECT * FROM Genre";
        pnlGenre.setLayout(new WrapLayout());
        try{
            Connection conn = SQLiteJDBCDriverConnection.connect();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                if(rs.getInt("id")==0){
                    continue;
                }
                int id = rs.getInt("id");                
                String genre = rs.getString("genre");                
                btnGenre.put(id, new JButton(genre){
                    {
                        setSize(1500, 200);
                        setMaximumSize(getSize());
                    }
                });
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        showButton();
    }
    
    public void showButton(){    
        if(this.check){
            tglDelete.setText("Cancel");
            for(int key : btnGenre.keySet()){
                btnGenre.get(key).setText("-" + btnGenre.get(key).getText() + "-");
                btnGenre.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
                btnGenre.get(key).setHorizontalTextPosition(SwingConstants.LEFT);
                pnlGenre.add(btnGenre.get(key));
                btnGenre.get(key).addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGenreActionPerformedDelete(evt, key);
                    }
                });
            }
        }else{
            tglDelete.setText("Delete");
            for(int key : btnGenre.keySet()){
                btnGenre.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
                btnGenre.get(key).setHorizontalTextPosition(SwingConstants.LEFT);
                pnlGenre.add(btnGenre.get(key));
                btnGenre.get(key).addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGenreActionPerformed(evt, key);
                    }
                });
            }
        }
    }
    
    public void deleteGenre(int id, int index){
        String sql;
        if(index==1){
            sql = "UPDATE Movie SET genre1 = 0 WHERE id = ?";
            Connection conn = SQLiteJDBCDriverConnection.connect();
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);   
                ps.executeUpdate();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }else if(index==2){
            sql = "UPDATE Movie SET genre2 = 0 WHERE id = ?";
            Connection conn = SQLiteJDBCDriverConnection.connect();
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);   
                ps.executeUpdate();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }else if(index==3){
            sql = "UPDATE Movie SET genre3 = 0 WHERE id = ?";
            Connection conn = SQLiteJDBCDriverConnection.connect();
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);   
                ps.executeUpdate();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }else if(index==4){
            sql = "UPDATE Movie SET genre4 = 0 WHERE id = ?";
            Connection conn = SQLiteJDBCDriverConnection.connect();
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);   
                ps.executeUpdate();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }else if(index==5){
            sql = "UPDATE Movie SET genre5 = 0 WHERE id = ?";
            Connection conn = SQLiteJDBCDriverConnection.connect();
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);   
                ps.executeUpdate();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void checkMovie(int id){
        String sql = "SELECT * FROM Movie";
        try {
            Connection conn = SQLiteJDBCDriverConnection.connect();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                if(rs.getInt("genre1")==id){
                    deleteGenre(rs.getInt("id"), 1);
                }else if(rs.getInt("genre2")==id){
                    deleteGenre(rs.getInt("id"), 2);
                }else if(rs.getInt("genre3")==id){
                    deleteGenre(rs.getInt("id"), 3);
                }else if(rs.getInt("genre4")==id){
                    deleteGenre(rs.getInt("id"), 4);
                }else if(rs.getInt("genre5")==id){
                    deleteGenre(rs.getInt("id"), 5);
                }
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean checkGenre(String genre){
        String sql = "SELECT * FROM Genre WHERE genre = ?";
        int count = 0;
        try{
           Connection c = SQLiteJDBCDriverConnection.connect();
           PreparedStatement ps = c.prepareStatement(sql);
           ps.setString(1, genre);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               count++;
           }
           if(count == 0){
               return true;
           } else {
               return false;
           }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public void save(String genre){ 
        String sql = "INSERT INTO Genre(genre) VALUES(?)";
        try {
            Connection c = SQLiteJDBCDriverConnection.connect();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, genre);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(pnlGenre,genre + " berhasil ditambahkan");
            txtAdd.setText("");
            new Genre(false).setVisible(true);
            this.dispose();    
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }   
    }

    public void edit(String genre){
        String sql = "UPDATE Genre SET genre = ? WHERE id = ?";
        Connection conn = SQLiteJDBCDriverConnection.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, genre);   
            ps.setInt(2, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(jPanel2, "Genre berhasil diedit"); 
            txtEdit.setText("");
            new Genre(false).setVisible(true);
            this.dispose();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtAdd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEdit = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        pnlGenre = new javax.swing.JPanel();
        tglDelete = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Database");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/brainware.png"))); // NOI18N

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("User");
        lblUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Create");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout)
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Genre");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Klik genre untuk edit");

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Edit");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlGenre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlGenreLayout = new javax.swing.GroupLayout(pnlGenre);
        pnlGenre.setLayout(pnlGenreLayout);
        pnlGenreLayout.setHorizontalGroup(
            pnlGenreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        pnlGenreLayout.setVerticalGroup(
            pnlGenreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );

        tglDelete.setText("Delete");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tglDelete)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(205, 205, 205))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdd)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdit)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglDelete))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenreActionPerformed(java.awt.event.ActionEvent evt, int id){
        String sql = "SELECT * FROM Genre WHERE id = ? ";        
        String genre = null;
        Connection conn = SQLiteJDBCDriverConnection.connect();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);           
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                genre = rs.getString("genre");
            }           
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        txtEdit.setEditable(true);
        txtEdit.setText(genre);
        this.id = id;
    }
    
    private void btnGenreActionPerformedDelete(java.awt.event.ActionEvent evt, int id){
        int confirm = JOptionPane.showConfirmDialog(pnlGenre, "Apakah anda yakin?", "Confirm delete", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            checkMovie(id);
            String sql = "DELETE FROM Genre WHERE id = ?";
            try{
                Connection conn = SQLiteJDBCDriverConnection.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Genre berhasil dihapus");
                new Genre(true).setVisible(true);
                this.dispose();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?", "Logout", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            Logout.logout();
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if(txtAdd.equals("")){
        JOptionPane.showMessageDialog(jPanel2, "Data masih kosong!");
        }else{
            boolean result = false;
            genre = txtAdd.getText();
            try{
                result = check(genre);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if(result){
                try{
                    save(genre);
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(jPanel2, "Genre sudah ada!");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(Genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Genre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Genre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlGenre;
    private javax.swing.JToggleButton tglDelete;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtEdit;
    // End of variables declaration//GEN-END:variables
}
