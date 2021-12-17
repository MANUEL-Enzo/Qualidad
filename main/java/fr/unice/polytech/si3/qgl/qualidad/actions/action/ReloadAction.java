package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReloadAction extends Action {
    @JsonCreator
    public ReloadAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId, "RELOAD");
    }
}