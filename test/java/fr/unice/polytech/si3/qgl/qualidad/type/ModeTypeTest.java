package fr.unice.polytech.si3.qgl.qualidad.type;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModeTypeTest {
    @Test
    void getTest() {
        assertEquals(ModeType.REGATTA, ModeType.get("REGATTA"));
        assertEquals(ModeType.BATTLE, ModeType.get("BATTLE"));
        assertThrows(IllegalArgumentException.class, () -> ModeType.get("OUI"));
    }

    @Test
    void toStringTest() {
        assertEquals(ModeType.REGATTA.toString(), "REGATTA");
        assertEquals(ModeType.BATTLE.toString(), "BATTLE");
    }
}