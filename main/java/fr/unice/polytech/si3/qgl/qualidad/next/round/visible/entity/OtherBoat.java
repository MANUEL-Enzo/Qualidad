package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;

public class OtherBoat extends VisibleEntity {
    private final int life;

    @JsonCreator
    public OtherBoat(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape, @JsonProperty("life") int life) {
        super("ship", position, shape);
        this.life = life;
    }

    public int getLife() {
        return life;
    }
}