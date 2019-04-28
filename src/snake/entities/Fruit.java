package snake.entities;

import java.awt.Point;
import java.security.InvalidParameterException;

import snake.enums.EntityTypes;

/**
 * Fruit class represent the not harmful object the snake can eat
 */
public class Fruit implements Entity {
    private  final EntityTypes __type;
    private final Point __location;

    /**
     * Create a Fruit with a specified type and location
     * <p>
     * Possible types:
     * <ul>
     * <li>BasicFruit</li>
     * </ul>
     * <p>
     * Any other type will throw an exception
     * @param ft Type of the Entity
     * @param p Location of the Fruit
     * @throws InvalidParameterException When the Type is not valid
     */
    public Fruit(EntityTypes ft, Point p) {
        if(ft != EntityTypes.BASICFRUIT)
            throw new InvalidParameterException("Fruit type is not valid");
        __type = ft;
        __location = p;
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