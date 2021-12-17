package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.type.VisibleEntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class VisibleEntityTest {
    @Test
    void getTest() {
        Position position = new Position();
        Shape shape = new Rectangle(1, 1, 0);
        VisibleEntity visibleEntity = new Reef(position, shape);

        assertEquals(VisibleEntityType.REEF, visibleEntity.getType());
        assertEquals(position, visibleEntity.getPosition());
        assertEquals(shape, visibleEntity.getShape());
    }
}