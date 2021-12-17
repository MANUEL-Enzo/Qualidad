package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

public class AngleAndDistance {
    double angle;
    double distance;

    public AngleAndDistance(double angle, double distance) {
        this.angle = angle;
        this.distance = distance;
    }

    public AngleAndDistance() {
        this(-1, -1);
    }

    public double getAngle() {
        return angle;
    }

    public double getDistance() {
        return distance;
    }

    public boolean hasBeenInitialized() {
        return !(distance == -1);
    }
}