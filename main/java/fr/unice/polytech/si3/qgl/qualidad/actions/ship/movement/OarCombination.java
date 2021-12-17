package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

public class OarCombination {
    private final int nbOarsLeft;
    private final int nbOarsRight;

    public OarCombination(int nbOarsLeft, int nbOarsRight) {
        this.nbOarsLeft = nbOarsLeft;
        this.nbOarsRight = nbOarsRight;
    }

    public int getNbOarsLeft() {
        return nbOarsLeft;
    }

    public int getNbOarsRight() {
        return nbOarsRight;
    }
}