package snake;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Deque;

import org.junit.Test;

import snake.enums.Direction;
import snake.entities.SnakeHead;
import snake.entities.SnakeBody;

public class SnakeHeadTest
{
    private SnakeHead snake;

    private void setup() {
        snake = new SnakeHead(5, 5, 10, 10, 3);
    } 

    /**
     * Test Snake Setup and getSnakeBody function;
     */
    @Test
    public void SnakeSetupTest() {
        setup();

        Deque<SnakeBody> bodyList = snake.getSnakeBody();
        Point point1 = new Point(6, 5);
        Point point2 = new Point(7, 5);
        Point point3 = new Point(8, 5);

        assertEquals("Snake body length is not good", 3, bodyList.size());
        assertEquals("First body element is not in the right position", point1, bodyList.removeFirst().getLocation());
        assertEquals("Second body element is not in the right position", point2, bodyList.removeFirst().getLocation());
        assertEquals("Third body element is not in the right position", point3, bodyList.removeFirst().getLocation());
    }

    /**
     * Test the entire Move function and getLocation
     */
    @Test
    public void MoveTest() {
        setup();
        
        snake.move(Direction.LEFT, false);
        Point point1 = new Point(4 , 5);
        Point point2 = new Point(3 , 4);
        Point point3 = new Point(2 , 5);
        Point point4 = new Point(3 , 6);
        Point tailPoint1 = new Point(8, 5);
        Point tailPoint2 = new Point(7, 5);

        assertEquals("New location is not good", point1, snake.getLocation());

        Deque<SnakeBody> bodyList = snake.getSnakeBody();
        assertEquals("Body size is not right after move", 3, bodyList.size());

        
        assertEquals("Body size is not right after move", 3, bodyList.size());
        assertNotEquals("Third body element is not in the right position after move", tailPoint1, bodyList.peekLast().getLocation());

        snake.move(Direction.LEFT, true);
        assertEquals("Body size is not right after food move", 4, bodyList.size());
        assertEquals("Fourth body element is not in the right position after food move", tailPoint2, bodyList.peekLast().getLocation());

        snake.move(Direction.UP, false);
        assertEquals("Head location not good after move up", point2, snake.getLocation());

        snake.move(Direction.LEFT, false);
        snake.move(Direction.DOWN, false);
        assertEquals("Head location not good after move up", point3, snake.getLocation());

        snake.move(Direction.DOWN, false);
        snake.move(Direction.RIGHT, false);
        assertEquals("Head location not good after move up", point4, snake.getLocation());
    }
}