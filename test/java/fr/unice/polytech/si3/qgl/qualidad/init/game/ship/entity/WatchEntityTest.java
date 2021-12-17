package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class WatchEntityTest {
    @Test
    void getTest() {
        WatchEntity watchEntity = new WatchEntity(0, 0);

        assertEquals(EntityType.WATCH, watchEntity.getEntityType());
    }
}