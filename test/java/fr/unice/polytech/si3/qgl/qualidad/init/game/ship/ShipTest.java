package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ShipTest {
    @Test
    void getTest() {
        Deck deck = new Deck(1, 1);
        Shape shape = new Rectangle(1, 1, 0);
        Ship ship = new Ship("ship", 100, new Position(), "shipship", deck, new Entity[]{}, shape);

        assertEquals("ship", ship.getType());
        assertEquals(100, ship.getLife());
        assertEquals(new Position(), ship.getPosition());
        assertEquals("shipship", ship.getName());
        assertEquals(deck, ship.getDeck());
        assertEquals(new ArrayList<>(), ship.getEntities());
        assertEquals(shape, ship.getShape());
    }
}