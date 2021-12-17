package fr.unice.polytech.si3.qgl.qualidad.type;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShapeTypeTest {
    @Test
    void getTest() {
        assertEquals(ShapeType.CIRCLE, ShapeType.get("circle"));
        assertEquals(ShapeType.RECTANGLE, ShapeType.get("rectangle"));
        assertEquals(ShapeType.POLYGON, ShapeType.get("polygon"));
        assertThrows(IllegalArgumentException.class, () -> ShapeType.get("oui"));
    }

    @Test
    void toStringTest() {
        assertEquals(ShapeType.CIRCLE.toString(), "circle");
        assertEquals(ShapeType.RECTANGLE.toString(), "rectangle");
        assertEquals(ShapeType.POLYGON.toString(), "polygon");
    }
}