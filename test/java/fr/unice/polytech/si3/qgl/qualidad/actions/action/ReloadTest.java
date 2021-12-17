package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReloadTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void ReloadTest() throws JsonProcessingException {
        Action reload = new ReloadAction(3);
        String jsonReload = "{\"sailorId\":3,\"type\":\"RELOAD\"}";
        String jsonReloadFalse = "{\"type\":\"RELOAD\",\"sailorId\":3}";
        assertEquals(jsonReload, om.writeValueAsString(reload));
        assertNotEquals(jsonReloadFalse, om.writeValueAsString(reload));
    }
}