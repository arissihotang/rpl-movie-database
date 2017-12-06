/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Comment extends javax.swing.JFrame {
    private int userId = Session.getId(), movieId;
    private String user, comment;
    private boolean check = false;
    /**
     * Creates new form Comment
     */
    public Comment(int movieId, boolean check) {
        initComponents();
        lblUser.setText(Session.getUsername());
        this.movieId = movieId;
        this.check = check;
        if(this.check){
            btnDelete.setVisible(true);
            show();
        }else{
            btnDelete.setVisible(false);
        }
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    public void show(){
        String sql = "SELECT comment From Comment WHERE id_user = ? AND id_movie = ?";
        Connection c = SQLiteJDBCDriverConnection.connect();
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                txtComment.setText(rs.getString("comment"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void add(){
        String sql = "SELECT user_comment FROM Movie WHERE id = ?";
        Connection c = SQLiteJDBCDriverConnection.connect();
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = rs.getString("user_comment");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        user = user + "," + userId;
        sql = "UPDATE Movie SET user_comment = ? WHERE id = ?";
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, movieId);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void comment(){
        String sql = "INSERT INTO Comment(id_user, id_movie, comment) VALUES(?, ?, ?)";
        Connection c = SQLiteJDBCDriverConnection.connect();
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ps.setString(3, txtComment.getText());
            ps.executeUpdate();
            add();
            JOptionPane.showMessageDialog(this, "comment berhasil ditambahkan");
            new Read().read(movieId, 1);
            this.dispose();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void edit(){
        String sql = "UPDATE Comment SET comment = ? WHERE id_user = ? AND id_movie = ?";
        Connection c = SQLiteJDBCDriverConnection.connect();
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, txtComment.getText());
            ps.setInt(2, userId);
            ps.setInt(3, movieId);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "comment berhasil diedit");
            new Read().read(movieId, 1);
            this.dispose();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void update(){
        String sql = "SELECT user_comment FROM Movie WHERE id = ?";
        Connection c = SQLiteJDBCDriverConnection.connect();
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = rs.getString("user_comment");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        String[] temp = user.split(",");
        user = "0";
        for(int i=0;i<temp.length;i++){
            if(Integer.parseInt(temp[i])==userId){
                temp[i] = "0";
            }
            if(i == 0){
                continue;
            }else{
                user = user + "," + temp[i];
            }
        }
        sql = "UPDATE Movie SET user_comment = ? WHERE id = ?";
        try{
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, movieId);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(){
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah anda yakin?", "Delete", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            String sql = "DELETE FROM Comment WHERE id_user = ? AND id_movie = ?";
            Connection c = SQLiteJDBCDriverConnection.connect();
            try{
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, movieId);
                ps.executeUpdate();
                update();
                JOptionPane.showMessageDialog(this, "comment berhasil dihapus");
                new Read().read(movieId, 1);
                this.dispose();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
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
        btnDelete = new javax.swing.JButton();
        btnComment = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Database");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/brainware.png"))); // NOI18N

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setText("User");
        lblUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Comment");
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogout)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnComment.setText("Comment");
        btnComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommentActionPerformed(evt);
            }
        });

        txtComment.setColumns(20);
        txtComment.setRows(5);
        jScrollPane1.setViewportView(txtComment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnComment, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(334, 334, 334))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComment)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(btnCancel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        new Read().read(movieId, 1);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommentActionPerformed
        // TODO add your handling code here:
        if(txtComment.getText().equals("")){
            JOptionPane.showMessageDialog(this, "data masih kosong");
        }else if(this.check){
            edit();
        }else{
            comment();
        }
    }//GEN-LAST:event_btnCommentActionPerformed

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
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comment(0, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnComment;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextArea txtComment;
    // End of variables declaration//GEN-END:variables
}
