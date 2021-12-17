package fr.unice.polytech.si3.qgl.qualidad.actions.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Coordinate;

public class MovingAction extends Action {
    private final Coordinate coordinate;

    @JsonCreator
    public MovingAction(@JsonProperty("sailorId") int sailorId, @JsonProperty("xdistance") int xDistance, @JsonProperty("ydistance") int yDistance) {
        super(sailorId, "MOVING");
        coordinate = new Coordinate(xDistance, yDistance);
    }

    public MovingAction(int sailorId, Coordinate coordinate) {
        this(sailorId, coordinate.getX(), coordinate.getY());
    }

    public int getXdistance() {
        return coordinate.getX();
    }

    public int getYdistance() {
        return coordinate.getY();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if(!(obj instanceof MovingAction)) return false;

        MovingAction other = (MovingAction) obj;
        return this.sailorId == other.sailorId && this.getXdistance() == other.getXdistance()
                && this.getYdistance() == other.getYdistance();
    }

}