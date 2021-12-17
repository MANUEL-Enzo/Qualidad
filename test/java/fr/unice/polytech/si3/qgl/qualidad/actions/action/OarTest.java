package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OarTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void oarTest() throws JsonProcessingException {
        Action oar = new OarAction(3);
        String jsonOar = "{\"sailorId\":3,\"type\":\"OAR\"}";
        String jsonOarFalse = "{\"type\":\"OAR\",\"sailorId\":3}";
        assertEquals(jsonOar, om.writeValueAsString(oar));
        assertNotEquals(jsonOarFalse, om.writeValueAsString(oar));
    }
}