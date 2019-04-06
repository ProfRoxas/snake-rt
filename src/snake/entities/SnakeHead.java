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
     * @param bodyLength Snake starting body length 
     */
    public SnakeHead(int x, int y, int worldX, int worldY, int bodyLength) {
        position = new SnakePoint(x, y, worldX, worldY);
        body = new LinkedList<SnakeBody>();

        for(int i = 1; i < bodyLength-1; i++) {
            body.add(new SnakeBody(x-i, y, EntityTypes.SNAKEBODY));
        }
        body.add(new SnakeBody(x-bodyLength, y, EntityTypes.SNAKETAIL));
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
     * @param tile If Food, the Snake grow, if not the second Body Element is null
     * 
     * @return An array: First object is the new Body Element, second one is the Deleted Body Element.
     */
    public SnakeBody[] move(Direction dir, String tile) {
        SnakeBody[] refresh = new SnakeBody[2];
        if(tile.equals("")) {
            body.addFirst(new SnakeBody(position.x, position.y, EntityTypes.SNAKEBODY));
            refresh[0] = body.peekFirst();
            refresh[1] = body.peekLast();
            body.removeLast();
            body.peekLast().setVisual(EntityTypes.SNAKETAIL);
        }
        if(tile.equals("Food")) {
            body.addFirst(new SnakeBody(position.x, position.y, EntityTypes.FATSNAKEBODY));
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
    
    public void tick() {}
    
    public EntityTypes getType() {
        return EntityTypes.SNAKEHEAD;
    }
}