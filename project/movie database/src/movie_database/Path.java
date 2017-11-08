package movie_database;

public class Path {
    private static String path = "jdbc:sqlite:src/moviedatabase.db";
    private static String pathCover = "src/Cover/";
    
    

    public static String getPath() {
        return path;
    }

    public static String getPathCover() {
        return pathCover;
    }
}