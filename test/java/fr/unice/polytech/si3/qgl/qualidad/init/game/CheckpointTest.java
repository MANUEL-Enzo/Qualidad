package fr.unice.polytech.si3.qgl.qualidad.init.game;

import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CheckpointTest {
    @Test
    void getTest() {
        Position position = new Position();
        Shape shape = new Rectangle(1, 1, 0);
        Checkpoint checkpoint = new Checkpoint(position, shape);

        assertEquals(position, checkpoint.getPosition());
        assertEquals(shape, checkpoint.getShape());
    }

    @Test
    void testEquals() {
        Position p = new Position(0,0);
        Rectangle r = new Rectangle(100,100,0);
        Checkpoint c1 = new Checkpoint(p,r);
        Checkpoint c2 = new Checkpoint(p,r);
        assertEquals(c1,c2);
        assertNotEquals(c1, null);
        assertNotEquals(c1, new Object());
    }
}