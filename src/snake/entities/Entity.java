package snake.entities;

import java.awt.Point;

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
}