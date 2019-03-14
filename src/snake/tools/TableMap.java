package snake.tools;

import java.awt.Point;
import java.lang.annotation.Documented;
import java.util.Random;

import snake.entities.Entity;

public class TableMap {
    private final int __x;
    private final int __y;
    private final Entity __field[][];
    private final int __difficulty;

    /**
     * Create a new Entity field based on the parameters.
     * 
     * @param mx Maximum X, the width
     * @param my Maximum Y, the height
     * @param ss Snake Size, the initial size of the Snake
     * @param wc Wall Count, the initial count of walls
     * @param ds Difficulty Setting, determines entity spawn logic
     * @param rs Random Seed, the seed for the random generator
     */
    public TableMap(int mx, int my, int ss, int wc, int ds, long rs) {
        Random r = new Random(rs);
        __x = mx;
        __y = mx;
        __difficulty = ds;
        __field = new entity[x][y];
    }
    
    /**
     * Take the Entity placed on the tile 
     * 
     * @param p The tile you want to check
     * 
     * @return either null or the Entity object currently on that tile
     */
    public Entity get(Point p){
        return __field[p.x][p.y];
    }

    /**
     * Place a new entity on the field, if possible
     * 
     * @param p the tile you want to place it onto
     * 
     * @return True if it's empty, or False, if it's occupied
     */
    public boolean place(Point p, Entity e) {
        if(__field[p.x][p.y] != null)
            return false;
        __field[p.x][p.y] = e;
        return true;
    }
}