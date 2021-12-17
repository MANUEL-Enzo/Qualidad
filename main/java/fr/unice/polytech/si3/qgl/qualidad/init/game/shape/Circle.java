package fr.unice.polytech.si3.qgl.qualidad.init.game.shape;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Circle extends Shape {
    private final double radius;

    @JsonCreator
    public Circle(@JsonProperty("radius") double radius) {
        super("circle");
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}