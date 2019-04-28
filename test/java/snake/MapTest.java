package snake;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import snake.entities.Entity;
import snake.entities.SnakeHead;
import snake.entities.Wall;
import snake.tools.TableMap;

/**
 * Unit test for simple App.
 */
public class MapTest 
{
    private TableMap tm;
    private void setup() {
        tm = new TableMap(5, 6, new SnakeHead(0,1,5,6), 4, 0);
    }
    /**
     * Tests Spawn and if there are 4 walls witht he seed
     */
    @Test
    public void Deterministic5x6Map4Wall(){
        setup();
        Point w1 = new Point(0,4);
        Point w2 = new Point(0,5);
        Point w3 = new Point(1,3);
        Point w4 = new Point(4,5);

        assertNotEquals("Wall is not at it's place", null, tm.get(w1));
        assertNotEquals("Wall is not at it's place", null, tm.get(w2));
        assertNotEquals("Wall is not at it's place", null, tm.get(w3));
        assertNotEquals("Wall is not at it's place", null, tm.get(w4));
    }
    /**
     * Tests if the entity is inserted
     */
    @Test
    public void EntityPlaceAndRemove() {
        setup();
        Point p = new Point(2,3);
        Entity e = tm.get(p);
        if (e != null) throw new AssertionError("The tile is already occupied?");
        e = new Wall(p);
        assertTrue("Failed to place Entity on empty place", tm.place(e));
        assertFalse("Placing the same Entity didn't give false", tm.place(e));
        assertEquals("Different entity has been returned", e, tm.get(p));
        assertEquals("Removing didn't give back the original Entity", e, tm.remove(p));
        assertEquals("Removing didn't place null in it's place", null, tm.get(p));
    }
    /**
     * Tests if the map can wrap around
     * Test map is 5x6, starting from 0, so possible values for
     * x is 0 -> 4
     * y is 0 -> 5
     * 
     */
    @Test
    public void WrappedBoundaries() {
        setup();
        Point p = new Point(6,6);
        Point p2 = new Point(-4, -6);
        Point pe = new Point(1,0);
        try {
            tm.get(p);
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertFalse("Cant access out of positive bounds", true);
        }
        try {
            tm.get(p2);
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertFalse("Cant access out of negative bounds", true);
        }
        // 6 mod X(5) = 1
        // 6 mod Y(6) = 0
        //-4 mod X(5) = 1
        //-6 mod Y(6) = 0
        Boolean b = null;
        if(tm.get(p) == null)
            b = tm.place(new Wall(pe));
        Entity e = tm.get(p);
        p = e.getLocation();
        Entity e2 = tm.get(p2);
        p2 = e2.getLocation();
        assertEquals("Found different entity outside of positive boudaries", tm.get(p).getLocation(), pe);
        assertEquals("Found different entity outside of negative boudaries", tm.get(p2).getLocation(), pe);
        
    }
}
