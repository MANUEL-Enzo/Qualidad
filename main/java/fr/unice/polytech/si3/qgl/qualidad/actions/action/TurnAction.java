package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TurnAction extends Action {
    private final double rotation;

    @JsonCreator
    public TurnAction(@JsonProperty("sailorId") int sailorId, @JsonProperty("rotation") double rotation) {
        super(sailorId, "TURN");
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }
}