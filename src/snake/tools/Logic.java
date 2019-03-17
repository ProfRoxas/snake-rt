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

    public void generateSnakeHead(){
    	snakeHead = new SnakeHead(0,0,0,0);
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