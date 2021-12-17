package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class OarEntityTest {
    @Test
    void getTest() {
        OarEntity oarEntity = new OarEntity(0, 0);

        assertEquals(EntityType.OAR, oarEntity.getEntityType());
    }
}