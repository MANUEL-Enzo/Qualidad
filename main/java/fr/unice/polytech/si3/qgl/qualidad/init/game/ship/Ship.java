package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    private final String type;
    private final int life;
    private final Position position;
    private final String name;
    private final Deck deck;
    private final List<Entity> entities;
    private final Shape shape;

    @JsonCreator
    public Ship(@JsonProperty("type") String type, @JsonProperty("life") int life, @JsonProperty("position") Position position, @JsonProperty("name") String name, @JsonProperty("deck") Deck deck, @JsonProperty("entities") Entity[] entities, @JsonProperty("shape") Shape shape) {
        this.type = type;
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = new ArrayList<>(Arrays.asList(entities));
        this.shape = shape;
    }

    public String getType() {
        return type;
    }

    public int getLife() {
        return life;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Shape getShape() {
        return shape;
    }
}