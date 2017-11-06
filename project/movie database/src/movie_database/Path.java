/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.database;

/**
 *
 * @author Win8
 */
public class Path {
    private static String path = "jdbc:sqlite:E:/Semester 5/RPL/rpl-TA/movie_db.db";
    private static String pathCover = "src/Cover/";

    /**
     * @return the pathDB
     */
    public static String getPath() {
        return path;
    }

    /**
     * @return the gambarPath
     */
    public static String getPathCover() {
        return pathCover;
    }
    
}
