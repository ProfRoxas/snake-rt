package snake.entities;

import java.awt.Point;

public class SnakeBody implements Entity {
    private Point position;
    private String visual;

    public SnakeBody(int x, int y, String visual) {
        position = new Point(x, y);
        this.visual = visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }

    public String getVisual() {
        return visual;
    }

    public Point getLocation() {
        return position;
    }

    @Override
    public String toString() {
        return visual;
    }

    public void tick() {}
}