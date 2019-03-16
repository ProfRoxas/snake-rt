package snake.tools;

import snake.entities.SnakeHead;
import snake.enums.Direction;

public class Logic{

    private SnakeHead snakeHead;
    private Direction nextDirection = Direction.UP;
    private boolean status = false;
    //TODO: Visual component
    
    public Logic(/*Visual*/){
        //TODO: get visual component and save it
    }

    public void /*SnakeHead*/ generateSnakeHead(){
        //TODO: snakeHead = new SnakeHead(visual.x/2, visual.y/2, visual.x, visual.y);
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