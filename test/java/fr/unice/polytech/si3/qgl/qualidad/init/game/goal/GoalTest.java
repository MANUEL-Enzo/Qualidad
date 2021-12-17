package fr.unice.polytech.si3.qgl.qualidad.init.game.goal;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class GoalTest {
    @Test
    void getTest() {
        assertEquals(0, new RegattaGoal(new Checkpoint[]{}).getCheckpoints().size());
    }
}