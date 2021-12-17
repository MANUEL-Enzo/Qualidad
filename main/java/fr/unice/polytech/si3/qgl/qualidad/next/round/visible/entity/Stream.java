package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;

public class Stream extends VisibleEntity {
    private final double strength;

    @JsonCreator
    public Stream(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape, @JsonProperty("strength") double strength) {
        super("stream", position, shape);
        this.strength = strength;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Stream){
            return ((Stream) obj).getPosition().equals(this.getPosition()) && ((Stream) obj).getShape().getType().equals(this.getShape().getType()) && ((Stream) obj).getStrength() == this.getStrength();
        }
        return false;
    }
}