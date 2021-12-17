package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import fr.unice.polytech.si3.qgl.qualidad.type.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CircleTest {
    @Test
    void getTest() {
        assertEquals(1, new Circle(1).getRadius(), 0);
        assertEquals(ShapeType.CIRCLE, new Circle(1).getType());
    }
}