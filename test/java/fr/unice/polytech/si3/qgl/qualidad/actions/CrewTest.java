package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Coordinate;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.OarEntity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.RudderEntity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.SailEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class CrewTest {
    Crew crew;

    @BeforeEach
    void setUp() {
        crew = new Crew(Arrays.asList(new Sailor(0, 0, 0, "s"),
                new Sailor(1, 0, 0, "s"),
                new Sailor(2, 0, 0, "s"),
                new Sailor(3, 0, 0, "s")
        ));
    }

    @Test
    void sortRowersTest() {
        Sailor sailor1 = new Sailor(0, 0, 0, "s1");
        Sailor sailor2 = new Sailor(1, 0, 0, "s2");
        Sailor sailor3 = new Sailor(2, 0, 0, "s3");
        Sailor sailor4 = new Sailor(3, 0, 0, "s4");
        Sailor sailor5 = new Sailor(4, 0, 0, "s5");

        sailor1.assignSide(SideType.PORTSIDE);
        sailor2.assignSide(SideType.PORTSIDE);
        sailor3.assignSide(SideType.PORTSIDE);
        sailor4.assignSide(SideType.STARBOARD);
        sailor5.assignSide(SideType.STARBOARD);

        sailor3.assign(EntityType.ALTERNATIVE_OAR, new Coordinate());
        sailor4.assign(EntityType.ALTERNATIVE_OAR, new Coordinate());
        sailor5.assign(EntityType.ALTERNATIVE_OAR, new Coordinate());

        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor4);
        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor2);
        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor1);
        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor5);
        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor3);

        assertEquals(3, crew.getRowersBySides().get(SideType.PORTSIDE).get(0).getId());
        assertEquals(1, crew.getRowersBySides().get(SideType.PORTSIDE).get(1).getId());
        assertEquals(0, crew.getRowersBySides().get(SideType.PORTSIDE).get(2).getId());
        assertEquals(4, crew.getRowersBySides().get(SideType.PORTSIDE).get(3).getId());
        assertEquals(2, crew.getRowersBySides().get(SideType.PORTSIDE).get(4).getId());

        crew.sortRowersBySide(SideType.PORTSIDE);

        assertEquals(0, crew.getRowersBySides().get(SideType.PORTSIDE).get(0).getId());
        assertEquals(1, crew.getRowersBySides().get(SideType.PORTSIDE).get(1).getId());
        assertEquals(2, crew.getRowersBySides().get(SideType.PORTSIDE).get(2).getId());
        assertEquals(3, crew.getRowersBySides().get(SideType.PORTSIDE).get(3).getId());
        assertEquals(4, crew.getRowersBySides().get(SideType.PORTSIDE).get(4).getId());
    }

    @Test
    void sortRowersBySideTest() {
        Sailor sailor1 = new Sailor(0, 0, 0, "s1");
        Sailor sailor2 = new Sailor(1, 0, 0, "s2");

        sailor1.assignSide(SideType.PORTSIDE);
        sailor2.assignSide(SideType.STARBOARD);

        crew.getRowersBySides().get(SideType.STARBOARD).add(sailor1);
        crew.getRowersBySides().get(SideType.STARBOARD).add(sailor2);

        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor2);
        crew.getRowersBySides().get(SideType.PORTSIDE).add(sailor1);

        assertEquals(0, crew.getRowersBySides().get(SideType.STARBOARD).get(0).getId());
        assertEquals(1, crew.getRowersBySides().get(SideType.STARBOARD).get(1).getId());

        assertEquals(1, crew.getRowersBySides().get(SideType.PORTSIDE).get(0).getId());
        assertEquals(0, crew.getRowersBySides().get(SideType.PORTSIDE).get(1).getId());

        crew.sortRowers();

        assertEquals(1, crew.getRowersBySides().get(SideType.STARBOARD).get(0).getId());
        assertEquals(0, crew.getRowersBySides().get(SideType.STARBOARD).get(1).getId());

        assertEquals(0, crew.getRowersBySides().get(SideType.PORTSIDE).get(0).getId());
        assertEquals(1, crew.getRowersBySides().get(SideType.PORTSIDE).get(1).getId());
    }

    @Test
    void getTest() {
        assertEquals(0, crew.getNbRowersPortside());
        assertEquals(0, crew.getRowersBySides().get(SideType.PORTSIDE).size());
        assertEquals(0, crew.getNbRowersStarboard());
        assertEquals(0, crew.getRowersBySides().get(SideType.STARBOARD).size());

        assertEquals(4, crew.getNbSailors());
        assertEquals(4, crew.getSailors().size());
        assertEquals(4, crew.getSailorById().size());

        assertEquals(false, crew.hasSail());
        assertEquals(0, crew.getSailorsWithSail().size());

        assertEquals(false, crew.hasRudder());
        assertNull(crew.getRudder());
    }

    @Nested
    class giveAssignmentsTest {
        List<Set> sets;
        Set set;

        @BeforeEach
        void setUp() {
            sets = new ArrayList<>();
            set = new Set(SideType.PORTSIDE);
        }

        @Test
        void oarSet() {
            Entity oar1 = new OarEntity(0, 0);
            Entity oar2 = new OarEntity(0, 0);

            oar1.assignSide(SideType.PORTSIDE);
            oar2.assignSide(SideType.STARBOARD);

            set.add(EntityType.OAR, oar1);
            set.add(EntityType.ALTERNATIVE_OAR, oar2);

            sets.add(set);

            crew.giveAssignments(sets);

            assertEquals(1, crew.getNbRowersPortside());
            assertEquals(1, crew.getNbRowersStarboard());
        }

        @Test
        void rudderSet() {
            Entity rudder = new RudderEntity(0, 0);

            set.add(EntityType.RUDDER, rudder);

            sets.add(set);

            crew.giveAssignments(sets);

            assertEquals(true, crew.hasRudder());
        }

        @Test
        void sailSet() {
            Entity sail = new SailEntity(0, 0, true);

            set.add(EntityType.SAIL, sail);

            sets.add(set);

            crew.giveAssignments(sets);

            assertEquals(1, crew.getSailorsWithSail().size());
        }
    }
}