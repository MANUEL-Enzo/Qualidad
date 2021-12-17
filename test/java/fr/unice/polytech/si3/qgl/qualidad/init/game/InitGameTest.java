package fr.unice.polytech.si3.qgl.qualidad.init.game;

import fr.unice.polytech.si3.qgl.qualidad.init.game.goal.BattleGoal;
import fr.unice.polytech.si3.qgl.qualidad.init.game.goal.Goal;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Deck;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Ship;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class InitGameTest {
    @Test
    void getTest() {
        Goal goal = new BattleGoal();
        Ship ship = new Ship("ship", 100, new Position(), "shipship", new Deck(1, 1), new Entity[]{}, new Rectangle(1, 1, 0));
        Sailor[] sailors = new Sailor[]{};
        InitGame initGame = new InitGame(goal, ship, sailors, 0);

        assertEquals(goal, initGame.getGoal());
        assertEquals(ship, initGame.getShip());
        assertEquals(0, initGame.getSailors().size());
        assertEquals(0, initGame.getShipCount());
    }
}