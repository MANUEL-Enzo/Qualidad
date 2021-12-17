package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AimTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void aimTest() throws JsonProcessingException {
        Action aim = new AimAction(3, 15);
        String jsonAim = "{\"sailorId\":3,\"angle\":15.0,\"type\":\"AIM\"}";
        String jsonAimFalse = "{\"sailorId\":3,\"angle\":15,\"type\":\"AIM\"}";
        assertEquals(jsonAim, om.writeValueAsString(aim));
        assertNotEquals(jsonAimFalse, om.writeValueAsString(aim));
    }
}