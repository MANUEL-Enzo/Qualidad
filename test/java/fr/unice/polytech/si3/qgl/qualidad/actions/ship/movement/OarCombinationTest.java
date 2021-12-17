package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class OarCombinationTest {
    @Test
    void constructorTest() {
        assertEquals(1, new OarCombination(1, 1).getNbOarsLeft());
        assertEquals(1, new OarCombination(1, 1).getNbOarsRight());

        assertEquals(1, new OarCombination(1, 2).getNbOarsLeft());
        assertEquals(2, new OarCombination(1, 2).getNbOarsRight());
    }
}