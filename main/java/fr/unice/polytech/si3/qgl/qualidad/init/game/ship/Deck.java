package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Deck {
    private final int width;
    private final int length;

    @JsonCreator
    public Deck(@JsonProperty("width") int width, @JsonProperty("length") int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}