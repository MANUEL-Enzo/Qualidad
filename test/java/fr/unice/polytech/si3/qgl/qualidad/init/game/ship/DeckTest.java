package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {
    @Test
    void getTest() {
        Deck deck = new Deck(1, 2);

        assertEquals(1, deck.getWidth());
        assertEquals(2, deck.getLength());
    }
}