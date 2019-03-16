package snake.entities;

public interface Entity{
    /**
     * This Handles the game logic on every update. <p>
     * The TableMap will iterate over this on every tick
     */
    public void tick();
    /**
     * The function that handles collision.
     * 
     * @param e The Entity it collides with
     */
    public void collidesWith(Entity e);
}