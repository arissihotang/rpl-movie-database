/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_database;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Win8
 */
public class Search extends javax.swing.JFrame {

    /**
     * Creates new form Search
     */
    public Search() {
        initComponents();
        if(Session.getStatus()){
            //lblUsername.setText("Selamat datang, " + Session.getUsername());
            btnLogin.setVisible(false);
            btnLogout.setVisible(true);
            if(Session.getRole()==1){
                //btnTambah.setVisible(true);
            } else {
                //btnTambah.setVisible(false);
            }
        }else{
            //btnTambah.setVisible(false);
            btnLogout.setVisible(false);
            btnLogin.setVisible(true);
        }
        lblTahun.setVisible(false);
        lblGenre.setVisible(false);
        txtSearchTahun.setVisible(false);
        cbxSearchGenre.setVisible(false);
    }
    
    public void search(){
        jPanel2.removeAll();
        Map<Integer, JButton> btnGambar = new HashMap<Integer, JButton>();
        jPanel2.setLayout(new GridLayout(0, 4));
        int count = 0;
        if(txtSearch.getText().equals("") && !cbMetode.isSelected()){
            JOptionPane.showMessageDialog(this, "Keyword masih kosong");
        }else if(cbMetode.isSelected() && txtSearchTahun.getText().equals("") && cbxSearchGenre.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Pilih salah satu metode");
        }else{
            try{
                Connection c = Connect.connect();
                String sql;
                PreparedStatement ps = null;
                if(cbMetode.isSelected()){
                    if(txtSearchTahun.getText().equals("")){
                        sql = "SELECT * FROM Movie WHERE (judul LIKE ? OR sinopsis LIKE ?) AND (genre1 = ? OR genre2 = ? OR genre3 = ? OR genre4 = ? OR genre5 = ?)";
                        ps = c.prepareStatement(sql);
                        ps.setString(1, "%" + txtSearch.getText() + "%");
                        ps.setString(2, "%" + txtSearch.getText() + "%");
                        ps.setInt(3, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(4, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(5, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(6, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(7, cbxSearchGenre.getSelectedIndex());
                    }else if(cbxSearchGenre.getSelectedIndex() == 0){
                        sql = "SELECT * FROM Movie WHERE (judul LIKE ? OR sinopsis LIKE ?) AND tahun = ?";
                        ps = c.prepareStatement(sql);
                        ps.setString(1, "%" + txtSearch.getText() + "%");
                        ps.setString(2, "%" + txtSearch.getText() + "%");
                        ps.setInt(3, Integer.parseInt(txtSearchTahun.getText()));
                    } else{
                        sql = "SELECT * FROM Movie WHERE (judul LIKE ? OR sinopsis LIKE ?) AND tahun = ? AND (genre1 = ? OR genre2 = ? OR genre3 = ? OR genre4 = ? OR genre5 = ?)";
                        ps = c.prepareStatement(sql);
                        ps.setString(1, "%" + txtSearch.getText() + "%");
                        ps.setString(2, "%" + txtSearch.getText() + "%");
                        ps.setInt(3, Integer.parseInt(txtSearchTahun.getText()));
                        ps.setInt(4, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(5, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(6, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(7, cbxSearchGenre.getSelectedIndex());
                        ps.setInt(8, cbxSearchGenre.getSelectedIndex());
                    }
                }else{
                    sql = "SELECT * FROM Movie WHERE judul LIKE ? OR sinopsis LIKE ?";
                    ps = c.prepareStatement(sql);
                    ps.setString(1, "%" + txtSearch.getText() + "%");
                    ps.setString(2, "%" + txtSearch.getText() + "%");
                }    
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String img = Path.getPathCover() + rs.getString("gambar");
                    String judul = rs.getString("judul");
                    String tahun = rs.getString("tahun");

                    BufferedImage bimg = null;
                    try{
                        bimg = ImageIO.read(new File(img));
                    } catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                    Image image = bimg.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                    ImageIcon ii = new ImageIcon(image);

                    btnGambar.put(id, new JButton(judul + " (" + tahun + ")", ii){
                        {
                            setSize(145, 210);
                            setMaximumSize(getSize());

                        }
                    });
                    count++;
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }

            if(count == 0){
                SwingUtilities.updateComponentTreeUI(this);
                JOptionPane.showMessageDialog(this, "Hasil pencarian tidak ditemukan");
            } else {
                show(btnGambar);
            }
        }
    }
    
    private void show(Map<Integer, JButton> btnImage){
        SwingUtilities.updateComponentTreeUI(this);
        for(int key : btnImage.keySet()){
            btnImage.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
            btnImage.get(key).setHorizontalTextPosition(SwingConstants.CENTER);
            
            jPanel2.add(btnImage.get(key));
            
            btnImage.get(key).addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnImageActionPerformed(evt, key);
                }
            });
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

        jLabel6 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblTahun = new javax.swing.JLabel();
        txtSearchTahun = new javax.swing.JTextField();
        lblGenre = new javax.swing.JLabel();
        cbxSearchGenre = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        cbMetode = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Movie Database");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel1.setText("Search");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lblTahun.setText("Tahun");

        txtSearchTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchTahunActionPerformed(evt);
            }
        });

        lblGenre.setText("Genre");

        cbxSearchGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbMetode.setText("metode lain");
        cbMetode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodeActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTahun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSearchGenre, 0, 112, Short.MAX_VALUE)
                                    .addComponent(txtSearch)
                                    .addComponent(txtSearchTahun))
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch))
                            .addComponent(cbMetode))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMetode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahun)
                    .addComponent(txtSearchTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenre)
                    .addComponent(cbxSearchGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogout))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?", "Confirm logout", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            Logout.logout();
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchTahunActionPerformed

    private void cbMetodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodeActionPerformed
        // TODO add your handling code here:
        if(cbMetode.isSelected()){
            lblTahun.setVisible(true);
            lblGenre.setVisible(true);
            txtSearchTahun.setVisible(true);
            cbxSearchGenre.setVisible(true);
        } else {
            lblTahun.setVisible(false);
            lblGenre.setVisible(false);
            txtSearchTahun.setVisible(false);
            cbxSearchGenre.setVisible(false);
        }
    }//GEN-LAST:event_cbMetodeActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt, int id){
        new Read().read(id);
        this.dispose();
    }
    
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
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox cbMetode;
    private javax.swing.JComboBox cbxSearchGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGenre;
    private javax.swing.JLabel lblTahun;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchTahun;
    // End of variables declaration//GEN-END:variables
}
