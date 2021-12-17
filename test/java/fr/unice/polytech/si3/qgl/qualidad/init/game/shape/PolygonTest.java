package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import fr.unice.polytech.si3.qgl.qualidad.type.ShapeType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PolygonTest {
    @Test
    void getTest() {
        assertEquals(0, new Polygon(0, new ArrayList<>()).getOrientation(), 0);

        assertEquals(new ArrayList<>(), new Polygon(0, new Point[]{}).getVertices());
        assertEquals(new ArrayList<>(), new Polygon(0, new ArrayList<>()).getVertices());

        assertEquals(ShapeType.POLYGON, new Polygon(0, new ArrayList<>()).getType());

        assertEquals(0,new Polygon(0, new ArrayList<>()).getRadius(),0);
        assertEquals(1.4142135623730951,new Polygon(0, Arrays.asList(new Point(0,1), new Point(1,-1), new Point(-1,-1))).getRadius(),0);
        assertEquals(2,new Polygon(0, Arrays.asList(new Point(1,1), new Point(0,2), new Point(1,-1), new Point(-1,-1), new Point(-1,1))).getRadius(),0);
    }
}