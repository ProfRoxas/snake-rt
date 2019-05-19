package snake.tools;

import java.awt.Dimension;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;

/**
 * The Settings static class handles the global settings, like new map settings, window configuration etc
 * <p>
 * The class preserves the settings in a .ini file
 */
public class Settings {
    //TODO: Remake with Property Object
    private static int __x = 10;
    private static int __y = 10;
    private static int __seed = new Random().nextInt();
    private static int __walls = 3;
    private static int __h = 480;
    private static int __w = 640;
    private static String __name = "Player1";
    private static int __speed = 5;
    /**
     * Loads the settings.ini at initialization, if exists, otherwise use the initial parameters
     */
    static {
        try {
            InputStream f = new FileInputStream("./settings.ini");
            if(f!=null)
                readSettings(f);
                //internal variables have their values already assigned, so only the new ones from the ini gets overriden
        }catch(Exception e){
            System.out.println("Error loading settings.ini");
            e.printStackTrace();
        }
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
        saveSetting();
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
        saveSetting();
    }

    /** 
     * Get the seed of the Map
     */
    public static int getSeed() {
        return __seed;
    }

    /**
     * Set the speed of logic update time
     * @param s
     */
    public static void setSpeed(int s) {
        __speed = s;
        saveSetting();
    }

    /** 
     * Get the speed of logic update time
     */
    public static int getSpeed() {
        return __speed;
    }

    /**
     * Set the name of the player for high score
     * @param s
     */

    public static void setName(String n) {
        __name = n;
        saveSetting();
    }

    /** 
     * Get the name of the player for high score
     */
    public static String getName() {
        return __name;
    }

    /**
     * Set the number of walls
     * @param wc Wall count
     */
    public static void setWalls(int wc) {
        __walls = wc;
        saveSetting();
    }

    /**
     * Get the number of walls
     * @return Number of walls
     */
    public static int getWalls() {
        return __walls;
    }
    /**
     * Set the Window resolution
     * @param d Dimension of the screen
     */
    public static void setScreen(Dimension d) {
        //not the best but works
        __h = (int)(d.getHeight());
        __w = (int)(d.getWidth());
        saveSetting();
    }

    /**
     * Get the Window size
     * @return Window size
     */
    public static Dimension getScreen() {
        return new Dimension(__w, __h);
    }

    /**
     * Parses the settings file
     */
    private static void readSettings(InputStream f) {
        Properties p = new Properties();
        try {p.load(f);}
        catch(Exception e) {
            System.err.println("Settings file couldn't be read");
            e.printStackTrace();
        }
        //TODO: Add more possible settings parameter
        __x = Integer.parseInt(p.getProperty("Map_Width", "10"));
        __y = Integer.parseInt(p.getProperty("Map_Height", "10"));
        __walls = Integer.parseInt(p.getProperty("Wall_Count", "3"));
        __name = p.getProperty("User_Name", "Player1");
    }
    private static void saveSetting() {
        Properties p = new Properties();
        
        try(OutputStream os = new FileOutputStream("./settings.ini")) {
            p.setProperty("Map_Width", __x+"");
            p.setProperty("Map_Height", __y+"");
            p.setProperty("User_Name", __name);
            p.setProperty("Wall_Count", __walls+"");

            p.save(os, "Snake Settings File");
            System.out.println("Saving settings");
        }catch (IOException e) {
            //?shouldnt happen
            e.printStackTrace();
        }
    }
}