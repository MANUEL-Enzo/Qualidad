package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FireAction extends Action {
    @JsonCreator
    public FireAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId, "FIRE");
    }
}