package fr.unice.polytech.si3.qgl.qualidad.next.round;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Circle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Deck;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Ship;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Reef;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Stream;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class NextRoundTest {
    @Test
    void getTest() {
        Ship ship = new Ship("ship", 100, new Position(), "shipship", new Deck(1, 1), new Entity[]{}, new Rectangle(1, 1, 0));
        Wind wind = new Wind(0, 0);
        NextRound nextRound = new NextRound(ship, wind, new VisibleEntity[]{});

        assertEquals(ship, nextRound.getShip());
        assertEquals(wind, nextRound.getWind());
        assertEquals(new ArrayList<>(), nextRound.getVisibleEntities());
    }

    @Test
    void getReefsAndStreams() {
        Position p1 = new Position(100,100);
        Circle c1 = new Circle(100);
        NextRound nextRound = new NextRound(null,
                null,
                new VisibleEntity[]{
                        new Stream(p1, c1, 100),
                        new Stream(p1, c1, 100),
                        new Reef(p1,c1),
                        new Reef(p1,c1),
                        new Reef(p1,c1)
                });
        assertEquals(3, nextRound.getReefs().size());
        assertEquals(2, nextRound.getStreams().size());
    }
}