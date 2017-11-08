package movie_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Connect {
    private static Connection c = null;
    
    public static Connection connect(){
        if(c==null){
            try{
                String s = Path.getPath();
                c = DriverManager.getConnection(s);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Koneksi ke database gagal!");
            }
        }
        return c;
    }
}