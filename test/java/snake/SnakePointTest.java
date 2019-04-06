package snake;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Point;
import snake.tools.SnakePoint;

public class SnakePointTest
{

    @Test
    public void BoundariesTest() {
        SnakePoint point = new SnakePoint(0, 0, 10, 10);

        Point testPoint1 = new Point(9, 0);
        Point testPoint2 = new Point(9, 9);
        Point testPoint3 = new Point(0, 9);
        Point testPoint4 = new Point(0, 0);

        point.translate(-1, 0);
        assertEquals("Left X boundary not work.", point.getLocation(), testPoint1);
        point.translate(0, -1);
        assertEquals("Down Y boundary not work.", point.getLocation(), testPoint2);
        point.translate(1, 0);
        assertEquals("Right X boundary not work.", point.getLocation(), testPoint3);
        point.translate(0, 1);
        assertEquals("Up Y boundary not work.", point.getLocation(), testPoint4);
    }
}