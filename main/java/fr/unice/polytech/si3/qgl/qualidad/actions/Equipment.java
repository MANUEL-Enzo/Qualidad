package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.OarEntity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.SailEntity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.WatchEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

import java.util.*;

public class Equipment {
    private final List<Entity> listEntities;
    private final Map<EntityType, List<Entity>> entityTypeListMap;

    public Equipment(List<Entity> entities, int width) {
        listEntities = entities;
        listEntities.sort((e1, e2) -> {
            if (e1.getCoordinate().getX() == e2.getCoordinate().getX())
                if (e1.getCoordinate().getY() == e2.getCoordinate().getY())
                    return 0;
                else
                    return (e1.getCoordinate().getY() < e2.getCoordinate().getY()) ? -1 : 1;
            else
                return (e1.getCoordinate().getX() < e2.getCoordinate().getX()) ? -1 : 1;
        });
        entityTypeListMap = new HashMap<>();
        initializeEntitiesSideAssignments(width);
        initializeEntityTypeListMap();
    }

    void initializeEntitiesSideAssignments(int width) {
        for (Entity entity : listEntities)
            if (entity.getCoordinate().getY() == 0)
                entity.assignSide(SideType.PORTSIDE);
            else if (entity.getCoordinate().getY() == width - 1)
                entity.assignSide(SideType.STARBOARD);
            else
                entity.assignSide(SideType.NO_SIDE);
    }

    void initializeEntityTypeListMap() {
        for (Entity entity : listEntities)
            if (!entityTypeListMap.containsKey(entity.getEntityType()))
                entityTypeListMap.put(entity.getEntityType(), new ArrayList<>(Collections.singletonList(entity)));
            else
                entityTypeListMap.get(entity.getEntityType()).add(entity);
    }

    List<Set> getSets(int nbSailors) {
        List<Set> sets = new ArrayList<>();

        if (entityTypeListMap.containsKey(EntityType.WATCH))
            addWatchSets(sets, entityTypeListMap.get(EntityType.WATCH), nbSailors);

        if (entityTypeListMap.containsKey(EntityType.RUDDER))
            addRuddersSets(sets, entityTypeListMap.get(EntityType.RUDDER), nbSailors);

        if (entityTypeListMap.containsKey(EntityType.OAR))
            addOarsSets(sets, entityTypeListMap.get(EntityType.OAR), nbSailors);

        if (entityTypeListMap.containsKey(EntityType.SAIL))
            addSailsSets(sets, entityTypeListMap.get(EntityType.SAIL), nbSailors);

        return sets;
    }

    void addWatchSets(List<Set> sets, List<Entity> watch, int nbSailors) {
        if (sets.size() < nbSailors)
            addSet(sets, SideType.NO_SIDE, EntityType.WATCH, watch.get(0));
    }

    void addRuddersSets(List<Set> sets, List<Entity> rudders, int nbSailors) {
        if (sets.size() < nbSailors)
            addSet(sets, SideType.NO_SIDE, EntityType.RUDDER, rudders.get(0));
    }

    void addOarsSets(List<Set> sets, List<Entity> oarList, int nbSailors) {
        List<Entity> oars = new ArrayList<>(oarList);

        int nbSailorsWithOneOar = 2 * (nbSailors - sets.size()) - oars.size();
        int nbMaxSailorsPort = (nbSailors - sets.size()) / 2;
        int nbSailorsPort = addOneOarSets(sets, oars, nbSailors, nbSailorsWithOneOar);

        addTwoOarsSets(sets, oars, nbSailors, nbMaxSailorsPort, nbSailorsPort);

        sortSets(sets);
    }

    int addOneOarSets(List<Set> sets, List<Entity> oars, int nbSailors, int nbRowersWithOneOar) {
        int nbSailorsPort = 0;

        for (Set set : sets)
            if (set.get(0).getEntityType().equals(EntityType.WATCH)){
                set.add(EntityType.OAR,oars.get(0));

                if (oars.get(0).getSideType().equals(SideType.PORTSIDE))
                    nbSailorsPort++;

                set.setSideType(oars.get(0).getSideType());

                oars.remove(oars.get(0));
                nbRowersWithOneOar--;
            }

        for (int i = 0; sets.size() < nbSailors && oars.size() > 0 && i < nbRowersWithOneOar; i++) {
            Entity oar = oars.get(0);

            if (oar.getSideType().equals(SideType.PORTSIDE))
                nbSailorsPort++;

            addSet(sets, oar.getSideType(), EntityType.OAR, oar);

            oars.remove(oar);
        }

        return nbSailorsPort;
    }

    void addTwoOarsSets(List<Set> sets, List<Entity> oars, int nbSailors, int nbMaxSailorsPort, int nbSailorsPort) {
        while (sets.size() < nbSailors && oars.size() > 0) {
            Set set;

            if (nbSailorsPort < nbMaxSailorsPort) {
                set = new Set(SideType.PORTSIDE);
                nbSailorsPort++;
            } else
                set = new Set(SideType.STARBOARD);

            for (Entity oar : oars)
                if (set.contain(EntityType.OAR) && set.contain(EntityType.ALTERNATIVE_OAR))
                    break;
                else if (!set.contain(EntityType.OAR) && oar.getSideType().equals(SideType.PORTSIDE))
                    set.add(EntityType.OAR, oar);
                else if (!set.contain(EntityType.ALTERNATIVE_OAR) && oar.getSideType().equals(SideType.STARBOARD))
                    set.add(EntityType.ALTERNATIVE_OAR, oar);

            sets.add(set);
            oars.removeAll(set.getEntities());
        }
    }

    void addSailsSets(List<Set> sets, List<Entity> sails, int nbSailors) {
        for (Entity sail : sails)
            if (sets.size() < nbSailors)
            {
                addSet(sets, SideType.NO_SIDE, EntityType.SAIL, sail);
            }
            else
            {
                addSailToBestSet(sets, sail);
            }
    }

    void addSailToBestSet(List<Set> sets, Entity sail) {
        for (Set set : sets) {
            int nbEntityInOneTurn = 0;

            for (Entity entity : set.getEntities())
                if (sail.getCoordinate().minus(entity.getCoordinate()).getNorm() <= 5 && !entity.isEntity(EntityType.SAIL))
                    nbEntityInOneTurn++;

            if (nbEntityInOneTurn == set.size()) {
                set.add(EntityType.SAIL, sail);
                return;
            }
        }
    }

    void addSet(List<Set> sets, SideType sideType, EntityType entityType, Entity entity) {
        Set set = new Set(sideType);
        set.add(entityType, entity);
        sets.add(set);
    }

    void sortSets(List<Set> sets) {
        sets.sort((s1, s2) -> {
            if (s1.size() == s2.size())
            {
                if (s1.get(0).getEntityType().equals(s2.get(0).getEntityType()))
                {
                    return 0;
                }
                else
                {
                    return (s1.get(0).getEntityType().equals(EntityType.OAR)) ? 1 : -1;
                }
            }
            else
            {
                return (s1.size() > s2.size()) ? 1 : -1;
            }
        });

    }

    public List<Entity> getListEntities() {
        return listEntities;
    }

    public List<Entity> getEntityTypeList(EntityType entityType) {
        return entityTypeListMap.get(entityType);
    }

    public int getEntityTypeSize(EntityType entityType) {
        return getEntityTypeList(entityType).size();
    }




}