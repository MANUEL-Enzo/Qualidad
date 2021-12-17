package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SailEntityTest {
    @Test
    void getTest() {
        SailEntity sailEntity = new SailEntity(0, 0, false);

        assertFalse(sailEntity.isOpenned());
        assertEquals(EntityType.SAIL, sailEntity.getEntityType());
    }
}