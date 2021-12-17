package fr.unice.polytech.si3.qgl.qualidad.type;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EntityTypeTest {
    @Test
    void getTest() {
        assertEquals(EntityType.OAR, EntityType.get("oar"));
        assertEquals(EntityType.SAIL, EntityType.get("sail"));
        assertEquals(EntityType.RUDDER, EntityType.get("rudder"));
        assertEquals(EntityType.WATCH, EntityType.get("watch"));
        assertEquals(EntityType.CANON, EntityType.get("canon"));
        assertThrows(IllegalArgumentException.class, () -> EntityType.get("oui"));
    }

    @Test
    void toStringTest() {
        assertEquals(EntityType.OAR.toString(), "oar");
        assertEquals(EntityType.ALTERNATIVE_OAR.toString(), "Alternative oar");
        assertEquals(EntityType.SAIL.toString(), "sail");
        assertEquals(EntityType.RUDDER.toString(), "rudder");
        assertEquals(EntityType.WATCH.toString(), "watch");
        assertEquals(EntityType.CANON.toString(), "canon");
    }
}