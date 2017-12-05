/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
            btnLogin.setVisible(false);
            btnLogout.setVisible(true);
        }else{
            btnLogout.setVisible(false);
            btnLogin.setVisible(true);
        }
        lblTahun.setVisible(false);
        lblGenre.setVisible(false);
        txtSearchTahun.setVisible(false);
        cbxSearchGenre.setVisible(false);
    }

//    public void search(){
//        jPanel2.removeAll();
//        Map<Integer, JButton> btnImage = new HashMap<Integer, JButton>();
//        jPanel2.setLayout(new GridLayout(0, 4));
//        int count = 0;
//        if(txtSearch.getText().equals("") && !cbMetode.isSelected()){
//            JOptionPane.showMessageDialog(this, "Keyword masih kosong");
//        }else if(cbMetode.isSelected() && txtSearchTahun.getText().equals("") && cbxSearchGenre.getSelectedIndex() == 0){
//            JOptionPane.showMessageDialog(this, "Pilih salah satu metode");
//        }else{
//            try{
//                Connection c = SQLiteJDBCDriverConnection.connect();
//                String sql;
//                PreparedStatement ps = null;
//                if(cbMetode.isSelected()){
//                    if(txtSearchTahun.getText().equals("")){
//                        sql = "SELECT * FROM Movie WHERE (judul LIKE ? OR sinopsis LIKE ?) AND (genre1 = ? OR genre2 = ? OR genre3 = ? OR genre4 = ? OR genre5 = ?)";
//                        ps = c.prepareStatement(sql);
//                        ps.setString(1, "%" + txtSearch.getText() + "%");
//                        ps.setString(2, "%" + txtSearch.getText() + "%");
//                        ps.setInt(3, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(4, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(5, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(6, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(7, cbxSearchGenre.getSelectedIndex());
//                    }else if(cbxSearchGenre.getSelectedIndex() == 0){
//                        sql = "SELECT * FROM Movie WHERE (judul LIKE ? OR sinopsis LIKE ?) AND tahun = ?";
//                        ps = c.prepareStatement(sql);
//                        ps.setString(1, "%" + txtSearch.getText() + "%");
//                        ps.setString(2, "%" + txtSearch.getText() + "%");
//                        ps.setInt(3, Integer.parseInt(txtSearchTahun.getText()));
//                    } else{
//                        sql = "SELECT * FROM Movie WHERE (judul LIKE ? OR sinopsis LIKE ?) AND tahun = ? AND (genre1 = ? OR genre2 = ? OR genre3 = ? OR genre4 = ? OR genre5 = ?)";
//                        ps = c.prepareStatement(sql);
//                        ps.setString(1, "%" + txtSearch.getText() + "%");
//                        ps.setString(2, "%" + txtSearch.getText() + "%");
//                        ps.setInt(3, Integer.parseInt(txtSearchTahun.getText()));
//                        ps.setInt(4, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(5, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(6, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(7, cbxSearchGenre.getSelectedIndex());
//                        ps.setInt(8, cbxSearchGenre.getSelectedIndex());
//                    }
//                }else{
//                    sql = "SELECT * FROM Movie WHERE judul LIKE ? OR sinopsis LIKE ?";
//                    ps = c.prepareStatement(sql);
//                    ps.setString(1, "%" + txtSearch.getText() + "%");
//                    ps.setString(2, "%" + txtSearch.getText() + "%");
//                }    
//                ResultSet rs = ps.executeQuery();
//                while(rs.next()){
//                    int id = rs.getInt("id");
//                    String img = "src/Cover/" + rs.getString("gambar");
//                    String judul = rs.getString("judul");
//                    String tahun = rs.getString("tahun");
//
//                    BufferedImage bimg = null;
//                    try{
//                        bimg = ImageIO.read(new File(img));
//                    } catch(IOException e){
//                        System.out.println(e.getMessage());
//                    }
//                    Image image = bimg.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
//                    ImageIcon ii = new ImageIcon(image);
//
//                    btnImage.put(id, new JButton(judul + " (" + tahun + ")", ii){
//                        {
//                            setSize(150, 200);
//                            setMaximumSize(getSize());
//
//                        }
//                    });
//                    count++;
//                }
//            } catch(SQLException e){
//                System.out.println(e.getMessage());
//            }
//
//            if(count == 0){
//                SwingUtilities.updateComponentTreeUI(this);
//                JOptionPane.showMessageDialog(this, "Hasil pencarian tidak ditemukan");
//            } else {
//                show(btnImage);
//            }
//        }
//    }
//    
//    private void show(Map<Integer, JButton> btnImage){
//        SwingUtilities.updateComponentTreeUI(this);
//        for(int key : btnImage.keySet()){
//            btnImage.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
//            btnImage.get(key).setHorizontalTextPosition(SwingConstants.CENTER);
//            
//            jPanel2.add(btnImage.get(key));
//            
//            btnImage.get(key).addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    btnImageActionPerformed(evt, key);
//                }
//            });
//        }
//    }
    
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cbMetode = new javax.swing.JCheckBox();
        lblTahun = new javax.swing.JLabel();
        txtSearchTahun = new javax.swing.JTextField();
        lblGenre = new javax.swing.JLabel();
        cbxSearchGenre = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Database");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/brainware.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("User");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Search");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.setMaximumSize(new java.awt.Dimension(65, 23));
        btnLogin.setMinimumSize(new java.awt.Dimension(65, 23));
        btnLogin.setPreferredSize(new java.awt.Dimension(65, 23));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAdd.setText("Add Movie");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel4.setText("Search");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

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

        lblTahun.setText("Tahun");

        txtSearchTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchTahunActionPerformed(evt);
            }
        });

        lblGenre.setText("Genre");

        cbxSearchGenre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 627, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTahun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxSearchGenre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSearch)
                                    .addComponent(txtSearchTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch))
                            .addComponent(cbMetode))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMetode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTahun)
                    .addComponent(txtSearchTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenre)
                    .addComponent(cbxSearchGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnAdd))
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

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt, int id){
//        new Read().read(id, 2);
        this.dispose();
    }
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?", "Logout", JOptionPane.OK_CANCEL_OPTION);
        if(confirm == 0){
            Logout.logout();
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
//        search();
    }//GEN-LAST:event_btnSearchActionPerformed

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

    private void txtSearchTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchTahunActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new Home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSearch;
    private javax.swing.JCheckBox cbMetode;
    private javax.swing.JComboBox cbxSearchGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGenre;
    private javax.swing.JLabel lblTahun;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchTahun;
    // End of variables declaration//GEN-END:variables
}
