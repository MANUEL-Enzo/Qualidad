package fr.unice.polytech.si3.qgl.qualidad.next.round;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class WindTest {
    @Test
    void getTest() {
        Wind wind = new Wind(1.35, 2.47);

        assertEquals(1.35, wind.getOrientation(), 0);
        assertEquals(2.47, wind.getStrength(), 0);
    }
}