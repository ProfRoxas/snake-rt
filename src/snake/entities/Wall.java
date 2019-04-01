package snake.entities;

import java.awt.Point;

public class Wall implements Entity {
    private final Point __location;
    
    public Wall(Point p) {
        __location = p;
    }
    @Override
    public void tick() {

    }

    @Override
    public String toString() {
        return "Wall";
    }

    @Override
    public Point getLocation() {
        return __location;
    }
}