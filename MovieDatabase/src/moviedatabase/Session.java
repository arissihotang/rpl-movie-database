/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;

/**
 *
 * @author Win8
 */
public class Session {
    private static boolean status = false;
    private static int id = 0;
    private static int role = 0;
    private static String username = null;

    public static boolean getStatus() {
        return status;
    }

    public static void setStatus(boolean s) {
        status = s;
    }

    public static int getId() {
        return id;
    }
    
    public static void setId(int i) {
        id = i;
    }

    public static int getRole() {
        return role;
    }

    public static void setRole(int r) {
        role = r;
    }
    
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String u) {
        username = u;
    }
}
