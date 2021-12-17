package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.TurnAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.ActionType;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement.RudderActions.*;
import static org.junit.Assert.assertEquals;

public class RudderActionsTest {
    @Nested
    class getRuddersActionsTest {
        Sailor sailor;

        @BeforeEach
        void setUp() {
            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.RUDDER)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getId()).thenReturn(0);
        }

        @Test
        void canMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.RUDDER)).thenReturn(true);

            assertEquals(2, getRuddersActions(Math.PI, 0, sailor).size());
            assertEquals(ActionType.MOVING, getRuddersActions(Math.PI, 0, sailor).get(0).getType());
            assertEquals(ActionType.TURN, getRuddersActions(Math.PI, 0, sailor).get(1).getType());
            assertEquals(Math.PI / 4, ((TurnAction) getRuddersActions(Math.PI, 0, sailor).get(1)).getRotation(), 0);
        }

        @Test
        void cantMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.RUDDER)).thenReturn(false);

            assertEquals(1, getRuddersActions(Math.PI, 0, sailor).size());
            assertEquals(ActionType.MOVING, getRuddersActions(Math.PI, 0, sailor).get(0).getType());
        }
    }

    @Nested
    class getBestRudderAngleTest {
        @Test
        void noAngleDifference() {
            assertEquals(0, getBestRudderAngle(0, 0), 0);
            assertEquals(0, getBestRudderAngle(Math.PI, Math.PI), 0);
            assertEquals(0, getBestRudderAngle(-Math.PI, -Math.PI), 0);
        }

        @Test
        void moreThan45Degrees() {
            assertEquals(-Math.PI / 4, getBestRudderAngle(0, Math.PI / 2), 0);
            assertEquals(Math.PI / 4, getBestRudderAngle(Math.PI / 2, 0), 0);
            assertEquals(Math.PI / 4, getBestRudderAngle(0, -Math.PI / 2), 0);
            assertEquals(-Math.PI / 4, getBestRudderAngle(-Math.PI / 2, 0), 0);
        }

        @Test
        void lessThen45Degrees() {
            assertEquals(-Math.PI / 8, getBestRudderAngle(0, Math.PI / 8), 0);
            assertEquals(Math.PI / 8, getBestRudderAngle(Math.PI / 2, Math.PI * 3 / 8), 0);
            assertEquals(Math.PI / 8, getBestRudderAngle(0, -Math.PI / 8), 0);
            assertEquals(-Math.PI / 8, getBestRudderAngle(-Math.PI / 2, -Math.PI * 3 / 8), 0);
        }
    }

    @Nested
    class getRudderActionsTest {
        Sailor sailor;

        @BeforeEach
        void setUp() {
            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.RUDDER)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getId()).thenReturn(0);
        }

        @Test
        void canMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.RUDDER)).thenReturn(true);

            assertEquals(2, getRudderActions(sailor, 0).size());
            assertEquals(ActionType.MOVING, getRudderActions(sailor, 0).get(0).getType());
            assertEquals(ActionType.TURN, getRudderActions(sailor, 0).get(1).getType());
        }

        @Test
        void cantMoveInOneTurn() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.RUDDER)).thenReturn(false);

            assertEquals(1, getRudderActions(sailor, 0).size());
            assertEquals(ActionType.MOVING, getRudderActions(sailor, 0).get(0).getType());
        }
    }
}