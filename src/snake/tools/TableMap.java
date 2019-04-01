package snake.tools;

import java.awt.Point;
import java.lang.annotation.Documented;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import snake.entities.Entity;
import snake.entities.Fruit;
import snake.entities.Wall;
import snake.enums.EntityTypes;

public class TableMap {
    private final int __x;
    private final int __y;
    private final Entity __field[][];
    private final Random __random;
    /** The objects the map holds, for quicker tick() iterating */
    private List<Entity> __container;

    /**
     * Create a new Entity field based on the parameters.
     * 
     * @param mx Maximum X, the width
     * @param my Maximum Y, the height
     * @param wc Wall Count, the initial count of walls
     * @param rs Random Seed, the seed for the random generator
     */
    public TableMap(int mx, int my, int wc, long rs) {
        __random = new Random(rs);
        __x = mx;
        __y = my;
        __field = new Entity[__x][__y];

        __container = new LinkedList<>();

        for(int i = 0; i<wc; i++) {
            Point p = spawnEntity(EntityTypes.WALL);
            if(p==null) System.err.println("Failed to print Wall id "+p);
        }
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
     * @param e the Entity you want to place
     * 
     * @return True if it's empty, or False, if it's occupied
     */
    public boolean place(Entity e) {
        Point p = e.getLocation();
        if(__field[p.x][p.y] != null)
            return false;
        __field[p.x][p.y] = e;
        /* [TODO] Put snake parts in the head for quicker remove */
        __container.add(e);
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
        Entity r = __field[p.x][p.y];
        __container.remove(r);
        __field[p.x][p.y] = null;
        return r;
    }
    /**
     * Calls the Tick function on every Entity of the map for game logic update
     */
    public void tick() {
        __container.forEach(entity->entity.tick());
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
        if(__container.size() > ((__x*__y)-lastBuffer)) return null;
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
            default: e = null;
        }
        if(e == null) return null;
        this.place(p, e);
        return p;
    }
}