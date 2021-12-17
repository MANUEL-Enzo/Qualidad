package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.OarEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetTest {
    @Test
    void addTest() {
        Entity entity = new OarEntity(0, 0);
        Set set = new Set(SideType.NO_SIDE);

        set.add(EntityType.OAR, entity);

        assertEquals(1, set.size());
        assertEquals(entity, set.get(0));
        assertTrue(set.contain(EntityType.OAR));
    }

    @Test
    void getTest() {
        assertEquals(SideType.NOT_ASSIGNED, new Set(SideType.NOT_ASSIGNED).getSideType());
        assertEquals(SideType.STARBOARD, new Set(SideType.STARBOARD).getSideType());
        assertEquals(SideType.PORTSIDE, new Set(SideType.PORTSIDE).getSideType());
        assertEquals(SideType.NO_SIDE, new Set(SideType.NO_SIDE).getSideType());

        assertEquals(new ArrayList<>(), new Set(SideType.NO_SIDE).getEntities());

        assertEquals(0, new Set(SideType.NO_SIDE).size());
    }

    @Test
    void toStringTest(){
        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(5, 5);

        Set s1 = new Set(SideType.NO_SIDE);
        s1.add(EntityType.OAR , oar1);s1.add(EntityType.OAR , oar2);s1.add(EntityType.OAR , oar3);s1.add(EntityType.OAR , oar4);
        String s = " oar oar oar oar";
        assertEquals(s , s1.toString());
    }
}