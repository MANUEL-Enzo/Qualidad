package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.type.VisibleEntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class StreamTest {
    @Test
    void getTest() {
        Stream stream = new Stream(new Position(), new Rectangle(1, 1, 0), 100);

        assertEquals(100, stream.getStrength(), 0);
        assertEquals(VisibleEntityType.STREAM, stream.getType());
    }
}