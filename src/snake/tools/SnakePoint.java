package snake.tools;

import java.awt.Point;

public class SnakePoint extends Point {
    private int worldX;
    private int worldY;

    /**
     * Create a new special Point for the Snake based on parameters.
     * 
     * @param x x position
     * @param y y position
     * @param worldX World maximum width
     * @param worldY World maximum height
     */
    public SnakePoint(int x, int y, int worldX, int worldY) {
        super(x, y);
        this.worldX = worldX;
        this.worldY = worldY;
    }
    
    /**
     * Overrided move function. Guarantee x and y always less than worldX and worldY.
     */
    @Override
    public void translate(int dx, int dy) {
        x = Math.floorMod( x + dx,  worldX);
        y = Math.floorMod( y + dy,  worldY);
    }
}
