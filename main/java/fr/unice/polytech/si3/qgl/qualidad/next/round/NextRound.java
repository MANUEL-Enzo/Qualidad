package fr.unice.polytech.si3.qgl.qualidad.next.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Ship;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Reef;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Stream;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextRound {
    private final Ship ship;
    private final Wind wind;
    private final List<VisibleEntity> visibleEntities;

    @JsonCreator
    public NextRound(@JsonProperty("ship") Ship ship, @JsonProperty("wind") Wind wind, @JsonProperty("visibleEntities") VisibleEntity[] visibleEntities) {
        this.ship = ship;
        this.wind = wind;
        this.visibleEntities = new ArrayList<>(Arrays.asList(visibleEntities));
    }

    public Ship getShip() {
        return ship;
    }

    public Wind getWind() {
        return wind;
    }

    public List<VisibleEntity> getVisibleEntities() {
        return visibleEntities;
    }

    public List<Reef> getReefs(){
        List<Reef> reefs = new ArrayList<>();
        for (VisibleEntity e : visibleEntities){
            if (e instanceof Reef) reefs.add((Reef)e);
        }
        return reefs;
    }

    public List<Stream> getStreams(){
        List<Stream> streams = new ArrayList<>();
        for (VisibleEntity e : visibleEntities){
            if (e instanceof Stream) streams.add((Stream)e);
        }
        return streams;
    }
}