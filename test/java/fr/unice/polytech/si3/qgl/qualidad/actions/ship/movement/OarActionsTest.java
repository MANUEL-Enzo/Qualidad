package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.ActionType;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement.OarActions.*;
import static org.junit.Assert.*;

public class OarActionsTest {
    @Nested
    class getOarsActionsTest {
        Sailor sailor1, sailor2;
        Map<SideType, List<Sailor>> sailors;

        @BeforeEach
        void setUp() {
            sailor1 = Mockito.mock(Sailor.class);
            Mockito.when(sailor1.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor1.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor1.getSideType()).thenReturn(SideType.PORTSIDE);
            Mockito.when(sailor1.getId()).thenReturn(0);

            sailor2 = Mockito.mock(Sailor.class);
            Mockito.when(sailor2.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor2.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor2.getSideType()).thenReturn(SideType.STARBOARD);
            Mockito.when(sailor2.getId()).thenReturn(0);

            sailors = Map.of(SideType.PORTSIDE, Arrays.asList(sailor1, sailor2), SideType.STARBOARD, Arrays.asList(sailor2, sailor1));
        }

        @Test
        void noSailorNeeded() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(4, getOarsActions(new Position(100, 0), new Position(), 2, 2, 2, 4, sailors, 0, 0, 0).size());
        }

        @Test
        void noSailorCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);

            assertEquals(2, getOarsActions(new Position(100, 0), new Position(), 2, 2, 2, 4, sailors, 0, 0, 0).size());
        }
    }

    @Nested
    class getBestOarCombinationTest {
        @Test
        void forwardCombination() {
            assertEquals(1, getBestOarCombination(new Position(100, 0), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsLeft());
            assertEquals(1, getBestOarCombination(new Position(100, 0), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsRight());
        }

        @Test
        void leftCombination() {
            assertEquals(0, getBestOarCombination(new Position(0, 100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsLeft());
            assertEquals(2, getBestOarCombination(new Position(0, 100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsRight());
        }

        @Test
        void topLeftCombination() {
            assertEquals(0, getBestOarCombination(new Position(100, 100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsLeft());
            assertEquals(1, getBestOarCombination(new Position(100, 100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsRight());
        }

        @Test
        void rightCombination() {
            assertEquals(2, getBestOarCombination(new Position(0, -100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsLeft());
            assertEquals(0, getBestOarCombination(new Position(0, -100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsRight());
        }

        @Test
        void topRightCombination() {
            assertEquals(1, getBestOarCombination(new Position(100, -100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsLeft());
            assertEquals(0, getBestOarCombination(new Position(100, -100), new Position(), 2, 2, 2, 4, 0, 0, 0).getNbOarsRight());
        }
    }

    @Nested
    class getOarCombinationsByAngleAndSpeedTest {
        @Test
        void noCombination() {
            assertEquals(0, getOarCombinationsByAngleAndSpeed(0, 0, 0, 0).size());
        }

        @Test
        void oneCombination() {
            assertEquals(1, getOarCombinationsByAngleAndSpeed(1, 0, 1, 1).size());
            assertEquals(1, getOarCombinationsByAngleAndSpeed(0, 1, 1, 1).size());
        }

        @Test
        void twoCombinations() {
            assertEquals(2, getOarCombinationsByAngleAndSpeed(2, 0, 2, 2).size());
            assertEquals(2, getOarCombinationsByAngleAndSpeed(0, 2, 2, 2).size());
        }

        @Test
        void threeCombinations() {
            assertEquals(3, getOarCombinationsByAngleAndSpeed(1, 1, 2, 2).size());
            assertEquals(3, getOarCombinationsByAngleAndSpeed(1, 1, 2, 2).size());
            assertEquals(3, getOarCombinationsByAngleAndSpeed(3, 0, 3, 3).size());
            assertEquals(3, getOarCombinationsByAngleAndSpeed(0, 3, 3, 3).size());
        }
    }

    @Nested
    class isBestAngleAndDistanceTest {
        @Test
        void notInitializedAngleAndDistance() {
            assertTrue(isBestAngleAndDistance(new AngleAndDistance(), new AngleAndDistance(1, 1)));
        }

        @Test
        void lowerAngle() {
            assertTrue(isBestAngleAndDistance(new AngleAndDistance(1, 1), new AngleAndDistance(0.5, 1)));
        }

        @Test
        void higherAngle() {
            assertFalse(isBestAngleAndDistance(new AngleAndDistance(1, 1), new AngleAndDistance(1.5, 1)));
        }

        @Test
        void sameAngleAndLowerDistance() {
            assertTrue(isBestAngleAndDistance(new AngleAndDistance(1, 1), new AngleAndDistance(1, 0.5)));
        }

        @Test
        void sameAngleAndHigherDistance() {
            assertFalse(isBestAngleAndDistance(new AngleAndDistance(1, 1), new AngleAndDistance(1, 1.5)));
        }
    }

    @Nested
    class getRowersActionsOfShipTest {
        Sailor sailor1, sailor2;
        Map<SideType, List<Sailor>> sailors;

        @BeforeEach
        void setUp() {
            sailor1 = Mockito.mock(Sailor.class);
            Mockito.when(sailor1.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor1.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor1.getSideType()).thenReturn(SideType.PORTSIDE);
            Mockito.when(sailor1.getId()).thenReturn(0);

            sailor2 = Mockito.mock(Sailor.class);
            Mockito.when(sailor2.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor2.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor2.getSideType()).thenReturn(SideType.STARBOARD);
            Mockito.when(sailor2.getId()).thenReturn(0);

            sailors = Map.of(SideType.PORTSIDE, Arrays.asList(sailor1, sailor2), SideType.STARBOARD, Arrays.asList(sailor2, sailor1));
        }

        @Test
        void noSailorNeeded() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(0, getRowersActionsOfShip(new OarCombination(0, 0), sailors).size());
        }

        @Test
        void noSailorCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);

            assertEquals(2, getRowersActionsOfShip(new OarCombination(1, 1), sailors).size());
            assertEquals(2, getRowersActionsOfShip(new OarCombination(2, 0), sailors).size());
            assertEquals(2, getRowersActionsOfShip(new OarCombination(0, 2), sailors).size());
        }

        @Test
        void oneSailorCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);

            assertEquals(3, getRowersActionsOfShip(new OarCombination(1, 1), sailors).size());
            assertEquals(3, getRowersActionsOfShip(new OarCombination(2, 0), sailors).size());
            assertEquals(3, getRowersActionsOfShip(new OarCombination(0, 2), sailors).size());
        }

        @Test
        void allSailorsCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(4, getRowersActionsOfShip(new OarCombination(1, 1), sailors).size());
            assertEquals(4, getRowersActionsOfShip(new OarCombination(2, 0), sailors).size());
            assertEquals(4, getRowersActionsOfShip(new OarCombination(0, 2), sailors).size());
        }

        @Test
        void oneSailorNeededAndCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(2, getRowersActionsOfShip(new OarCombination(1, 0), sailors).size());
            assertEquals(2, getRowersActionsOfShip(new OarCombination(0, 1), sailors).size());
        }

        @Test
        void oneSailorNeededAndCantRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(1, getRowersActionsOfShip(new OarCombination(0, 1), sailors).size());
            assertEquals(1, getRowersActionsOfShip(new OarCombination(1, 0), sailors).size());
        }
    }

    @Nested
    class getRowersActionsBySideTest {
        Sailor sailor1, sailor2;
        List<Sailor> sailors;

        @BeforeEach
        void setUp() {
            sailor1 = Mockito.mock(Sailor.class);
            Mockito.when(sailor1.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor1.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor1.getSideType()).thenReturn(SideType.PORTSIDE);
            Mockito.when(sailor1.getId()).thenReturn(0);

            sailor2 = Mockito.mock(Sailor.class);
            Mockito.when(sailor2.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor2.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor2.getSideType()).thenReturn(SideType.STARBOARD);
            Mockito.when(sailor2.getId()).thenReturn(0);

            sailors = Arrays.asList(sailor1, sailor2);
        }

        @Test
        void noSailorNeeded() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(0, getRowersActionsBySide(0, SideType.PORTSIDE, sailors).size());
        }

        @Test
        void noSailorCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);

            assertEquals(2, getRowersActionsBySide(2, SideType.PORTSIDE, sailors).size());
        }

        @Test
        void oneSailorCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);

            assertEquals(3, getRowersActionsBySide(2, SideType.PORTSIDE, sailors).size());
        }

        @Test
        void oneSailorNeededAndCanRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(2, getRowersActionsBySide(1, SideType.PORTSIDE, sailors).size());
        }

        @Test
        void oneSailorNeededAndCantRow() {
            Mockito.when(sailor1.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);
            Mockito.when(sailor2.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(1, getRowersActionsBySide(1, SideType.PORTSIDE, sailors).size());
        }
    }

    @Nested
    class getRowerActionsTest {
        Sailor sailor;

        @BeforeEach
        void setUp() {
            sailor = Mockito.mock(Sailor.class);
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getMoveToGoToAssignment(EntityType.ALTERNATIVE_OAR)).thenReturn(new MovingAction(0, 0, 0));
            Mockito.when(sailor.getId()).thenReturn(0);
        }

        @Test
        void canMoveInOneTurnOar() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.OAR)).thenReturn(true);

            assertEquals(2, getRowerActions(EntityType.OAR, sailor).size());
            assertEquals(ActionType.MOVING, getRowerActions(EntityType.OAR, sailor).get(0).getType());
            assertEquals(ActionType.OAR, getRowerActions(EntityType.OAR, sailor).get(1).getType());
        }

        @Test
        void cantMoveInOneTurnOar() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.OAR)).thenReturn(false);

            assertEquals(1, getRowerActions(EntityType.OAR, sailor).size());
            assertEquals(ActionType.MOVING, getRowerActions(EntityType.OAR, sailor).get(0).getType());
        }

        @Test
        void canMoveInOneTurnAlternativeOar() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(true);

            assertEquals(2, getRowerActions(EntityType.ALTERNATIVE_OAR, sailor).size());
            assertEquals(ActionType.MOVING, getRowerActions(EntityType.ALTERNATIVE_OAR, sailor).get(0).getType());
            assertEquals(ActionType.OAR, getRowerActions(EntityType.ALTERNATIVE_OAR, sailor).get(1).getType());
        }

        @Test
        void cantMoveInOneTurnAlternativeOar() {
            Mockito.when(sailor.canMoveInOneTurn(EntityType.ALTERNATIVE_OAR)).thenReturn(false);

            assertEquals(1, getRowerActions(EntityType.ALTERNATIVE_OAR, sailor).size());
            assertEquals(ActionType.MOVING, getRowerActions(EntityType.ALTERNATIVE_OAR, sailor).get(0).getType());
        }
    }
}