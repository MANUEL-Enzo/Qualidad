package fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.type.VisibleEntityType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OtherBoat.class, name = "ship"),
        @JsonSubTypes.Type(value = Reef.class, name = "reef"),
        @JsonSubTypes.Type(value = Stream.class, name = "stream")
})
public abstract class VisibleEntity {
    protected final VisibleEntityType type;
    protected final Position position;
    protected final Shape shape;

    @JsonCreator
    protected VisibleEntity(@JsonProperty("type") String type, @JsonProperty("position") Position position, @JsonProperty("shape") Shape shape) {
        this.type = VisibleEntityType.get(type);
        this.position = position;
        this.shape = shape;
    }

    public VisibleEntityType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }
}