package snake.tools;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import snake.entities.Entity;
import snake.entities.Fruit;
import snake.entities.SnakeBody;
import snake.entities.SnakeHead;
import snake.entities.Wall;
import snake.enums.EntityTypes;

/**
 * The TableMap stores the playfield of the game, each location hold either <code>null</code> or an <code>snake.entities.Entity</code> object. 
 * The Coordinates are wrapped around to assist in the snake going to the other side of the field.
 */
public class TableMap {
    private final int __x;
    private final int __y;
    private final Entity __field[][];
    private final Random __random;
    private int __size;

    /**
     * Create a new Entity field based on the parameters.
     * 
     * @param mx Maximum X, the width
     * @param my Maximum Y, the height
     * @param sh The SnakeHead to place
     * @param wc Wall Count, the initial count of walls
     * @param rs Random Seed, the seed for the random generator
     */
    public TableMap(int mx, int my, SnakeHead sh, int wc, long rs) {
        __random = new Random(rs);
        __x = mx;
        __y = my;
        __field = new Entity[__x][__y];

        place(sh);
        for (SnakeBody b : sh.getSnakeBody()) {
            place(b);
        }

        __size = sh.getSnakeBody().size()+1;

        for(int i = 0; i<wc; i++) {
            Point p = spawnEntity(EntityTypes.WALL);
            if(p==null) System.err.println("Failed to print Wall id "+p);
        }
    }

    public Point getSize(){
        return new Point(__x, __y);
    }
    
    /**
     * Take the Entity placed on the tile 
     * 
     * @param p The tile you want to check
     * 
     * @return either null or the Entity object currently on that tile
     */
    public Entity get(Point p){
        p = new Point(Math.floorMod(p.x, __x),Math.floorMod(p.y, __y));
        return __field[p.x][p.y];
    }

    /**
     * Place a new entity on the field, if possible
     * 
     * @param e the Entity you want to place
     * 
     * @return True if it's empty, or False, if it's occupied
     */
    public boolean place(Entity e) {
        Point p = e.getLocation();
        //checks for inside boundaries
        if(p.x!=(Math.floorMod(p.x, __x)) && p.y!=Math.floorMod(p.y, __y)) return false;
        if(__field[p.x][p.y] != null)
            return false;
        __field[p.x][p.y] = e;
        __size++;
        return true;
    }
    /**
     * Remove a tile from the map
     * 
     * @param p the tile you want it from
     * 
     * @return The entity that occupied the tile
     */
    public Entity remove(Point p) {
        p = new Point(Math.floorMod(p.x, __x),Math.floorMod(p.y, __y));
        Entity r = __field[p.x][p.y];
        __size--;
        __field[p.x][p.y] = null;
        return r;
    }
    /**
     * Returns how many free tiles are in the field when the free spaces are less than an optimal ammount for random
     */
    private List<Point> getFreeTiles() {
        List<Point> l = new LinkedList<>();
        Point p = new Point();
        for(int x = 0; x< __x; x++) {
            for(int y = 0; y< __y; y++) {
                p.move(x,y); //Saving on Object and GC usage
                if(get(p) == null)
                    l.add(p);
            }
        }

        return l;
    }
    /**
     * Spawn a new Entity on the map
     * 
     * @param et the Type of the Entity
     * 
     * @return Point on success, null if there are less than 5 tiles free or Unsupported EntityType
     */
    public Point spawnEntity(EntityTypes et) {
        /* A buffer to prevent pseudo infinite loop for the last free tiles */
        int lastBuffer = 5; int rx,ry;
        /* Special case on low element count */
        if(__size > ((__x*__y)-lastBuffer)) {
            List<Point> l = getFreeTiles();
            int n = __random.nextInt(l.size());
            return l.get(n);
        }
        /* Normal case */
        do {
            rx = __random.nextInt(__x);
            ry = __random.nextInt(__y);
        } while(__field[rx][ry] != null);
        Point p = new Point(rx,ry);
        Entity e;
        switch(et) {
            case WALL:
                e = new Wall(p);    
                break;
            case BASICFRUIT:
                e = new Fruit(et, p);
                break;
            case SPEEDUPFRUIT:
                e = new Fruit(et, p);
            default: e = null;
        }
        if(e == null) return null;
        this.place(e);
        return p;
    }
}