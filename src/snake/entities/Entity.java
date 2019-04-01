package snake.entities;

import java.awt.Point;

import snake.enums.EntityTypes;

public interface Entity{
    /**
     * This Handles the game logic on every update. <p>
     * The TableMap will iterate over this on every tick
     */
    public void tick();
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