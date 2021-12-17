package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LiftSailAction extends Action {
    @JsonCreator
    public LiftSailAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId, "LIFT_SAIL");
    }
}