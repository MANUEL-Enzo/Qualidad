package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OarAction extends Action {
    @JsonCreator
    public OarAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId, "OAR");
    }
}