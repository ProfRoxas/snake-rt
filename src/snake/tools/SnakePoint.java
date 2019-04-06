package snake.tools;

import java.awt.Point;

public class SnakePoint extends Point {
    private int worldX;
    private int worldY;

    public SnakePoint(int x, int y, int worldX, int worldY) {
        super(x, y);
        this.worldX = worldX;
        this.worldY = worldY;
    }
    
    @Override
    public void translate(int dx, int dy) {
        x = Math.floorMod( x + dx,  worldX);
        y = Math.floorMod( y + dy,  worldY);
    }
}
