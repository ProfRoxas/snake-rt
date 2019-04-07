package snake.tools;

import snake.entities.SnakeHead;
import snake.entities.Entity;
import snake.tools.TableMap;
import snake.enums.EntityTypes;

import snake.enums.Direction;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;


public class Logic{

    private SnakeHead snakeHead;
    private Direction nextDirection = Direction.UP;
    private boolean status = false;
    private TableMap map = new TableMap(10, 10, 0, 0);
    private boolean paused = true;

    public TableMap getMap(){
        return map;
    }

    public void update(){
        if (paused) return;
        Point headPos = snakeHead.getLocation();
        switch(nextDirection){
            case UP:
                headPos.translate(0, 1);
                break;
            case DOWN:
                headPos.translate(0, -1);
                break;
            case RIGHT:
                headPos.translate(1, 0);
                break;
            case LEFT:
                headPos.translate(-1, 0);
                break;
        }

        Entity tileEntity = map.get(headPos);
        if (tileEntity != null) {
            //implemented with switch for future use, its ugly, i get it
            switch(tileEntity.getType()){
                case BASICFRUIT:
                    snakeHead.move(nextDirection, true);
                    break;
                default:
                    gameOver();
            }
        }else{
            snakeHead.move(nextDirection, false);
        }
    }

    //TODO: implement game over
    public void gameOver(){
        System.out.println("Game over!");
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