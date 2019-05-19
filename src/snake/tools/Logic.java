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
import java.util.Random;


public class Logic{

    private SnakeHead snakeHead;
    private Direction nextDirection = Direction.LEFT;
    private Direction lastDirection = Direction.LEFT;
    private boolean status = false;
    private TableMap map;
    private boolean paused = true;
    private boolean exit = false;
    private double speed;
    private int score;
    private Random rand = new Random();

    public Logic() {
        Point pm = Settings.getMapSize();
        snakeHead = new SnakeHead((int)Settings.getMapSize().getX()/2-2, (int)Settings.getMapSize().getY()/2-1, pm.x, pm.y);
        map = new TableMap(pm.x, pm.y, snakeHead, Settings.getWalls(), Settings.getSeed());
        speed = 1.0;
        score = 0;
        map.spawnEntity(EntityTypes.BASICFRUIT);
    }

    public TableMap getMap(){
        return map;
    }

    public double update(){
        if(exit) {
            gameOver();
            return -1;
        }
        if (paused ) return speed;
        Point headPos = snakeHead.getLocation();
        map.remove(headPos);
        if (getOppositeDirection(nextDirection) == lastDirection) {
        	nextDirection = lastDirection;
        }
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
        boolean pickedUpFruit = false;
        if (tileEntity != null) {
            switch(tileEntity.getType()){
                case SPEEDUPFRUIT:
                    speedUpAndDown(0.2);
                case BASICFRUIT:
                	map.remove(headPos);
                	pickedUpFruit = true;
                    body = snakeHead.move(nextDirection, true);
                    score += Settings.getSpeed();
                    break;
                default:
                    gameOver();
                    return -1;
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
        	if (pickedUpFruit) {
	            if(rand.nextInt(100)<15){
	                map.spawnEntity(EntityTypes.SPEEDUPFRUIT); 
	            }else{
	                map.spawnEntity(EntityTypes.BASICFRUIT);
	            }	
        	}
        }
        return speed;
    }

    public double getScore(){
        return score;
    }

    public void setPaused(boolean toSet){
    	paused = toSet;
    }

    public void setExit(boolean toSet) {
        exit = toSet;
    }

    public void gameOver(){
    	map.place(snakeHead);
    	HighScores.addNewHighScore(new PersonScore(Settings.getName(), score));
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

    public void speedUp(double add){
        speed += add;
    }

    private void speedUpAndDown(double add){
        speed += add;
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    speedDown(add);
                }
            }, 
            5000 
        );
    }

    public void speedDown(double add){
        speed -= add;
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