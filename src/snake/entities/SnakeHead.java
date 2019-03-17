package snake.entities;

import java.util.Deque;
import java.util.LinkedList;
import snake.tools.SnakePoint;
import snake.enums.Direction;
import snake.entities.SnakeBody;

public class SnakeHead implements Entity {
    private SnakePoint position;
    private Deque body;

    public SnakeHead(int x, int y, int worldX, int worldY, int bodyLength) {
        position = new SnakePoint(x, y, worldX, worldY);
        body = new LinkedList<SnakeBody>();

        for(int i = 1; i < bodyLength; i++) {
            body.add(new SnakeBody(x-i, y, "body"));
        }
    }

    public SnakeHead(int x, int y, int worldX, int worldY) {
        this(x, y, worldX, worldY, 3);
    }

	public void move(Direction dir) {
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

    public void tick() {

    }
    
    @Override
    public String toString() {
           return "Head";
    }
}