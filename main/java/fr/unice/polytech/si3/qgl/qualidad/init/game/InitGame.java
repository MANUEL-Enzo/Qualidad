package fr.unice.polytech.si3.qgl.qualidad.init.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.goal.Goal;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Ship;

import java.util.Arrays;
import java.util.List;

public class InitGame {
    private final Goal goal;
    private final List<Sailor> crew;
    private final int shipCount;

    private final Ship ship;

    @JsonCreator
    public InitGame(@JsonProperty("goal") Goal goal, @JsonProperty("ship") Ship ship, @JsonProperty("sailors") Sailor[] sailors, @JsonProperty("shipCount") int shipCount) {
        this.goal = goal;
        this.ship = ship;
        this.crew = Arrays.asList(sailors);
        this.shipCount = shipCount;
    }

    public Goal getGoal() {
        return goal;
    }

    public Ship getShip() {
        return ship;
    }

    public List<Sailor> getSailors() {
        return crew;
    }

    public int getShipCount() {
        return shipCount;
    }
}