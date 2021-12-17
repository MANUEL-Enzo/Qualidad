package fr.unice.polytech.si3.qgl.qualidad.init.game;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {

    @Test
    void constructorsTest() {
        assertEquals(new Position(0, 0, 0), new Position(0, 0));
        assertEquals(new Position(), new Position(0, 0));
        assertEquals(new Position().hashCode(), new Position().hashCode());
        assertEquals(new Position(new Position(1, 0, 1), new Position(0, 1, 1)), new Position(1, 1, 2));
        assertNotEquals(new Position(0, 0, 1), new Position(0, 0));
        assertNotEquals(new Position(new Position(1, 0, 1), new Position(0, 1, 1)), new Position(1, 1));
        assertNotEquals(new Position(1, 0).hashCode(), new Position(0, 1).hashCode());
    }

    @Test
    void minusTest() {
        assertEquals(new Position().minus(new Position()), new Position());
        assertEquals(new Position(0, 1, 1).minus(new Position(1, 1, 1)), new Position(-1, 0));
        assertNotEquals(new Position().minus(new Position(0, 0, 1)), new Position());
    }

    @Test
    void timesTest() {
        assertEquals(new Position().times(0), new Position());
        assertEquals(new Position().times(10), new Position());
        assertEquals(new Position(10, 10).times(0), new Position());
        assertEquals(new Position(10, 10).times(1.0 / 2.0), new Position(5, 5));
        assertEquals(new Position(10, 10).times(10), new Position(100, 100));
    }

    @Test
    void scalarTest() {
        assertEquals(new Position().scalar(new Position()), 0);
        assertEquals(new Position(1, 1).scalar(new Position()), 0);
        assertEquals(new Position().scalar(new Position(1, 1)), 0);
        assertEquals(new Position(1, 1).scalar(new Position(1, 1)), 2);
        assertEquals(new Position(1, -1).scalar(new Position(1, 1)), 0);
        assertEquals(new Position(1, -1).scalar(new Position(-1, 1)), -2);
    }

    @Test
    public void getVectorTest() {
        Position p = new Position(2, 2);
        Position p1 = new Position(1, 1);
        Position p2 = new Position(-1, -1);

        Position resPositif = new Position(1, 1, 0.7853981633974484);
        Position resNegatif = new Position(-1, -1, -2.356194490192345);
        Position resNull = new Position(0, 0, 0);
        Position resDifferrentSignCoordP = new Position(3, 3, 0.7853981633974483);
        Position resDifferrentSignCoordN = new Position(-3, -3, -2.356194490192345);

        assertEquals(resPositif, p1.getVector(p));
        assertEquals(resNegatif, p.getVector(p1));
        assertEquals(resNull, p.getVector(p));
        assertEquals(p2.getVector(p), resDifferrentSignCoordP);
        assertEquals(p.getVector(p2), resDifferrentSignCoordN);
    }

    @Test
    void getDistanceTest() {
        Position p = new Position(2, 2);
        Position p1 = new Position(1, 1);
        Position p2 = new Position(-1, -1);
        Position p3 = new Position(-2, -2);

        assertEquals(p.getDistance(p1), 1.4142135623730951);
        assertEquals(p.getDistance(p2), 4.242640687119285);
        assertEquals(p3.getDistance(p2), 1.4142135623730951);
    }

    @Test
    void getAngleBetweenTest() {
        double orientation1 = Math.PI;
        double orientation2 = 2 * Math.PI;
        double orientation3 = Math.PI / 2;
        double orientation4 = Math.PI / 4;
        double orientation5 = -Math.PI;

        assertEquals(Position.getAngleBetween(orientation1, orientation3), Position.getAngleBetween(orientation2, orientation3));
        assertEquals(Position.getAngleBetween(orientation1, orientation4), 3 * Math.PI / 4);
        assertEquals(Position.getAngleBetween(orientation3, orientation2), Math.PI / 2);
        assertEquals(Position.getAngleBetween(orientation1, orientation5), 0);

    }

    @Test
    void moduloTest() {
        double orientation1 = 5 * Math.PI;
        double orientation2 = -5 * Math.PI;
        double orientation3 = Math.PI;

        assertEquals(Position.modulo(orientation1), Math.PI);
        assertEquals(Position.modulo(orientation2), -Math.PI);
        assertEquals(Position.modulo(orientation3), Math.PI);
    }

    @Test
    void getProjectionTest() {
        assertEquals(new Position().getProjection(new Position(0, 1), new Position(1, 1)), new Position(0.5, 0.5));
    }

    @Test
    void getNextPositionTest() {
        assertEquals(new Position().getNextPosition(1, 100, 0, 0, 0), new Position(84.3762461008662, 45.548650838731845, 1.0000000000000007));
    }

    @Test
    void toStringTest() {
        Assert.assertEquals("[x:0.0, y:0.0, orientation:0.0]", new Position().toString());
        Assert.assertEquals("[x:1.0, y:-1.0, orientation:1.0]", new Position(1, -1, 1).toString());
    }

    @Test
    void vectorialZComposantTest() {
        assertEquals(1, new Position(1, 0).vectorialZComposant(new Position(0,1)));
        assertEquals(-1, new Position(0, 1).vectorialZComposant(new Position(1,0)));
        //TODO others tests
    }

    @Test
    void hasJunctionTest() {
        assertTrue(Position.hasJunction(new Position(), new Position(1,1) , new Position(0,1), new Position(1,0)));
        assertFalse(Position.hasJunction(new Position(), new Position(1,0) , new Position(1,1), new Position(0,1)));
    }
}