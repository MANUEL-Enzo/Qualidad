package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LowerSailTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void lowerSailTest() throws JsonProcessingException {
        Action lowerSail = new LowerSailAction(3);
        String jsonLowerSail = "{\"sailorId\":3,\"type\":\"LOWER_SAIL\"}";
        String jsonLowerSailFalse = "{\"type\":\"LOWER_SAIL\",\"sailorId\":3}";
        assertEquals(jsonLowerSail, om.writeValueAsString(lowerSail));
        assertNotEquals(jsonLowerSailFalse, om.writeValueAsString(lowerSail));
    }
}