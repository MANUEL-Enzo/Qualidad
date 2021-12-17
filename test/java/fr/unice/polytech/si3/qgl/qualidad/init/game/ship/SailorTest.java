package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SailorTest {
    Sailor sailor;

    @BeforeEach
    void setUp() {
        sailor = new Sailor(0, 0, 0, "s");
    }

    @Test
    void getTest() {
        assertEquals(new Coordinate(), sailor.getCoordinate());
        assertEquals(SideType.NOT_ASSIGNED, sailor.getSideType());
        assertEquals("s", sailor.getName());
        assertEquals(0, sailor.getId());
    }

    @Test
    void assignSideTest() {
        assertEquals(SideType.NOT_ASSIGNED, sailor.getSideType());

        sailor.assignSide(SideType.PORTSIDE);

        assertEquals(SideType.PORTSIDE, sailor.getSideType());

        sailor.assignSide(SideType.STARBOARD);

        assertEquals(SideType.STARBOARD, sailor.getSideType());

        sailor.assignSide(SideType.NO_SIDE);

        assertEquals(SideType.NO_SIDE, sailor.getSideType());
    }

    @Test
    void hasAssigmentTest() {
        assertFalse(sailor.hasAssignment(EntityType.OAR));

        sailor.assign(EntityType.OAR, new Coordinate());

        assertTrue(sailor.hasAssignment(EntityType.OAR));
        assertFalse(sailor.hasAssignment(EntityType.ALTERNATIVE_OAR));
    }

    @Test
    void assignTest() {
        sailor.assign(EntityType.OAR, new Coordinate(1, 1));

        assertThrows(IllegalArgumentException.class, () -> sailor.assign(EntityType.OAR, new Coordinate(1, 1)));

        assertTrue(sailor.hasAssignment(EntityType.OAR));
    }

    @Test
    void isOnAssignmentTest() {
        assertThrows(IllegalArgumentException.class, () -> sailor.isOnAssignment(EntityType.OAR));

        sailor.assign(EntityType.OAR, new Coordinate(1, 1));

        assertFalse(sailor.isOnAssignment(EntityType.OAR));

        sailor.move(1, 1);

        assertTrue(sailor.isOnAssignment(EntityType.OAR));
    }

    @Test
    void canMoveInOneTurnTest() {
        assertThrows(IllegalArgumentException.class, () -> sailor.canMoveInOneTurn(EntityType.OAR));

        sailor.assign(EntityType.OAR, new Coordinate(1, 1));

        assertTrue(sailor.canMoveInOneTurn(EntityType.OAR));

        sailor.assign(EntityType.ALTERNATIVE_OAR, new Coordinate(1, -5));

        assertFalse(sailor.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR));
    }

    @Test
    void getMoveToGoToAssignmentTest() {
        assertThrows(IllegalArgumentException.class, () -> sailor.getMoveToGoToAssignment(EntityType.OAR));

        sailor.assign(EntityType.OAR, new Coordinate(2, 1));

        assertEquals(2, sailor.getMoveToGoToAssignment(EntityType.OAR).getXdistance());
        assertEquals(1, sailor.getMoveToGoToAssignment(EntityType.OAR).getYdistance());

        sailor.assign(EntityType.ALTERNATIVE_OAR, new Coordinate(2, 7));

        assertEquals(2, sailor.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR).getXdistance());
        assertEquals(3, sailor.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR).getYdistance());
    }

    @Test
    void getLegalMoveToTest() {
        assertEquals(new Coordinate(1, 2), sailor.getLegalMoveTo(new Coordinate(1, 2)));
        assertEquals(new Coordinate(1, -4), sailor.getLegalMoveTo(new Coordinate(1, -8)));
    }

    @Test
    void moveTest() {
        sailor.move(1, 1);

        assertEquals(new Coordinate(1, 1), sailor.getCoordinate());

        sailor.move(-2, 1);

        assertEquals(new Coordinate(-1, 2), sailor.getCoordinate());
    }

    @Test
    void getEntityCoordinateTest() {
        assertThrows(IllegalArgumentException.class, () -> sailor.getEntityCoordinate(EntityType.OAR));

        sailor.assign(EntityType.OAR, new Coordinate(2, 1));
        sailor.assign(EntityType.ALTERNATIVE_OAR, new Coordinate(-6, 10));

        assertEquals(new Coordinate(2, 1), sailor.getEntityCoordinate(EntityType.OAR));
        assertEquals(new Coordinate(-6, 10), sailor.getEntityCoordinate(EntityType.ALTERNATIVE_OAR));
    }

}