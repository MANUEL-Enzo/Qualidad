package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

import java.util.HashMap;
import java.util.Map;

public class Sailor {
    private final int id;
    private final String name;
    private final Map<EntityType, Coordinate> entitiesCoordinates;
    private static final String ERR_WRONG_ENTITY_TYPE = "Wrong entityType ask to sailor ";
    private static final String ERR_ALREADY_ASSIGN = "Already assign this to sailor ";

    private Coordinate coordinate;
    private SideType sideType;

    @JsonCreator
    public Sailor(@JsonProperty("id") int id, @JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("name") String name) {
        this.id = id;
        this.coordinate = new Coordinate(x, y);
        this.name = name;
        this.sideType = SideType.NOT_ASSIGNED;
        this.entitiesCoordinates = new HashMap<>();
    }

    public void assignSide(SideType sideType) {
        this.sideType = sideType;
    }

    public boolean hasAssignment(EntityType entityType) {
        return entitiesCoordinates.containsKey(entityType);
    }

    public void assign(EntityType entityType, Coordinate entityCoordinate) {
        if (!hasAssignment(entityType))
            entitiesCoordinates.put(entityType, entityCoordinate);

        else throw new IllegalArgumentException(ERR_ALREADY_ASSIGN + id);
    }

    public boolean isOnAssignment(EntityType entityType) {
        if (hasAssignment(entityType))
            return entitiesCoordinates.get(entityType).equals(coordinate);

        throw new IllegalArgumentException(ERR_WRONG_ENTITY_TYPE + id);
    }

    public boolean canMoveInOneTurn(EntityType entityType) {
        if (hasAssignment(entityType))
            return entitiesCoordinates.get(entityType).minus(coordinate).getNorm() <= 5;

        throw new IllegalArgumentException(ERR_WRONG_ENTITY_TYPE + id);
    }

    public MovingAction getMoveToGoToAssignment(EntityType entityType) {
        if (hasAssignment(entityType))
            return new MovingAction(id, getLegalMoveTo(entitiesCoordinates.get(entityType)));

        throw new IllegalArgumentException(ERR_WRONG_ENTITY_TYPE + id);
    }

    Coordinate getLegalMoveTo(Coordinate coordinate) {
        int MAX_MOVES = 5;
        return coordinate.minus(this.coordinate).reduceTo(MAX_MOVES);
    }

    public void move(int xDistance, int yDistance) {
        coordinate = coordinate.plus(new Coordinate(xDistance, yDistance));
    }

    public Coordinate getEntityCoordinate(EntityType entityType) {
        if (hasAssignment(entityType))
            return entitiesCoordinates.get(entityType);

        throw new IllegalArgumentException(ERR_WRONG_ENTITY_TYPE + id);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public SideType getSideType() {
        return sideType;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}