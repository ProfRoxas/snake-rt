package snake.tools;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

public class Settings {
    private static int __x;
    private static int __y;
    private static int __seed;
    private static int __walls;

    static {
        File f = new File("./settings.ini");
        if(!f.canRead()) {
            __x = 10;
            __y = 10;
            __seed = new Random().nextInt();
            __walls = 10;
        }else readSettings(f);
    }
    /**
     * Prevent making an instance of this Class
     */
    private Settings() {};

    /**
     * Set the Map size
     * @param p Desired size of the Map as Point
     */
    public static void setMapSize(Point p){setMapSize(p.x, p.y);}
    /**
     * Set the Map size
     * @param x Horizontal Size of the Map
     * @param y Vertical Size of the Map
     */
    public static void setMapSize(int x, int y) {
        __x = x;
        __y = y;
    }

    /**
     * Get the size of the Map
     * @return Map size
     */
    public static Point getMapSize() {
        return new Point(__x,__y);
    }

    /**
     * Set the seed for the Map's random generation
     * @param s
     */
    public static void setSeed(int s) {
        __seed = s;
    }

    /** 
     * Get the seed of the Map
     */
    public static int getSeed() {
        return __seed;
    }

    /**
     * Parses the settings file
     */
    private static void readSettings(File f) {
        Properties p = new Properties();
        try {p.load(new FileReader(f));}
        catch(Exception e) {
            System.err.println("Settings file couldn't be read");
            e.printStackTrace();
        }

        __x = Integer.parseInt(p.getProperty("Map_Width", "10"));
        __y = Integer.parseInt(p.getProperty("MapHeight", "10"));
        __seed = Integer.parseInt(p.getProperty("MapSeed", "10"));
    }
}