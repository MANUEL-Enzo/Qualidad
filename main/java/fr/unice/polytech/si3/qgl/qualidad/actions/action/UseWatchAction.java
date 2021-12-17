package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UseWatchAction extends Action {
    @JsonCreator
    public UseWatchAction(@JsonProperty("sailorId") int sailorId) {
        super(sailorId, "USE_WATCH");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if(!(obj instanceof UseWatchAction)) return false;

        UseWatchAction other = (UseWatchAction) obj;
        return this.sailorId == other.sailorId;
    }
}