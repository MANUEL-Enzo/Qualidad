package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanonEntityTest {
    @Test
    void getTest() {
        CanonEntity canonEntity = new CanonEntity(0, 0, true, 0);

        assertTrue(canonEntity.isLoaded());
        assertEquals(0, canonEntity.getAngle(), 0);
        assertEquals(EntityType.CANON, canonEntity.getEntityType());
    }
}