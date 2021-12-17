package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LiftSailTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void liftSailTest() throws JsonProcessingException {
        Action liftSail = new LiftSailAction(3);
        String jsonLiftSail = "{\"sailorId\":3,\"type\":\"LIFT_SAIL\"}";
        String jsonLiftSailFalse = "{\"type\":\"LIFT_SAIL\",\"sailorId\":3}";
        assertEquals(jsonLiftSail, om.writeValueAsString(liftSail));
        assertNotEquals(jsonLiftSailFalse, om.writeValueAsString(liftSail));
    }
}