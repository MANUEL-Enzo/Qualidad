package fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OarEntity extends Entity {

    @JsonCreator
    public OarEntity(@JsonProperty("x") int x, @JsonProperty("y") int y) {
        super("oar", x, y);
    }
}