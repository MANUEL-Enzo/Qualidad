package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Coordinate;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class EntityTest {
    Entity entity;

    @BeforeEach
    void setUp() {
        entity = new OarEntity(0, 0);
    }

    @Test
    void assignSideTest() {
        assertEquals(SideType.NOT_ASSIGNED, entity.getSideType());

        entity.assignSide(SideType.PORTSIDE);

        assertEquals(SideType.PORTSIDE, entity.getSideType());

        entity.assignSide(SideType.STARBOARD);

        assertEquals(SideType.STARBOARD, entity.getSideType());

        entity.assignSide(SideType.NO_SIDE);

        assertEquals(SideType.NO_SIDE, entity.getSideType());
    }

    @Test
    void assignTest() {
        assertFalse(entity.isAssigned());

        entity.assign();

        assertTrue(entity.isAssigned());
    }

    @Test
    void isEntityTest() {
        assertFalse(entity.isEntity(EntityType.RUDDER));
        assertFalse(entity.isEntity(EntityType.CANON));
        assertFalse(entity.isEntity(EntityType.SAIL));
        assertFalse(entity.isEntity(EntityType.WATCH));
        assertFalse(entity.isEntity(EntityType.RUDDER));
        assertFalse(entity.isEntity(EntityType.ALTERNATIVE_OAR));
        assertTrue(entity.isEntity(EntityType.OAR));
    }

    @Test
    void isSideTest() {
        assertFalse(entity.isSide(SideType.NO_SIDE));
        assertFalse(entity.isSide(SideType.PORTSIDE));
        assertFalse(entity.isSide(SideType.STARBOARD));
        assertTrue(entity.isSide(SideType.NOT_ASSIGNED));
    }

    @Test
    void getTest() {
        assertEquals(new Coordinate(), entity.getCoordinate());
        assertEquals(SideType.NOT_ASSIGNED, entity.getSideType());
        assertEquals(EntityType.OAR, entity.getEntityType());
    }
}