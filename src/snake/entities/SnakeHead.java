package snake.entities;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import snake.tools.SnakePoint;
import snake.enums.Direction;
import snake.enums.EntityTypes;
import snake.entities.SnakeBody;

public class SnakeHead implements Entity {
    private SnakePoint position;
    private Deque<SnakeBody> body;

    /**
     * Create a new Snake based on the parameters.
     * 
     * @param x Snake starting x position
     * @param y Snake starting y position
     * @param worldX World maximum width
     * @param worldY World maximum height
     * @param bodyLenght Snake starting body lenght 
     */
    public SnakeHead(int x, int y, int worldX, int worldY, int bodyLength) {
        position = new SnakePoint(x, y, worldX, worldY);
        body = new LinkedList<SnakeBody>();

        for(int i = 1; i < bodyLength-1; i++) {
            body.add(new SnakeBody(x-i, y, "Body"));
        }
        body.add(new SnakeBody(x-bodyLength, y, "Tale"));
    }

    public SnakeHead(int x, int y, int worldX, int worldY) {
        this(x, y, worldX, worldY, 3);
    }

    /**
     * Move Snake head to the given direction
     * 
     * @param dir Direction where you want to move the Snake
     */
	private void move(Direction dir) {
		if(dir.equals(Direction.valueOf("UP"))) {
			position.translate(-1, 0);
		}
		if(dir.equals(Direction.valueOf("DOWN"))) {
			position.translate(1, 0);
		}
		if(dir.equals(Direction.valueOf("RIGHT"))) {
			position.translate(0, 1);
		}
		if(dir.equals(Direction.valueOf("LEFT"))) {
			position.translate(0, -1);
		}
    }
    
    /**
     * Move Snake to the given direction and if the parameters right grow the Snake
     * 
     * @param dir Direction where you want to move the Snake
     * @param tile If Food, the Snake grow
     * 
     * @return An array: First object is the new Body Element, second one is the Deleted Body Element.
     */
    public SnakeBody[] move(Direction dir, String tile) {
        SnakeBody[] refresh = new SnakeBody[2];
        if(tile.equals("")) {
            body.addFirst(new SnakeBody(position.x, position.y, "Body"));
            refresh[0] = body.peekFirst();
            refresh[1] = body.peekLast();
            body.removeLast();
            body.peekLast().setVisual("Tale");
        }
        if(tile.equals("Food")) {
            body.addFirst(new SnakeBody(position.x, position.y, "FatBody"));
            refresh[0] = body.peekFirst();
            refresh[1] = null;
        }
        move(dir);
        return refresh;
    }

    /** Return the Snake body Entities for inicialization */
    public Deque<SnakeBody> getSnakeBody() {
        return body;
    }

    /** Return the Snake head Location */
    public Point getLocation() {
        return position.getLocation();
    }
    
    /** Return the Snake head String */
    @Override
    public String toString() {
           return "Head";
    }
    
    public void tick() {}
    
    public EntityTypes getType() {
        return EntityTypes.SNAKEHEAD;
    }
}