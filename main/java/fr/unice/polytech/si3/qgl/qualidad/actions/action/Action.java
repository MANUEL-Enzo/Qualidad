package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.qualidad.type.ActionType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NONE,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MovingAction.class, name = "MOVING"),
        @JsonSubTypes.Type(value = LiftSailAction.class, name = "LIFT_SAIL"),
        @JsonSubTypes.Type(value = LowerSailAction.class, name = "LOWER_SAIL"),
        @JsonSubTypes.Type(value = TurnAction.class, name = "TURN"),
        @JsonSubTypes.Type(value = OarAction.class, name = "OAR"),
        @JsonSubTypes.Type(value = UseWatchAction.class, name = "USE_WATCH"),
        @JsonSubTypes.Type(value = AimAction.class, name = "AIM"),
        @JsonSubTypes.Type(value = FireAction.class, name = "FIRE"),
        @JsonSubTypes.Type(value = ReloadAction.class, name = "RELOAD")
})
public abstract class Action {
    protected final int sailorId;
    protected final ActionType type;

    @JsonCreator
    protected Action(@JsonProperty("sailorId") int sailorId, @JsonProperty("type") String type) {
        this.sailorId = sailorId;
        this.type = ActionType.get(type);
    }

    public int getSailorId() {
        return sailorId;
    }

    public ActionType getType() {
        return type;
    }
}