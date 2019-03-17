package snake.tools;

import snake.entities.SnakeHead;
import snake.enums.Direction;
import javax.swing.JFrame;
import java.awt.*;

public class Logic{

    private SnakeHead snakeHead;
    private Direction nextDirection = Direction.UP;
    private boolean status = false;
    private JFrame visual;
    
    public Logic(JFrame visual){
        this.visual = visual;
    }

    public SnakeHead generateSnakeHead(){
    	Dimension size = visual.getContentPane().getSize();
    	snakeHead = new SnakeHead(size.getWidth()/2, size.getHeight()/2, size.getWidth(), size.getWidth());
    }

    public SnakeHead getSnakeHead(){
        return snakeHead;
    }

    public Direction getNextDirection(){
        return nextDirection;
    }

    public void setNextDirection(Direction dir){
        nextDirection = dir;
    }

}