package fr.unice.polytech.si3.qgl.qualidad.init.game.goal;

import fr.unice.polytech.si3.qgl.qualidad.type.ModeType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class BattleGoalTest {
    @Test
    void getTest() {
        assertEquals(ModeType.BATTLE, new BattleGoal().getMode());
    }
}