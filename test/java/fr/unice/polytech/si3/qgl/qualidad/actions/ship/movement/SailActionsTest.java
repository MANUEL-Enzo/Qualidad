package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Coordinate;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.SailEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.ActionType;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement.SailActions.*;
import static org.junit.Assert.*;

public class SailActionsTest {
    @Nested
    class getSailsActionsTest {
        SailEntity sail;
        List<SailEntity> sails;
        Sailor sailor;
        List<Sailor> sailors;

        @BeforeEach
        void setUp() {
            sails = new ArrayList<>();
            sailors = new ArrayList<>();

            sail = Mockito.mock(SailEntity.class);
            Mockito.when(sail.getCoordinate()).thenReturn(new Coordinate());
            sails.add(sail);

            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.SAIL)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getEntityCoordinate(EntityType.SAIL)).thenReturn(new Coordinate());
            Mockito.when(sailor.canMoveInOneTurn(EntityType.SAIL)).thenReturn(true);
            Mockito.when(sailor.getId()).thenReturn(0);
            sailors.add(sailor);
        }

        @Test
        void alreadyLifted() {
            Mockito.when(sail.isOpenned()).thenReturn(true);

            assertEquals(0, getSailsActions(sails, sailors, 0, 10, 0).size());
        }

        @Test
        void alreadyLowered() {
            Mockito.when(sail.isOpenned()).thenReturn(false);

            assertEquals(0, getSailsActions(sails, sailors, 0, 0, 0).size());
        }

        @Test
        void needToBeLifted() {
            Mockito.when(sail.isOpenned()).thenReturn(false);

            assertEquals(2, getSailsActions(sails, sailors, 0, 10, 0).size());
        }

        @Test
        void needToBeLowered() {
            Mockito.when(sail.isOpenned()).thenReturn(true);

            assertEquals(2, getSailsActions(sails, sailors, 0, 0, 0).size());
        }
    }

    @Nested
    class getWantedStateSailTest {
        @Test
        void sameAngleAndPositiveStrength() {
            assertTrue(getWantedStateSail(0, 10, 0));
        }

        @Test
        void angleJustUnder90DegreesAndPositiveStrength() {
            assertTrue(getWantedStateSail(0, 10, -Math.PI / 2 + 0.000001));
            assertTrue(getWantedStateSail(0, 10, Math.PI / 2 - 0.000001));
            assertTrue(getWantedStateSail(-Math.PI / 2 + 0.000001, 10, 0));
            assertTrue(getWantedStateSail(Math.PI / 2 - 0.000001, 10, 0));
        }

        @Test
        void sameAngleAndZeroStrength() {
            assertFalse(getWantedStateSail(0, 0, 0));
        }

        @Test
        void angleOf180DegreesAndPositiveStrength() {
            assertFalse(getWantedStateSail(Math.PI, 10, 0));
            assertFalse(getWantedStateSail(0, 10, Math.PI));
            assertFalse(getWantedStateSail(-Math.PI, 10, 0));
            assertFalse(getWantedStateSail(0, 10, -Math.PI));
        }

        @Test
        void angleJustOver90DegreesAndPositiveStrength() {
            assertFalse(getWantedStateSail(0, 10, -Math.PI / 2 - 0.000001));
            assertFalse(getWantedStateSail(0, 10, Math.PI / 2 + 0.000001));
            assertFalse(getWantedStateSail(-Math.PI / 2 - 0.000001, 10, 0));
            assertFalse(getWantedStateSail(Math.PI / 2 + 0.000001, 10, 0));
        }
    }

    @Nested
    class getSailActionsTest {
        Sailor sailor;

        @BeforeEach
        void setUp() {
            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.SAIL)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getId()).thenReturn(0);
        }

        @Test
        void cantMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.SAIL)).thenReturn(false);

            assertEquals(1, getLowerSailActions(sailor).size());
            assertEquals(ActionType.MOVING, getLowerSailActions(sailor).get(0).getType());
        }

        @Test
        void canMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.SAIL)).thenReturn(true);

            assertEquals(2, getLowerSailActions(sailor).size());
            assertEquals(ActionType.MOVING, getLowerSailActions(sailor).get(0).getType());
            assertEquals(ActionType.LOWER_SAIL, getLowerSailActions(sailor).get(1).getType());
        }
    }

    @Nested
    class getLowerSailActionsTest {
        Sailor sailor;

        @BeforeEach
        void setUp() {
            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.SAIL)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getId()).thenReturn(0);
            Mockito.when(sailor.canMoveInOneTurn(EntityType.SAIL)).thenReturn(true);
        }

        @Test
        void liftSail() {
            assertEquals(2, getSailActions(sailor, true).size());
            assertEquals(ActionType.MOVING, getSailActions(sailor, true).get(0).getType());
            assertEquals(ActionType.LIFT_SAIL, getSailActions(sailor, true).get(1).getType());
        }

        @Test
        void lowerSail() {
            assertEquals(2, getSailActions(sailor, false).size());
            assertEquals(ActionType.MOVING, getSailActions(sailor, false).get(0).getType());
            assertEquals(ActionType.LOWER_SAIL, getSailActions(sailor, false).get(1).getType());
        }
    }

    @Nested
    class getLiftSailActionsTest {
        Sailor sailor;

        @BeforeEach
        void setUp() {
            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.SAIL)).thenReturn(new MovingAction(0, 0, 0));
        }

        @Test
        void cantMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.SAIL)).thenReturn(false);

            assertEquals(1, getLiftSailActions(sailor).size());
            assertEquals(ActionType.MOVING, getLiftSailActions(sailor).get(0).getType());
        }

        @Test
        void canMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.SAIL)).thenReturn(true);
            Mockito.when(sailor.getId()).thenReturn(0);

            assertEquals(2, getLiftSailActions(sailor).size());
            assertEquals(ActionType.MOVING, getLiftSailActions(sailor).get(0).getType());
            assertEquals(ActionType.LIFT_SAIL, getLiftSailActions(sailor).get(1).getType());
        }
    }
}