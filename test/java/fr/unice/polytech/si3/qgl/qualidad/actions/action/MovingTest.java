package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MovingTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void movingTest() throws JsonProcessingException {
        Action moving = new MovingAction(3, 4, 2);
        String jsonMoving = "{\"sailorId\":3,\"xdistance\":4,\"ydistance\":2,\"type\":\"MOVING\"}";
        String jsonMovingFalse = "{\"sailorId\":3,\"ydistance\":2,\"xdistance\":4,\"type\":\"MOVING\"}";
        assertEquals(jsonMoving, om.writeValueAsString(moving));
        assertNotEquals(jsonMovingFalse, om.writeValueAsString(moving));
    }
}