package fr.unice.polytech.si3.qgl.qualidad.type;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class SideTypeTest {
    @Test
    void toStringTest() {
        assertEquals(SideType.PORTSIDE.toString(), "Portside");
        assertEquals(SideType.STARBOARD.toString(), "Starboard");
        assertEquals(SideType.NO_SIDE.toString(), "No side");
        assertEquals(SideType.NOT_ASSIGNED.toString(), "Not assigned");
    }
}