package snake.entities;

import java.awt.Point;

import snake.enums.EntityTypes;

public class Fruit implements Entity {
    private  final EntityTypes __type;
    private final Point __location;

    public Fruit(EntityTypes ft, Point p) {
        __type = ft;
        __location = p;
    }

    @Override
    public void tick() {

    }

    @Override
    public String toString() {
        return __type.toString();
    }

    @Override
    public Point getLocation() {
        return __location;
    }

    @Override
    public EntityTypes getType() {
        return __type;
    }
}