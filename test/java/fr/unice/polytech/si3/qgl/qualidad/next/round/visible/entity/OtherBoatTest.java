package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.type.VisibleEntityType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class OtherBoatTest {
    @Test
    void getTest() {
        OtherBoat otherBoat = new OtherBoat(new Position(), new Rectangle(1, 1, 0), 100);

        assertEquals(100, otherBoat.getLife());
        assertEquals(VisibleEntityType.SHIP, otherBoat.getType());
    }
}