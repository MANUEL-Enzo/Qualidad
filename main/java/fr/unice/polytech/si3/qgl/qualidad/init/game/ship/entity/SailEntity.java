package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SailEntity extends Entity {
    private final boolean openned;

    @JsonCreator
    public SailEntity(@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("openned") boolean openned) {
        super("sail", x, y);
        this.openned = openned;
    }

    public boolean isOpenned() {
        return openned;
    }
}