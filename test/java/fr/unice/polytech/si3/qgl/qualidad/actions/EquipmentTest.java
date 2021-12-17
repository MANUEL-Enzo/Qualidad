package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.*;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    Equipment equipment;
    List<Entity> entities;

    @BeforeEach
    void setUp() {

        entities = new ArrayList<>(Arrays.asList(new OarEntity(0, 0), new OarEntity(0, 2), new RudderEntity(1, 1), new SailEntity(1, 1, true)));
        equipment = new Equipment(entities, 3);
    }

    @Test
    void getTest() {

        assertEquals(4, equipment.getListEntities().size());

        assertEquals(2, equipment.getEntityTypeList(EntityType.OAR).size());
        assertEquals(2, equipment.getEntityTypeSize(EntityType.OAR));

        assertEquals(1, equipment.getEntityTypeList(EntityType.RUDDER).size());
        assertEquals(1, equipment.getEntityTypeSize(EntityType.RUDDER));

        assertEquals(1, equipment.getEntityTypeList(EntityType.SAIL).size());
        assertEquals(1, equipment.getEntityTypeSize(EntityType.SAIL));
    }

   @Test
    void sortSetsTest(){

        SailEntity sail = new SailEntity(1 , 1 , true);
        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(5, 5);

        Set s1 = new Set(SideType.NO_SIDE);
        s1.add(EntityType.OAR , oar1);s1.add(EntityType.OAR , oar2);s1.add(EntityType.OAR , oar3);s1.add(EntityType.OAR , oar4);
        Set s2 = new Set(SideType.NO_SIDE);
        s2.add(EntityType.OAR , oar1);s2.add(EntityType.OAR , oar2);s2.add(EntityType.OAR , oar3);s2.add(EntityType.SAIL, sail);
        Set s3 = new Set(SideType.NO_SIDE);
       s3.add(EntityType.OAR , oar1);s3.add(EntityType.OAR , oar2);s3.add(EntityType.OAR , oar3);
       Set s4 = new Set(SideType.PORTSIDE);
       s4.add(EntityType.OAR , oar1);s4.add(EntityType.OAR , oar2);s4.add(EntityType.OAR , oar3);
       Set s5 = new Set(SideType.PORTSIDE);
       s5.add(EntityType.OAR , oar1);s5.add(EntityType.OAR , oar2);s5.add(EntityType.OAR , oar3);s5.add(EntityType.OAR , oar4);
        Set s6 = new Set(SideType.STARBOARD);
       s6.add(EntityType.OAR , oar1);s6.add(EntityType.OAR , oar2);s6.add(EntityType.OAR , oar3);
       Set s7 = new Set(SideType.PORTSIDE);
       s7.add(EntityType.OAR , oar1);s7.add(EntityType.OAR , oar2);s7.add(EntityType.OAR , oar3);s7.add(EntityType.OAR , oar4);
       Set s8 = new Set(SideType.STARBOARD);
       s8.add(EntityType.SAIL , sail);s8.add(EntityType.OAR , oar2);s8.add(EntityType.OAR , oar3);s8.add(EntityType.OAR , oar4);
       Set s9 = new Set(SideType.PORTSIDE);
      s9.add(EntityType.OAR , oar1);s9.add(EntityType.OAR , oar2);s9.add(EntityType.OAR , oar3);s9.add(EntityType.OAR , oar4);




        List<Entity> entities1 = Arrays.asList(oar1,oar2,oar3,oar4,sail);
        List<Set> sets = Arrays.asList(s1 , s2);
        List<Set> sets1 = Arrays.asList(s1 , s3);
        List<Set> sets2 = Arrays.asList(s4 , s5);
        List<Set> sets3 = Arrays.asList(s6 , s7);
       List<Set> sets4 = Arrays.asList(s8 , s9);



        Equipment e = new Equipment(entities1 , 50);
        e.sortSets(sets);
        e.sortSets(sets1);
        e.sortSets(sets2);
        e.sortSets(sets3);
       e.sortSets(sets4);

        String res = " oar oar oar oar";
        String res1 = " oar oar oar sail";
        String res2 = " oar oar oar";
        String res3 = " oar oar oar oar";
       String res4 = " sail oar oar oar";

        assertEquals(sets.get(0).toString() , res);
        assertEquals(sets.get(1).toString() , res1);
        assertEquals(sets1.get(0).toString() , res2);
        assertEquals(sets1.get(1).toString() , res3);
        assertEquals(sets2.get(0).toString() , res2);
        assertEquals(sets2.get(1).toString() , res3);
       assertEquals(sets3.get(0).toString() , res2);
       assertEquals(sets3.get(1).toString() , res3);
       assertEquals(sets4.get(0).toString() , res4);
       assertEquals(sets4.get(1).toString() , res3);


    }



    @Nested
    class getSets {

        @Test
        void fourSailors() {

            List<Set> sets = equipment.getSets(4);

            assertEquals(4, sets.size());
        }

        @Test
        void twoSailors() {

            List<Set> sets = equipment.getSets(2);

            assertEquals(2, sets.size());
            assertEquals(2, sets.get(0).size());
            assertEquals(2, sets.get(1).size());
        }

        @Test
        void threeSailors() {

            entities = new ArrayList<>(Arrays.asList(new OarEntity(0, 0), new OarEntity(0, 2), new OarEntity(1, 0), new OarEntity(1, 2), new RudderEntity(1, 1), new SailEntity(0, 1, true)));
            equipment = new Equipment(entities, 3);

            List<Set> sets = equipment.getSets(3);

            assertEquals(3, sets.size());
            assertEquals(2, sets.get(0).size());
            assertEquals(2, sets.get(1).size());
            assertEquals(2, sets.get(2).size());
        }

        @Test
        void threeSailorsSailTooFar() {

            entities = new ArrayList<>(Arrays.asList(new OarEntity(0, 0), new OarEntity(0, 2), new OarEntity(1, 0), new OarEntity(1, 2), new RudderEntity(1, 1), new SailEntity(10, 0, true)));
            equipment = new Equipment(entities, 3);

            List<Set> sets = equipment.getSets(3);

            assertEquals(3, sets.size());
            assertEquals(1, sets.get(0).size());
            assertEquals(2, sets.get(1).size());
            assertEquals(2, sets.get(2).size());
        }
    }

    @Test
    void initializeEntitiesSideAssignments(){

        OarEntity oar1 = new OarEntity(2 ,0);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(2 ,2);

        List<Entity> entities =  Arrays.asList(oar1 , oar2,oar3);
        Equipment e = new Equipment(entities , 4);

        assertEquals(oar1.getSideType() , SideType.PORTSIDE);
        assertEquals(oar2.getSideType() , SideType.STARBOARD);
        assertEquals(oar3.getSideType() , SideType.NO_SIDE);


    }

    @Test
    void addSet(){

        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(5, 5);

        List<Entity> entities1 =  Arrays.asList(oar1 , oar2,oar3 , oar4);
        Equipment e = new Equipment(entities1 , 10);

        Set s1 = new Set(SideType.NO_SIDE);
        s1.add(EntityType.OAR , oar1);s1.add(EntityType.OAR , oar2);s1.add(EntityType.OAR , oar3);s1.add(EntityType.OAR , oar4);
        Set s2 = new Set(SideType.NO_SIDE);
        s2.add(EntityType.OAR , oar1);s2.add(EntityType.OAR , oar2);s2.add(EntityType.OAR , oar3);

        List<Set> sets = new ArrayList<Set>();
        sets.add(s1);
        sets.add(s2);

        e.addSet(sets, SideType.NO_SIDE , EntityType.OAR , new OarEntity(6 ,6));

        assertEquals(sets.get(2).toString() , " oar");


    }

    @Test
    void addWactchSet(){

        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(5, 5);

        WatchEntity watch = new WatchEntity(1 , 2);

        List<Entity> entities1 =  Arrays.asList(oar1 , oar2,oar3 , oar4);
        Equipment e = new Equipment(entities1 , 10);
        List<Entity> list = Arrays.asList(watch);

        Set s1 = new Set(SideType.NO_SIDE);


        s1.add(EntityType.OAR , oar1);s1.add(EntityType.OAR , oar2);s1.add(EntityType.OAR , oar3);s1.add(EntityType.OAR , oar4);
        Set s2 = new Set(SideType.NO_SIDE);
        s2.add(EntityType.OAR , oar1);s2.add(EntityType.OAR , oar2);s2.add(EntityType.OAR , oar3);

        List<Set> sets = new ArrayList<Set>();
        sets.add(s1);
        sets.add(s2);


        e.addWatchSets(sets ,list , 5);

        assertEquals(sets.get(2).toString() , " watch" );
    }

    @Test
    void addRudderSet(){

        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(5, 5);
        RudderEntity rudder = new RudderEntity(1 , 2);


        List<Entity> entities1 =  Arrays.asList(oar1 , oar2,oar3 , oar4);
        Equipment e = new Equipment(entities1 , 10);
        List<Entity> list = Arrays.asList(rudder);

        Set s1 = new Set(SideType.NO_SIDE);


        s1.add(EntityType.OAR , oar1);s1.add(EntityType.OAR , oar2);s1.add(EntityType.OAR , oar3);s1.add(EntityType.OAR , oar4);
        Set s2 = new Set(SideType.NO_SIDE);
        s2.add(EntityType.OAR , oar1);s2.add(EntityType.OAR , oar2);s2.add(EntityType.OAR , oar3);

        List<Set> sets1 = new ArrayList<Set>();
        sets1.add(s1);
        sets1.add(s2);


        e.addRuddersSets(sets1 ,list , 5);

        assertEquals(sets1.get(2).toString() , " rudder" );
    }

    @Test
    void addSailSet(){

        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(5, 5);
        SailEntity sail = new SailEntity(1 , 2, false);


        List<Entity> entities1 =  Arrays.asList(oar1 , oar2,oar3 , oar4);
        Equipment e = new Equipment(entities1 , 10);
        List<Entity> list = Arrays.asList(sail);

        Set s1 = new Set(SideType.NO_SIDE);
        Set s2 = new Set(SideType.NO_SIDE);

        s1.add(EntityType.OAR , oar1);
        s1.add(EntityType.OAR , oar2);
        s1.add(EntityType.OAR , oar3);
        s1.add(EntityType.OAR , oar4);

        s2.add(EntityType.OAR , oar1);
        s2.add(EntityType.OAR , oar2);
        s2.add(EntityType.OAR , oar3);

        List<Set> sets1 = new ArrayList<Set>();
        sets1.add(s1);
        sets1.add(s2);


        e.addSailsSets(sets1 ,list , 5);

        assertEquals(sets1.get(2).toString() , " sail" );
    }

    @Test
   void addSailToBestSetTest(){
        OarEntity oar1 = new OarEntity(2 ,2);
        OarEntity oar2 = new OarEntity(3 ,3);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(2, 3);

        OarEntity oar5 = new OarEntity(5, 5);
        OarEntity oar6 = new OarEntity(6, 6);
        OarEntity oar7 = new OarEntity(7, 7);
        OarEntity oar8 = new OarEntity(8, 8);
        OarEntity oar9 = new OarEntity(9, 9);
        OarEntity oar10 = new OarEntity(10 ,  10);
        OarEntity oar11 = new OarEntity(5, 10);

        SailEntity sailEntity = new SailEntity(1 , 2, false);

        List<Entity> entities1 =  Arrays.asList(oar1 , oar2,oar3 , oar4 , oar5 , oar6 , oar7 , oar8 ,
                oar9, oar10, oar11 , sailEntity);

        Equipment e = new Equipment(entities1 , 50);

        Set s1 = new Set(SideType.NO_SIDE);
        Set s2 = new Set(SideType.NO_SIDE);
        Set s3 = new Set(SideType.NO_SIDE);

        s1.add(EntityType.OAR , oar1);
        s1.add(EntityType.OAR , oar2);
        s1.add(EntityType.OAR , oar3);
        s1.add(EntityType.OAR , oar4);

        s2.add(EntityType.OAR , oar5);
        s2.add(EntityType.OAR , oar6);
        s2.add(EntityType.OAR , oar7);
        s2.add(EntityType.OAR , oar7);

        s3.add(EntityType.OAR , oar1);
        s3.add(EntityType.OAR , oar2);
        s3.add(EntityType.OAR , oar3);
        s3.add(EntityType.OAR , oar4);
        s3.add(EntityType.OAR , oar8);

        List<Set> sets1 = new ArrayList<Set>();
        sets1.add(s1);
        sets1.add(s2);
        sets1.add(s3);

        e.addSailToBestSet(sets1 , sailEntity);

        assertTrue(s1.contain(EntityType.SAIL));
        assertFalse(s2.contain(EntityType.SAIL));
        assertFalse(s3.contain(EntityType.SAIL));

    }

    @Test
    void addOneOarTest(){

        OarEntity oar1 = new OarEntity(2 ,0);
        OarEntity oar2 = new OarEntity(3 ,0);
        OarEntity oar3 = new OarEntity(4 ,0);
        OarEntity oar4 = new OarEntity(1, 0);

        oar1.assignSide(SideType.PORTSIDE);
        oar2.assignSide(SideType.PORTSIDE);
        oar3.assignSide(SideType.PORTSIDE);
        oar4.assignSide(SideType.PORTSIDE);

        Set s1 = new Set(SideType.NO_SIDE);

        s1.add(EntityType.OAR , oar1);
        s1.add(EntityType.OAR , oar2);
        s1.add(EntityType.OAR , oar3);
        s1.add(EntityType.OAR , oar4);

        List<Entity> entities1 =  Arrays.asList(oar1 , oar2,oar3 , oar4);

        Equipment e = new Equipment(entities1 , 50);

        ArrayList<Entity> oars = new ArrayList<>();
        oars.add(oar1);
        oars.add(oar2);
        oars.add(oar3);
        oars.add(oar4);

        List<Set> sets1 = new ArrayList<Set>();
        sets1.add(s1);

      assertEquals(e.addOneOarSets(sets1 , oars , 5 , 5) , 4);

    }

    @Test
  void  addTwoOarsSetsTest(){

        OarEntity oar1 = new OarEntity(2 ,0);
        OarEntity oar2 = new OarEntity(3 ,0);
        OarEntity oar3 = new OarEntity(4 ,4);
        OarEntity oar4 = new OarEntity(1, 4);

        List<Entity> entities1 =  Arrays.asList(oar1 , oar2);
        List<Entity> entities2 = Arrays.asList(oar3 , oar4);
        List<Entity> entities3 = Arrays.asList(oar1 , oar2 , oar3 , oar4);

        Equipment e1 = new Equipment(entities2 , 5);
        Equipment e = new Equipment(entities1 , 5);
        Equipment e3 = new Equipment(entities3 , 5);


        List<Set> sets = new ArrayList<>();
        List<Set> sets1 = new ArrayList<>();
        List<Set> sets2 = new ArrayList<>();


        ArrayList<Entity> oars = new ArrayList<>();
        oars.add(oar1);
        oars.add(oar2);

        ArrayList<Entity> oars1 = new ArrayList<>();
        oars1.add(oar3);
        oars1.add(oar4);

        ArrayList<Entity> oars2 = new ArrayList<>();
        oars2.add(oar3);
        oars2.add(oar4);
        oars2.add(oar1);
        oars2.add(oar2);


        e.addTwoOarsSets(sets , oars , 10 , 5 , 2 );
        e1.addTwoOarsSets(sets1 , oars1 , 10 , 5 , 2 );
        e3.addTwoOarsSets(sets2 , oars2 ,10 , 5  , 2);

        assertTrue(sets.get(0).contain(EntityType.OAR));
        assertEquals(sets.get(0).size()  , 1);

        assertTrue(sets1.get(0).contain(EntityType.ALTERNATIVE_OAR));
        assertEquals(sets1.get(0).size()  , 1);

        assertEquals(oars.size() , 0);
        assertEquals(oars1.size() , 0);

        assertEquals(sets2.get(0).size() , 2);
        assertTrue(sets2.get(0).contain(EntityType.OAR));
        assertTrue(sets2.get(0).contain(EntityType.ALTERNATIVE_OAR));

    }

}