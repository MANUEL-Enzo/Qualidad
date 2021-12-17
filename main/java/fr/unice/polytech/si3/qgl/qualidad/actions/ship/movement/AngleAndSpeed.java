package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

public class AngleAndSpeed {
    double angle;
    double speed;

    public AngleAndSpeed(double angle, double speed) {
        this.angle = angle;
        this.speed = speed;
    }

    public AngleAndSpeed() {
        this(0, 0);
    }

    public AngleAndSpeed(double nbLeftRowers, double nbRightRowers, double nbTotalOars) {
        this(((nbRightRowers - nbLeftRowers) / nbTotalOars) * Math.PI, ((nbLeftRowers + nbRightRowers) / nbTotalOars) * 165);
    }

    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return speed;
    }
}