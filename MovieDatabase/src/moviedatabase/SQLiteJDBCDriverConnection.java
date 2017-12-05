/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    private static Connection c = null;
    
    public static Connection connect(){
        if(c==null){
            try{
                String s = "jdbc:sqlite:src/MovieDatabase.db";
                c = DriverManager.getConnection(s);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Koneksi ke database gagal!");
            }
        }
        return c;
    }
}
