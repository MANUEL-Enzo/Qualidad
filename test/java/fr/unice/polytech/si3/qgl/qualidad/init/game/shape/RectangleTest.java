package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import fr.unice.polytech.si3.qgl.qualidad.type.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    @Test
    void getTest() {
        assertEquals(0, new Rectangle(2, 2, 0).getOrientation(), 0);
        assertEquals(2, new Rectangle(2, 2, 0).getWidth(), 0);
        assertEquals(2, new Rectangle(2, 2, 0).getHeight(), 0);

        assertEquals(4, new Rectangle(2, 2, 0).getVertices().size());

        assertEquals(new Point(1, 1), new Rectangle(2, 2, 0).getVertices().get(0));
        assertEquals(new Point(1, -1), new Rectangle(2, 2, 0).getVertices().get(1));
        assertEquals(new Point(-1, -1), new Rectangle(2, 2, 0).getVertices().get(2));
        assertEquals(new Point(-1, 1), new Rectangle(2, 2, 0).getVertices().get(3));

        assertEquals(ShapeType.RECTANGLE, new Rectangle(2, 2, 0).getType());

        assertEquals(0,new Rectangle(0,0,0).getRadius(),0);
        assertEquals(2.23606797749979,new Rectangle(2,4,0).getRadius(),0);
        assertEquals(2.23606797749979,new Rectangle(4,2,0).getRadius(),0);
    }
}