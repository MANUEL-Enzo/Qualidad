package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FireTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void fireTest() throws JsonProcessingException {
        Action fire = new FireAction(3);
        String jsonFire = "{\"sailorId\":3,\"type\":\"FIRE\"}";
        String jsonFireFalse = "{\"type\":\"FIRE\",\"sailorId\":3}";
        assertEquals(jsonFire, om.writeValueAsString(fire));
        assertNotEquals(jsonFireFalse, om.writeValueAsString(fire));
    }
}