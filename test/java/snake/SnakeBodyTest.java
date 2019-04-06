package snake;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import snake.entities.SnakeBody;
import snake.enums.EntityTypes;

/**
 * Unit test for SnakeBody.
 */
public class SnakeBodyTest
{
    private SnakeBody singleBody;

    private void setup() {
        singleBody = new SnakeBody(1, 4, EntityTypes.SNAKEBODY);
    }

    /**
     * Tests Location inicialization
     */
    @Test
    public void LocationCheck() {
        setup();
        Point test = new Point(1, 4);
        assertEquals("Different point location returned.", test, singleBody.getLocation());
    }

    /**
     * Tests Etnity inicialization and setType
     */
    @Test
    public void EtnityAndSetEtnityTypeCheck() {
        setup();
        assertEquals("Different Etnity type returned.", EntityTypes.SNAKEBODY, singleBody.getType());

        singleBody.setType(EntityTypes.FATSNAKEBODY);
        assertEquals("Different Etnity type returned.", EntityTypes.FATSNAKEBODY, singleBody.getType());
    }
}