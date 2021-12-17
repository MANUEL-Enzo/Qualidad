package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon extends Shape {
    private final double orientation;
    private final List<Point> vertices;

    @JsonCreator
    public Polygon(@JsonProperty("orientation") double orientation, @JsonProperty("vertices") Point[] vertices) {
        this(orientation, Arrays.asList(vertices));
    }

    public Polygon(double orientation, List<Point> vertices) {
        super("polygon");
        this.orientation = orientation;
        this.vertices = new ArrayList<>(vertices);
    }

    public double getOrientation() {
        return orientation;
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public double getRadius() {
        double inclusiveRadius = 0;

        for (Point point : vertices)
            if (point.getNorm() > inclusiveRadius)
                inclusiveRadius = point.getNorm();

        return inclusiveRadius;
    }
}