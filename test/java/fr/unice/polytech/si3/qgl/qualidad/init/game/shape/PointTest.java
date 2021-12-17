package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PointTest {
    @Test
    void equalsTest() {
        assertEquals(new Point(0, 0), new Point(0, 0));
        assertEquals(new Point(0, 0).hashCode(), new Point(0, 0).hashCode());
        assertNotEquals(new Point(1, 0), new Point(0, 1));
        assertNotEquals(new Point(1, 0).hashCode(), new Point(0, 1).hashCode());
    }

    @Test
    void getMiddlePointTest() {
        assertEquals(1, Point.getMiddlePoint(new Point(0, 0), new Point(2, 2)).getX(), 0);
        assertEquals(1, Point.getMiddlePoint(new Point(0, 0), new Point(2, 2)).getY(), 0);
        assertEquals(0.5, Point.getMiddlePoint(new Point(0, 0), new Point(1, 2)).getX(), 0);
        assertEquals(1, Point.getMiddlePoint(new Point(0, 0), new Point(1, 2)).getY(), 0);
        assertEquals(1, Point.getMiddlePoint(new Point(0, 0), new Point(2, 0)).getX(), 0);
        assertEquals(0, Point.getMiddlePoint(new Point(0, 0), new Point(2, 0)).getY(), 0);
    }

    @Test
    void getTest() {
        assertEquals(1, new Point(1, -1).getX(), 0);
        assertEquals(-1, new Point(1, -1).getY(), 0);
        assertEquals(1.414, new Point(1, -1).getNorm(), 0.001);
    }
}