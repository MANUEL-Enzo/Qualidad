package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CanonEntity extends Entity {
    private final boolean loaded;
    private final double angle;

    @JsonCreator
    public CanonEntity(@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("loaded") boolean loaded, @JsonProperty("angle") double angle) {
        super("canon", x, y);
        this.loaded = loaded;
        this.angle = angle;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public double getAngle() {
        return angle;
    }
}