package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Circle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Point;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Polygon;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PositionDeterminerTest {

    @Nested
    class getBestPositionBetweenTwoCheckpointsTest {

        @Test
        void shipBotNextCheckpointTop() {
            assertEquals(new Position(),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(0, -100), new Position(), 50, new Position(0, 100)));
        }

        @Test
        void shipBotNextCheckpointRight() {
            assertEquals(new Position(25, -25),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(0, -100), new Position(), 50, new Position(100, 0)));
        }

        @Test
        void shipBotNextCheckpointLeft() {
            assertEquals(new Position(-25, -25),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(0, -100), new Position(), 50, new Position(-100, 0)));
        }

        @Test
        void shipLeftNextCheckpointTop() {
            assertEquals(new Position(-25, 25),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(-100, 0), new Position(), 50, new Position(0, 100)));
        }

        @Test
        void shipTopNextCheckpointRight() {
            assertEquals(new Position(25, 25),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(0, 100), new Position(), 50, new Position(100, 0)));
        }

        @Test
        void shipLeftNextCheckpointLeft() {
            assertEquals(new Position(50, 0),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(100, 0), new Position(), 50, new Position(100, 0)));
        }

        @Test
        void shipLeftNextCheckpointTopRight() {
            assertEquals(new Position(42.67766952966369, 17.677669529663685),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(100, 0), new Position(), 50, new Position(100, 100)));
        }

        @Test
        void shipLeftNextCheckpointTopLeft() {
            assertEquals(new Position(7.322330470336315, 17.677669529663685),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(100, 0), new Position(), 50, new Position(-100, 100)));
        }

        @Test
        void shipBotRightNextCheckpointBotLeft() {
            assertEquals(new Position(0, -35.35533905932737),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(100, -100), new Position(), 50, new Position(-100, -100)));
        }

        @Test
        void shipBotRightNextCheckpointTopMidRight() {
            assertEquals(new Position(28.858009417162634, 4.683010245334213),
                    PositionDeterminer.getBestPositionBetweenTwoCheckpoints(new Position(100, -100), new Position(), 50, new Position(50, 100)));
        }
    }

    @Nested
    class getBestPositionTest {
        List<Checkpoint> checkpoints;
        Position shipPosition;
        Shape shipShape;

        @BeforeEach
        void setUp() {
            checkpoints = new ArrayList<>(Collections.singletonList(new Checkpoint(new Position(), new Circle(50))));
            shipPosition = new Position(0, -100, Math.PI / 2);
            shipShape = new Polygon(0, Arrays.asList(new Point(1, 1), new Point(2, 0), new Point(1, -1), new Point(-1, -1), new Point(-1, 1)));
        }

        @Test
        void oneCheckpoint() {
            assertEquals(new Position(), PositionDeterminer.getBestPosition(checkpoints, shipPosition, shipShape, new ArrayList<>()));
        }

        @Test
        void twoCheckpoints() {
            checkpoints.add(new Checkpoint(new Position(100, 0), new Circle(50)));

            assertEquals(new Position(25.0, -25.0), PositionDeterminer.getBestPosition(checkpoints, shipPosition, shipShape, new ArrayList<>()));
        }

        @Test
        void collidingOneCheckpoint() {
            shipPosition = new Position();

            assertNull(PositionDeterminer.getBestPosition(checkpoints, shipPosition, shipShape, new ArrayList<>()));
        }

        @Test
        void collidingCheckpointTwoCheckpoints() {
            shipPosition = new Position();

            checkpoints.add(new Checkpoint(new Position(100, 0), new Circle(50)));

            assertEquals(new Position(100, 0), PositionDeterminer.getBestPosition(checkpoints, shipPosition, shipShape, new ArrayList<>()));
        }

        @Test
        void zeroCheckpoint() {
            checkpoints.clear();

            assertNull(PositionDeterminer.getBestPosition(checkpoints, shipPosition, shipShape, new ArrayList<>()));
        }
    }

    @Nested
    class getBestPositionToAvoidReefsTest {
        List<VisibleEntity> visibleEntities;
        Position shipPosition;
        Shape shipShape;

        @BeforeEach
        void setUp() {
            visibleEntities = new ArrayList<>();
            shipPosition = new Position(0, -100, Math.PI / 2);
            shipShape = new Polygon(0, Arrays.asList(new Point(1, 1), new Point(2, 0), new Point(1, -1), new Point(-1, -1), new Point(-1, 1)));
        }


    }
}