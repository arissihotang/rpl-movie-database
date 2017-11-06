/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *
 * @author Fabrian
 */
public class connect {
     public static Connection connectDB(){
        
        String path="jdbc:sqlite:E:/Semester 5/RPL/rpl-TA/movie_db.db";
        Connection con=null;
        try{
            con=DriverManager.getConnection(path);
        }
        catch(SQLException e){
            showMessageDialog(null,"Koneksi ke database gagal!","Error",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
