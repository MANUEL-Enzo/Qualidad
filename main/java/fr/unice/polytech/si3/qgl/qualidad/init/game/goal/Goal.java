package fr.unice.polytech.si3.qgl.qualidad.init.game.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.qualidad.type.ModeType;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "mode")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BattleGoal.class, name = "BATTLE"),
        @JsonSubTypes.Type(value = RegattaGoal.class, name = "REGATTA")
})
public abstract class Goal implements Serializable {
    protected final ModeType mode;

    @JsonCreator
    protected Goal(@JsonProperty("mode") String mode) {
        this.mode = ModeType.get(mode);
    }

    public ModeType getMode() {
        return mode;
    }
}