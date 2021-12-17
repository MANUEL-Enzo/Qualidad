package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.type.VisibleEntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ReefTest {
    @Test
    void getTest() {
        Reef reef = new Reef(new Position(), new Rectangle(1, 1, 0));

        assertEquals(VisibleEntityType.REEF, reef.getType());
    }
}