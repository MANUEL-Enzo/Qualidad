package fr.unice.polytech.si3.qgl.qualidad.next.round;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
    private final double orientation;
    private final double strength;

    @JsonCreator
    public Wind(@JsonProperty("orientation") double orientation, @JsonProperty("strength") double strength) {
        this.orientation = orientation;
        this.strength = strength;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getStrength() {
        return strength;
    }
}