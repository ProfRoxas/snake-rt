package snake.entities;

import java.awt.Point;

import snake.enums.EntityTypes;

public interface Entity{
    /**
     * Get the location of the Entity
     * 
     * @return Point
     */
    public Point getLocation();
    /**
     * Get the type of this Entity
     * 
     * @return EntityType
     */
    public EntityTypes getType();
}