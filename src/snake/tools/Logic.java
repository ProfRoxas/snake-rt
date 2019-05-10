package snake.tools;

import snake.entities.SnakeHead;
import snake.entities.Entity;
import snake.tools.TableMap;
import snake.enums.EntityTypes;
import snake.entities.SnakeBody;

import snake.enums.Direction;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;


public class Logic{

    private SnakeHead snakeHead;
    private Direction nextDirection = Direction.UP;
    private Direction lastDirection = Direction.UP;
    private boolean status = false;
    private TableMap map;
    private boolean paused = true;

    public Logic() {
        
        Point pm = Settings.getMapSize();
        snakeHead = new SnakeHead(0, 0, pm.x, pm.y);
        map = new TableMap(pm.x, pm.y, snakeHead, 3, 3);
    }

    public TableMap getMap(){
        return map;
    }

    public void update(){
        if (paused || getOppositeDirection(nextDirection) == lastDirection) return;
        Point headPos = snakeHead.getLocation();
        map.remove(headPos);
        switch(nextDirection){
            case UP:
                headPos.translate(0, -1);
                break;
            case DOWN:
                headPos.translate(0, 1);
                break;
            case RIGHT:
                headPos.translate(1, 0);
                break;
            case LEFT:
                headPos.translate(-1, 0);
                break;
        }
        lastDirection = nextDirection;

        Entity tileEntity = map.get(headPos);
        SnakeBody[] body = null;
        if (tileEntity != null) {
            //implemented with switch for future use, its ugly, i get it
            switch(tileEntity.getType()){
                case BASICFRUIT:
                    body = snakeHead.move(nextDirection, true);
                    break;
                default:
                    gameOver();
            }
        }else{
            body = snakeHead.move(nextDirection, false);
        }
        if (!paused) {
        	map.place(snakeHead);
        	map.place(body[0]);
        	if (body[1] != null) {
        		map.remove(body[1].getLocation());	
        	}
        }
    }

    public void setPaused(boolean toSet){
    	paused = toSet;
    }


    //TODO: implement game over
    public void gameOver(){
    	paused = true;
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

    private Direction getOppositeDirection(Direction dir){
    	switch(dir){
    		case UP:
    			return Direction.DOWN;
    		case DOWN:
    			return Direction.UP;
    		case LEFT:
    			return Direction.RIGHT;
    		case RIGHT:
    			return Direction.LEFT;
    		default:
    			return null;
    	}
    }

}