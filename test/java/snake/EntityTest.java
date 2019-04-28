package snake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import snake.entities.Entity;
import snake.entities.Fruit;
import snake.entities.Wall;
import snake.enums.EntityTypes;

/**
 * Unit test for Entities
 */
public class EntityTest 
{
    private Point p = new Point(1,2);
    /**
     * Tests proper Wall type and toString() handling
     */
    @Test
    public void WallType() {
        Entity e = new Wall(p);
        assertEquals("Wall type string is different","WALL", e.toString());
        assertEquals("Wall type value is different", e.getType(), EntityTypes.WALL);
        assertEquals("Wall location is not the same as specified", p, e.getLocation());
    }

    /**
     * Tests proper Fruit type and toString() handling
     */
    @Test
    public void FruitType() {
        Entity e = new Fruit(EntityTypes.BASICFRUIT, p);
        assertEquals("Wall type string is different","BASICFRUIT", e.toString());
        assertEquals("Wall type value is different", EntityTypes.BASICFRUIT, e.getType());
        assertEquals("Wall location is not the same as specified", p, e.getLocation());
    }

    @Rule
    public ExpectedException ee = ExpectedException.none();
    /**
     * Tests proper Wall type and toString() handling
     */
    @Test
    public void WrongFruit() {
        ee.expect(InvalidParameterException.class);
        ee.expectMessage("Fruit type is not valid");
        
        new Fruit(EntityTypes.FATSNAKEBODY, new Point());
        new Fruit(EntityTypes.SNAKEBODY, new Point());
        new Fruit(EntityTypes.SNAKEHEAD, new Point());
        new Fruit(EntityTypes.SNAKETAIL, new Point());
        new Fruit(EntityTypes.WALL, new Point());

    }
}