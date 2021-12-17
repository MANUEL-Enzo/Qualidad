package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AngleAndSpeedTest {
    @Test
    void constructorTest() {
        assertEquals(1, new AngleAndSpeed(1, 1).getAngle(), 0);
        assertEquals(1, new AngleAndSpeed(1, 1).getSpeed(), 0);

        assertEquals(-Math.PI / 2, new AngleAndSpeed(1, 0, 2).getAngle(), 0);
        assertEquals(82.5, new AngleAndSpeed(1, 0, 2).getSpeed(), 0);

        assertEquals(Math.PI / 2, new AngleAndSpeed(0, 1, 2).getAngle(), 0);
        assertEquals(82.5, new AngleAndSpeed(0, 1, 2).getSpeed(), 0);

        assertEquals(0, new AngleAndSpeed(1, 1, 2).getAngle(), 0);
        assertEquals(165, new AngleAndSpeed(1, 1, 2).getSpeed(), 0);

        assertEquals(0, new AngleAndSpeed().getAngle(), 0);
        assertEquals(0, new AngleAndSpeed().getSpeed(), 0);
    }
}