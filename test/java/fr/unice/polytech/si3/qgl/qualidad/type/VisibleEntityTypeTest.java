package fr.unice.polytech.si3.qgl.qualidad.type;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VisibleEntityTypeTest {
    @Test
    void getTest() {
        assertEquals(VisibleEntityType.SHIP, VisibleEntityType.get("ship"));
        assertEquals(VisibleEntityType.REEF, VisibleEntityType.get("reef"));
        assertEquals(VisibleEntityType.STREAM, VisibleEntityType.get("stream"));
        assertThrows(IllegalArgumentException.class, () -> VisibleEntityType.get("oui"));
    }

    @Test
    void toStringTest() {
        assertEquals(VisibleEntityType.SHIP.toString(), "ship");
        assertEquals(VisibleEntityType.REEF.toString(), "reef");
        assertEquals(VisibleEntityType.STREAM.toString(), "stream");
    }
}