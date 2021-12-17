package fr.unice.polytech.si3.qgl.qualidad.type;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionsTypeTest {
    @Test
    void getTest() {
        assertEquals(ActionType.MOVING, ActionType.get("MOVING"));
        assertEquals(ActionType.LIFT_SAIL, ActionType.get("LIFT_SAIL"));
        assertEquals(ActionType.LOWER_SAIL, ActionType.get("LOWER_SAIL"));
        assertEquals(ActionType.TURN, ActionType.get("TURN"));
        assertEquals(ActionType.OAR, ActionType.get("OAR"));
        assertEquals(ActionType.USE_WATCH, ActionType.get("USE_WATCH"));
        assertEquals(ActionType.AIM, ActionType.get("AIM"));
        assertEquals(ActionType.FIRE, ActionType.get("FIRE"));
        assertEquals(ActionType.RELOAD, ActionType.get("RELOAD"));
        assertThrows(IllegalArgumentException.class, () -> ActionType.get("OUI"));
    }

    @Test
    void toStringTest() {
        assertEquals(ActionType.MOVING.toString(), "MOVING");
        assertEquals(ActionType.LIFT_SAIL.toString(), "LIFT_SAIL");
        assertEquals(ActionType.LOWER_SAIL.toString(), "LOWER_SAIL");
        assertEquals(ActionType.TURN.toString(), "TURN");
        assertEquals(ActionType.OAR.toString(), "OAR");
        assertEquals(ActionType.USE_WATCH.toString(), "USE_WATCH");
        assertEquals(ActionType.AIM.toString(), "AIM");
        assertEquals(ActionType.FIRE.toString(), "FIRE");
        assertEquals(ActionType.RELOAD.toString(), "RELOAD");
    }
}