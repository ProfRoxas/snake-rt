package snake.entities;

import java.awt.Point;

import snake.enums.EntityTypes;

/**
 * The Wall class represent the not moving harmful object the snake will die when it touches them
 */
public class Wall implements Entity {
    private final Point __location;
    
    /**
     * Create a Wall at the specified Location
     * @param p Location of the Wall
     */
    public Wall(Point p) {
        __location = p;
    }

    @Override
    public String toString() {
        return "WALL";
    }

    @Override
    public Point getLocation() {
        return __location;
    }
    
    @Override
    public EntityTypes getType() {
        return EntityTypes.WALL;
    }
}