package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AimAction extends Action {
    private final double angle;

    @JsonCreator
    public AimAction(@JsonProperty("sailorId") int sailorId, @JsonProperty("angle") double angle) {
        super(sailorId, "AIM");
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }
}