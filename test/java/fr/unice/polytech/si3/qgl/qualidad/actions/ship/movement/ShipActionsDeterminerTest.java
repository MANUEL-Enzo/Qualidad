package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.*;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Coordinate;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement.ShipActionsDeterminer.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipActionsDeterminerTest {
    @Nested
    class addActionsTest {
        Action action1, action2, action3, action4;
        List<Action> actions, actionsToAdd;

        @BeforeEach
        void setUp() {
            action1 = new MovingAction(1, 1, 1);
            action2 = new TurnAction(1, 1);
            action3 = new OarAction(1);
            action4 = new LiftSailAction(2);

            actions = new ArrayList<>();
            actionsToAdd = new ArrayList<>();
        }

        @Test
        void oneActionToAddNoIn() {
            actionsToAdd.add(action1);

            addActions(actions, actionsToAdd);

            assertEquals(1, actions.size());
        }

        @Test
        void twoActionToAddNoIn() {
            actionsToAdd.add(action1);
            actionsToAdd.add(action2);

            addActions(actions, actionsToAdd);

            assertEquals(2, actions.size());
        }

        @Test
        void oneActionToAddOneIn() {
            actions.add(action4);
            actionsToAdd.add(action1);

            addActions(actions, actionsToAdd);

            assertEquals(2, actions.size());
        }

        @Test
        void twoActionToAddOneIn() {
            actions.add(action3);
            actions.add(action4);
            actionsToAdd.add(action1);

            addActions(actions, actionsToAdd);

            assertEquals(2, actions.size());
        }
    }

    @Nested
    class getShipPositionTest {
        Action action1, action2, action3;
        List<Action> actions;

        @BeforeEach
        void setUp() {
            action1 = new MovingAction(1, 1, 1);
            action2 = new TurnAction(2, 1);
            action3 = new OarAction(1);

            actions = new ArrayList<>();
        }

        @Test
        void noTurnAction() {
            assertEquals(new Position(), getShipPosition(actions, new Position()));

            actions.add(action1);

            assertEquals(new Position(), getShipPosition(actions, new Position()));

            actions.add(action3);

            assertEquals(new Position(), getShipPosition(actions, new Position()));
        }

        @Test
        void oneTurnAction() {
            actions.add(action1);
            actions.add(action2);
            actions.add(action3);

            assertEquals(new Position(0, 0, 1), getShipPosition(actions, new Position()));
        }
    }

    @Nested
    class execMovingActionsTest {
        Action action1, action2, action3;
        List<Action> actions;
        Sailor sailor1, sailor2;
        Map<Integer, Sailor> sailorById;

        @BeforeEach
        void setUp() {
            action1 = new MovingAction(1, 1, 1);
            action2 = new MovingAction(2, -1, -1);
            action3 = new OarAction(1);

            actions = new ArrayList<>();

            sailor1 = new Sailor(1, 0, 0, "s1");
            sailor2 = new Sailor(2, 0, 0, "s2");

            sailorById = Map.of(sailor1.getId(), sailor1, sailor2.getId(), sailor2);
        }

        @Test
        void noMovingActionsToExec() {
            execMovingActions(actions, sailorById);

            assertEquals(new Coordinate(), sailor1.getCoordinate());
            assertEquals(new Coordinate(), sailor2.getCoordinate());

            actions.add(action3);

            execMovingActions(actions, sailorById);

            assertEquals(new Coordinate(), sailor1.getCoordinate());
            assertEquals(new Coordinate(), sailor2.getCoordinate());
        }

        @Test
        void oneMovingActionsToExec() {
            actions.add(action1);

            execMovingActions(actions, sailorById);

            assertEquals(new Coordinate(1, 1), sailor1.getCoordinate());
            assertEquals(new Coordinate(), sailor2.getCoordinate());
        }

        @Test
        void twoMovingActionsToExec() {
            actions.add(action1);
            actions.add(action2);

            execMovingActions(actions, sailorById);

            assertEquals(new Coordinate(1, 1), sailor1.getCoordinate());
            assertEquals(new Coordinate(-1, -1), sailor2.getCoordinate());
        }

        @Test
        void twoMovingActionsToExecAndOneOarAction() {
            actions.add(action1);
            actions.add(action2);
            actions.add(action3);

            execMovingActions(actions, sailorById);

            assertEquals(new Coordinate(1, 1), sailor1.getCoordinate());
            assertEquals(new Coordinate(-1, -1), sailor2.getCoordinate());
        }
    }
}