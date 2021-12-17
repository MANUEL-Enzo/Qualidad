package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class RudderEntityTest {
    @Test
    void getTest() {
        RudderEntity rudderEntity = new RudderEntity(0, 0);

        assertEquals(EntityType.RUDDER, rudderEntity.getEntityType());
    }
}