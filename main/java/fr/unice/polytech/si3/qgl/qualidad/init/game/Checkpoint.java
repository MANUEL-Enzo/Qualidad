package fr.unice.polytech.si3.qgl.qualidad.init.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;

import java.util.Objects;

public class Checkpoint {
    private final Position position;
    private final Shape shape;

    @JsonCreator
    public Checkpoint(@JsonProperty("position") Position position, @JsonProperty("shape") Shape shape) {
        this.position = position;
        this.shape = shape;
    }

    public Position getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkpoint that = (Checkpoint) o;
        return Objects.equals(position, that.position) && Objects.equals(shape, that.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, shape);
    }
}