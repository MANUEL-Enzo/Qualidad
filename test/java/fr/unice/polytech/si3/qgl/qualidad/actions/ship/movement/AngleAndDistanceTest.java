package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AngleAndDistanceTest {
    @Test
    void constructorTest() {
        assertEquals(1, new AngleAndDistance(1, 1).getAngle(), 0);
        assertEquals(1, new AngleAndDistance(1, 1).getDistance(), 0);

        assertEquals(-1, new AngleAndDistance().getAngle(), 0);
        assertEquals(-1, new AngleAndDistance().getDistance(), 0);
    }

    @Test
    void hasBeenInitialized() {
        assertFalse(new AngleAndDistance().hasBeenInitialized());
        assertFalse(new AngleAndDistance(-1, -1).hasBeenInitialized());
        assertFalse(new AngleAndDistance(1, -1).hasBeenInitialized());

        assertTrue(new AngleAndDistance(-1, 1).hasBeenInitialized());
        assertTrue(new AngleAndDistance(1, 1).hasBeenInitialized());
    }
}