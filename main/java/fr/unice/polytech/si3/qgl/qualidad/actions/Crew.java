package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crew {
    private final List<Sailor> sailorsWithSail;
    private final Map<Integer, Sailor> sailorsById;
    private final Map<SideType, List<Sailor>> rowersBySide;
    private int nbRowers;
    private int rudderId;
    private int watchId;

    public Crew(List<Sailor> sailors) {
        rudderId = -1;
        watchId = -1;

        nbRowers = 0;

        sailorsWithSail = new ArrayList<>();

        sailorsById = new HashMap<>();
        for (Sailor sailor : sailors)
            sailorsById.put(sailor.getId(), sailor);

        rowersBySide = Map.of(SideType.PORTSIDE, new ArrayList<>(), SideType.STARBOARD, new ArrayList<>());
    }

    public void giveAssignments(List<Set> sets) {
        int nbSailor = 0;

        for (Sailor sailor : sailorsById.values())
            if (nbSailor < sets.size()) {
                sailor.assignSide(sets.get(nbSailor).getSideType());

                for (Entity entity : sets.get(nbSailor).getEntities())
                    assignEntity(entity, sailor);

                nbSailor++;
            }

        sortRowers();
    }

    void assignEntity(Entity entity, Sailor sailor){
        if (entity.getEntityType().equals(EntityType.OAR)) {
            if (entity.isSide(sailor.getSideType())){
                sailor.assign(EntityType.OAR, entity.getCoordinate());
                nbRowers++;
            } else{
                sailor.assign(EntityType.ALTERNATIVE_OAR, entity.getCoordinate());
            }

            rowersBySide.get(entity.getSideType()).add(sailor);
        } else if (entity.getEntityType().equals(EntityType.RUDDER)) {
            sailor.assign(EntityType.RUDDER, entity.getCoordinate());

            rudderId = sailor.getId();
        } else if (entity.getEntityType().equals(EntityType.SAIL)) {
            sailor.assign(EntityType.SAIL, entity.getCoordinate());

            sailorsWithSail.add(sailor);
        } else if (entity.getEntityType().equals(EntityType.WATCH)) {
            sailor.assign(EntityType.WATCH, entity.getCoordinate());

            watchId = sailor.getId();
        }
    }

    void sortRowers() {
        sortRowersBySide(SideType.PORTSIDE);
        sortRowersBySide(SideType.STARBOARD);
    }

    void sortRowersBySide(SideType sideType) {
        rowersBySide.get(sideType).sort((o1, o2) -> {
            if (o1.getSideType().equals(o2.getSideType()))
                if (o1.hasAssignment(EntityType.ALTERNATIVE_OAR) && o2.hasAssignment(EntityType.ALTERNATIVE_OAR))
                {
                    return 0;
                }
                else if (o1.hasAssignment(EntityType.ALTERNATIVE_OAR))
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            else
            {
                return (o1.getSideType().equals(sideType)) ? -1 : 1;
            }
        });
    }

    public int getNbRowersPortside() {
        return rowersBySide.get(SideType.PORTSIDE).size();
    }

    public int getNbRowersStarboard() {
        return rowersBySide.get(SideType.STARBOARD).size();
    }

    public int getNbTotalRowers() {
        return nbRowers;
    }

    public int getNbSailors(){
        return sailorsById.size();
    }

    public List<Sailor> getSailors() {
        return new ArrayList<>(sailorsById.values());
    }

    public Map<SideType, List<Sailor>> getRowersBySides() {
        return rowersBySide;
    }

    public List<Sailor> getSailorsWithSail() {
        return sailorsWithSail;
    }

    public boolean hasSail() {
        //return sailorsWithSail.size() > 0;
        return false;
    }

    public Map<Integer, Sailor> getSailorById() {
        return sailorsById;
    }

    public Sailor getRudder() {
        return sailorsById.get(rudderId);
    }

    public boolean hasRudder() {
        return rudderId != -1;
    }

    public Sailor getWatcher() {
        return sailorsById.get(watchId);
    }

    public boolean hasWatcher() {
        return watchId != -1;
    }
}