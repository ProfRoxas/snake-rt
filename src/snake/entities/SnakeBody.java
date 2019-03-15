package snake.entities;

import java.awt.Point;

public class SnakeBody {
    private Point position;
    private String visual;

    public SnakeBody(int x, int y, String visual) {
        position = new Point(x, y);
        this.visual = visual;
    }

    @Override
    public String toString() {
        return visual;
    }
}