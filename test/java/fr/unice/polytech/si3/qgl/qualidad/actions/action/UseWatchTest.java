package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UseWatchTest {
    ObjectMapper om;

    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
    }

    @Test
    public void UseWatchTest() throws JsonProcessingException {
        Action usewatch = new UseWatchAction(3);
        String jsonReload = "{\"sailorId\":3,\"type\":\"USE_WATCH\"}";
        String jsonReloadFalse = "{\"type\":\"USE_WATCH\",\"sailorId\":3}";
        assertEquals(jsonReload, om.writeValueAsString(usewatch));
        assertNotEquals(jsonReloadFalse, om.writeValueAsString(usewatch));
    }
}