package movie_database;

import javax.swing.JOptionPane;

public class Logout {
    public static void logout(){
        Session.setId(0);
        Session.setUsername(null);
        Session.setRole(0);
        Session.setStatus(false);
        JOptionPane.showMessageDialog(null, "Anda berhasil Logout");
        new Home().setVisible(true);
    }
}