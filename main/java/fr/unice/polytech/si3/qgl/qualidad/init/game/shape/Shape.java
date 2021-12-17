package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import com.fasterxml.jackson.annotation.*;
import fr.unice.polytech.si3.qgl.qualidad.type.ShapeType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Polygon.class, name = "polygon")
})
public abstract class Shape {
    protected final ShapeType type;

    @JsonCreator
    protected Shape(@JsonProperty("type") String type) {
        this.type = ShapeType.get(type);
    }

    @JsonIgnore
    public ShapeType getType() {
        return type;
    }

    public abstract double getRadius();
}
