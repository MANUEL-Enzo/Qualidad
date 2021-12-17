package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LowerSailAction extends Action {
    @JsonCreator
    public LowerSailAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId, "LOWER_SAIL");
    }
}