package fr.unice.polytech.si3.qgl.qualidad.init.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;

import java.util.Objects;

public class Position {
    private final double[] coordinates;
    private final double orientation;

    @JsonCreator
    public Position(@JsonProperty("x") double x, @JsonProperty("y") double y, @JsonProperty("orientation") double orientation) {
        coordinates = new double[]{x, y};
        this.orientation = orientation;
    }

    public Position(double x, double y) {
        this(x, y, 0);
    }

    public Position(Position... positions) {
        coordinates = new double[]{0, 0};
        double orientation = 0;
        for (Position position : positions) {
            coordinates[0] += position.coordinates[0];
            coordinates[1] += position.coordinates[1];
            orientation += position.orientation;
        }
        this.orientation = orientation;
    }

    static double modulo(double orientation) {
        while (orientation > Math.PI)
            orientation -= 2 * Math.PI;

        while (orientation < -1 * Math.PI)
            orientation += 2 * Math.PI;

        return orientation;
    }

    public static double getAngleBetween(double orientation1, double orientation2) {
        double o1 = modulo(orientation1);
        double o2 = modulo(orientation2);

        double angle = Math.abs(o1 - o2);

        if (angle > Math.PI)
            angle = Math.abs(angle - 2 * Math.PI);

        return angle;
    }

    public Position minus(Position position) {
        return new Position(getX() - position.getX(),
                getY() - position.getY(),
                getOrientation() - position.getOrientation());
    }

    public Position times(double times) {
        return new Position(getX() * times, getY() * times, orientation);
    }

    public double scalar(Position v) {
        return getX() * v.getX() + getY() * v.getY();
    }

    public double vectorialZComposant(Position v){
        return this.getX() * v.getY() - this.getY() * v.getX();
    }

    public static boolean hasJunction(Position a, Position b, Position a_prime, Position b_prime){
        return lineIsCollidingVector(a, b, a_prime, b_prime) && lineIsCollidingVector(a_prime, b_prime, a, b);
    }

    public static boolean lineIsCollidingVector(Position a, Position b, Position a_prime, Position b_prime){
        Position ab = a.getVector(b);
        Position a_b_prime = a.getVector(b_prime);
        Position a_a_prime = a.getVector(a_prime);

        return ab.vectorialZComposant(a_b_prime) * ab.vectorialZComposant(a_a_prime) <= 0;
    }

    public double getDistance(Position position) {
        Position vector = position.minus(this);
        return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
    }

    public Position getVector(Position position) {
        Position vector = position.minus(this);

        if (vector.getX() == 0 && vector.getY() == 0) {
            return new Position();
        }

        double angle = Math.acos(vector.getX() / Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY()));

        if (vector.getY() < 0) {
            return new Position(vector.getX(), vector.getY(), -angle);
        }

        return new Position(vector.getX(), vector.getY(), angle);
    }

    public Position getProjection(Position positionToProject, Position positionToBeProjectedOn) {
        Position u = positionToProject.minus(this);
        Position v = positionToBeProjectedOn.minus(this);

        double uv_on_vv = v.scalar(u) / v.scalar(v);

        return new Position(this, v.times(uv_on_vv));
    }

    public Position getNextPosition(double angle, double speed, double windOrientation, double windStrength, double nbOpenedSail) {
        final int nbMicroActions = 100;
        Position newPosition = this;

        for (int i = 0; i < nbMicroActions; i++) {
            double microSpeed = (speed + Math.cos(getAngleBetween(newPosition.getOrientation(), windOrientation)) * windStrength * nbOpenedSail) / nbMicroActions;
            double microAngle = angle / nbMicroActions;

            double xMicroMovement = Math.cos(newPosition.orientation) * microSpeed;
            double yMicroMovement = Math.sin(newPosition.orientation) * microSpeed;

            newPosition = new Position(
                    newPosition,
                    new Position(xMicroMovement, yMicroMovement, microAngle)
            );
        }

        return newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return coordinates[0] == position.coordinates[0] && coordinates[1] == position.coordinates[1] && orientation == position.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates[0], coordinates[1], orientation);
    }

    @Override
    public String toString() {
        return "[x:" + getX() + ", y:" + getY() + ", orientation:" + orientation + "]";
    }

    public double getX() {
        return coordinates[0];
    }

    public double getY() {
        return coordinates[1];
    }

    public double getOrientation() {
        return orientation;
    }


}