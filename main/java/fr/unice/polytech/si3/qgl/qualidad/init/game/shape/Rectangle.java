package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class Rectangle extends Shape {
    private final double width;
    private final double height;
    private final double orientation;
    private final List<Point> vertices;

    @JsonCreator
    public Rectangle(@JsonProperty("width") double width, @JsonProperty("height") double height, @JsonProperty("orientation") double orientation) {
        super("rectangle");
        this.width = width;
        this.height = height;
        this.orientation = orientation;
        this.vertices = Arrays.asList(new Point(height / 2, width / 2),
                new Point(height / 2, -width / 2),
                new Point(-height / 2, -width / 2),
                new Point(-height / 2, width / 2));
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getRadius() {
        return vertices.get(0).getNorm();
    }

}
