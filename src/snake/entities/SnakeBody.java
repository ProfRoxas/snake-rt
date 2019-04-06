package snake.entities;

import java.awt.Point;

import snake.enums.EntityTypes;

public class SnakeBody implements Entity {
    private Point position;
    private EntityTypes visual;

    /**
     * Create a new Snake based on the parameters.
     * 
     * @param x Body element starting x position
     * @param y Body element starting y position
     * @param visual The body element visual parameter in String 
     */
    public SnakeBody(int x, int y, EntityTypes visual) {
        position = new Point(x, y);
        this.visual = visual;
    }

    /**
     * Override the previus EntityTypes enum with the parameter
     * 
     * @param visual The body element visual parameter in EntityTypes enum 
     */
    public void setType(EntityTypes visual) {
        this.visual = visual;
    }

    public Point getLocation() {
        return position;
    }

    public void tick() {}
    
    /** Get the actual EntityTypes */
    public EntityTypes getType() {
        return visual;
    }
}