package snake.entities;

import java.awt.Point;

public class Fruit implements Entity {
    private  final FruitTypes __type;
    private final Point __location;

    public static enum FruitTypes {
        Basic
    }

    public Fruit(FruitTypes ft, Point p) {
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
}