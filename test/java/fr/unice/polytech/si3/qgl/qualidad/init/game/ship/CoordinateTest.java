package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinateTest {
    @Test
    void constructorOneCoordinate() {
        assertEquals(new Coordinate(), new Coordinate());
        assertEquals(new Coordinate().hashCode(), new Coordinate().hashCode());
        assertEquals(new Coordinate(), new Coordinate(0, 0));
        assertEquals(new Coordinate(new Coordinate()), new Coordinate());
        assertEquals(new Coordinate(new Coordinate(0, 0)), new Coordinate());

        assertNotEquals(new Coordinate(), null);
        assertNotEquals(new Coordinate(1, 0), new Coordinate(0, 1));
        assertNotEquals(new Coordinate(1, 0).hashCode(), new Coordinate(0, 1).hashCode());
    }

    @Test
    void constructorMultipleCoordinates() {
        assertEquals(new Coordinate(2, 2), new Coordinate(new Coordinate(1, 1), new Coordinate(1, 1)));
        assertEquals(new Coordinate(2, 2), new Coordinate(new Coordinate(2, 0), new Coordinate(0, 2)));
        assertEquals(new Coordinate(2, 2), new Coordinate(new Coordinate(3, -1), new Coordinate(-1, 3)));

        assertNotEquals(new Coordinate(2, 2), new Coordinate(new Coordinate(2, 0), new Coordinate(0, -2)));
        assertNotEquals(new Coordinate(2, 2), new Coordinate(new Coordinate(-2, 0), new Coordinate(0, 2)));
        assertNotEquals(new Coordinate(2, 2), new Coordinate(new Coordinate(-2, 0), new Coordinate(0, -2)));
    }

    @Test
    void plusOneCoordinate() {
        assertEquals(new Coordinate(2, 2), new Coordinate(1, 1).plus(new Coordinate(1, 1)));
        assertEquals(new Coordinate(2, 2), new Coordinate(2, 0).plus(new Coordinate(0, 2)));
        assertEquals(new Coordinate(2, 2), new Coordinate(3, -1).plus(new Coordinate(-1, 3)));

        assertNotEquals(new Coordinate(2, 2), new Coordinate(2, 0).plus(new Coordinate(0, -2)));
        assertNotEquals(new Coordinate(2, 2), new Coordinate(-2, 0).plus(new Coordinate(0, 2)));
        assertNotEquals(new Coordinate(2, 2), new Coordinate(-2, 0).plus(new Coordinate(0, -2)));
    }

    @Test
    void plusMultipleCoordinates() {
        assertEquals(new Coordinate(3, 3), new Coordinate(1, 1).plus(new Coordinate(1, 1), new Coordinate(1, 1)));
        assertEquals(new Coordinate(3, 3), new Coordinate(2, 0).plus(new Coordinate(0, 2), new Coordinate(1, 1)));
        assertEquals(new Coordinate(3, 3), new Coordinate(3, -1).plus(new Coordinate(-1, 3), new Coordinate(1, 1)));

        assertNotEquals(new Coordinate(3, 3), new Coordinate(2, 0).plus(new Coordinate(0, -2), new Coordinate(1, 1)));
        assertNotEquals(new Coordinate(3, 3), new Coordinate(-2, 0).plus(new Coordinate(0, 2), new Coordinate(1, 1)));
        assertNotEquals(new Coordinate(3, 3), new Coordinate(-2, 0).plus(new Coordinate(0, -2), new Coordinate(1, 1)));
    }

    @Test
    void plusWithXAndY() {
        assertEquals(new Coordinate(2, 2), new Coordinate(1, 1).plus(1, 1));
        assertEquals(new Coordinate(2, 2), new Coordinate(2, 0).plus(0, 2));
        assertEquals(new Coordinate(2, 2), new Coordinate(3, -1).plus(-1, 3));

        assertNotEquals(new Coordinate(2, 2), new Coordinate(2, 0).plus(0, -2));
        assertNotEquals(new Coordinate(2, 2), new Coordinate(-2, 0).plus(0, 2));
        assertNotEquals(new Coordinate(2, 2), new Coordinate(-2, 0).plus(0, -2));
    }

    @Test
    void minusOneCoordinate() {
        assertEquals(new Coordinate(), new Coordinate(1, 1).minus(new Coordinate(1, 1)));
        assertEquals(new Coordinate(2, -2), new Coordinate(2, 0).minus(new Coordinate(0, 2)));
        assertEquals(new Coordinate(4, -4), new Coordinate(3, -1).minus(new Coordinate(-1, 3)));

        assertNotEquals(new Coordinate(2, -2), new Coordinate(2, 0).minus(new Coordinate(0, -2)));
        assertNotEquals(new Coordinate(2, -2), new Coordinate(-2, 0).minus(new Coordinate(0, 2)));
        assertNotEquals(new Coordinate(2, -2), new Coordinate(-2, 0).minus(new Coordinate(0, -2)));
    }

    @Test
    void minusMultipleCoordinates() {
        assertEquals(new Coordinate(-1, -1), new Coordinate(1, 1).minus(new Coordinate(1, 1), new Coordinate(1, 1)));
        assertEquals(new Coordinate(1, -3), new Coordinate(2, 0).minus(new Coordinate(0, 2), new Coordinate(1, 1)));
        assertEquals(new Coordinate(3, -5), new Coordinate(3, -1).minus(new Coordinate(-1, 3), new Coordinate(1, 1)));

        assertNotEquals(new Coordinate(1, -3), new Coordinate(2, 0).minus(new Coordinate(0, -2), new Coordinate(1, 1)));
        assertNotEquals(new Coordinate(1, -3), new Coordinate(-2, 0).minus(new Coordinate(0, 2), new Coordinate(1, 1)));
        assertNotEquals(new Coordinate(1, -3), new Coordinate(-2, 0).minus(new Coordinate(0, -2), new Coordinate(1, 1)));
    }

    @Test
    void minusWithXAndY() {
        assertEquals(new Coordinate(), new Coordinate(1, 1).minus(1, 1));
        assertEquals(new Coordinate(2, -2), new Coordinate(2, 0).minus(0, 2));
        assertEquals(new Coordinate(4, -4), new Coordinate(3, -1).minus(-1, 3));

        assertNotEquals(new Coordinate(2, -2), new Coordinate(2, 0).minus(0, -2));
        assertNotEquals(new Coordinate(2, -2), new Coordinate(-2, 0).minus(0, 2));
        assertNotEquals(new Coordinate(2, -2), new Coordinate(-2, 0).minus(0, -2));
    }

    @Test
    void getNormTest() {
        assertEquals(4, new Coordinate(2, 2).getNorm());
        assertEquals(3, new Coordinate(2, 1).getNorm());
        assertEquals(2, new Coordinate(2, 0).getNorm());
        assertEquals(4, new Coordinate(2, -2).getNorm());

        assertNotEquals(0, new Coordinate(2, -2).getNorm());
        assertNotEquals(0, new Coordinate(-2, 2).getNorm());
    }

    @Test
    void reduceToTest() {
        assertEquals(new Coordinate(2, 2), new Coordinate(2, 2).reduceTo(4));
        assertEquals(new Coordinate(2, -1), new Coordinate(2, -2).reduceTo(3));
        assertEquals(new Coordinate(2, 0), new Coordinate(2, 2).reduceTo(2));
        assertEquals(new Coordinate(1, 0), new Coordinate(2, -2).reduceTo(1));
        assertEquals(new Coordinate(0, 0), new Coordinate(2, 2).reduceTo(0));

        assertNotEquals(new Coordinate(1, 2), new Coordinate(2, 2).reduceTo(3));
        assertNotEquals(new Coordinate(0, -2), new Coordinate(2, -2).reduceTo(2));
        assertNotEquals(new Coordinate(0, 1), new Coordinate(2, 2).reduceTo(1));
    }

    @Test
    void toStringTest() {
        assertEquals("[0,0]", new Coordinate().toString());
        assertEquals("[1,0]", new Coordinate(1, 0).toString());
        assertEquals("[0,1]", new Coordinate(0, 1).toString());
        assertEquals("[-1,-1]", new Coordinate(-1, -1).toString());
    }
}