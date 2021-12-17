package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TurnTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void TurnTest() throws JsonProcessingException {
        Action turn = new TurnAction(3, 20.0);
        String jsonTurn = "{\"sailorId\":3,\"rotation\":20.0,\"type\":\"TURN\"}";
        String jsonTurnFalse = "{\"rotation\":20.0,\"sailorId\":3,\"type\":\"TURN\"}";
        assertEquals(jsonTurn, om.writeValueAsString(turn));
        assertNotEquals(jsonTurnFalse, om.writeValueAsString(turn));
    }
}