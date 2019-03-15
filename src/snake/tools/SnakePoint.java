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
        x = (x + dx) % worldX;
        y = (y + dy) % worldY;
    }
}
