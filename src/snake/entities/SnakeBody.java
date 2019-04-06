package snake.entities;

import java.awt.Point;

import snake.enums.EntityTypes;

public class SnakeBody implements Entity {
    private Point position;
    private String visual;

    /**
     * Create a new Snake based on the parameters.
     * 
     * @param x Body element starting x position
     * @param y Body element starting y position
     * @param visual The body element visual parameter in String 
     */
    public SnakeBody(int x, int y, String visual) {
        position = new Point(x, y);
        this.visual = visual;
    }

    /**
     * 
     * @param visual The body element visual parameter in String 
     */
    public void setVisual(String visual) {
        this.visual = visual;
    }
    
    /**
    * 
    * @return The body element visual parameter in String
    */
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
    
    @Override
    public EntityTypes getType() {
        return EntityTypes.SNAKEBODY;
    }
}