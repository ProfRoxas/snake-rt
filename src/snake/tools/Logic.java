package snake.tools;

import snake.entities.SnakeHead;
import snake.enums.Direction;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Logic{

    private SnakeHead snakeHead;
    private Direction nextDirection = Direction.UP;
    private boolean status = false;
    private JFrame visual;
    
    public Logic(JFrame visual){
        this.visual = visual;
    }

    public void update(){
        //TODO:
        //utkozes detektalas
        //snakeHead.move(nextDirection);
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

    public void setNextDirection(int __code){        
        switch(__code){
            case KeyEvent.VK_LEFT:
                setNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                setNextDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                setNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                setNextDirection(Direction.DOWN);
                break;
        }
    }

    public void setNextDirection(Direction dir){
        nextDirection = dir;
    }

}