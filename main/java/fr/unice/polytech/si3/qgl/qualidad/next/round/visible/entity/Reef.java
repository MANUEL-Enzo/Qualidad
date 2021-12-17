package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;

public class Reef extends VisibleEntity {

    @JsonCreator
    public Reef(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape) {
        super("reef", position, shape);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Reef){
            return ((Reef) obj).getPosition().equals(this.getPosition()) && ((Reef) obj).getShape().getType().equals(this.getShape().getType());
        }
        return false;
    }
}