package fr.unice.polytech.si3.qgl.qualidad.init.game.ship;

import java.util.Objects;

public class Coordinate {
    private final int[] coordinates;

    public Coordinate(int x, int y) {
        coordinates = new int[]{x, y};
    }

    public Coordinate(Coordinate... coordinates) {
        this.coordinates = new int[]{0, 0};
        for (Coordinate coordinate : coordinates) {
            this.coordinates[0] += coordinate.coordinates[0];
            this.coordinates[1] += coordinate.coordinates[1];
        }
    }

    public Coordinate plus(Coordinate... coordinates) {
        return new Coordinate(this, new Coordinate(coordinates));
    }

    public Coordinate minus(Coordinate... coordinates) {
        Coordinate newCoordinate = new Coordinate(this);
        for (Coordinate coordinate : coordinates) {
            newCoordinate.coordinates[0] -= coordinate.getX();
            newCoordinate.coordinates[1] -= coordinate.getY();
        }
        return newCoordinate;
    }

    public Coordinate plus(int x, int y) {
        return this.plus(new Coordinate(x, y));
    }

    public Coordinate minus(int x, int y) {
        return this.plus(new Coordinate(-x, -y));
    }

    public Coordinate reduceTo(int max_moves) {
        Coordinate newCoordinate = new Coordinate(this);

        if (this.getNorm() > max_moves) {
            int surplus = newCoordinate.getNorm() - max_moves;
            for (int i = newCoordinate.coordinates.length - 1; i >= 0; i--) {
                int coordinate = newCoordinate.coordinates[i];

                if (surplus > 0)
                    if (surplus > Math.abs(coordinate)) {
                        newCoordinate.coordinates[i] = 0;
                        surplus -= Math.abs(coordinate);
                    } else if (coordinate > 0) {
                        newCoordinate.coordinates[i] -= surplus;
                        surplus -= Math.abs(coordinate);
                    } else if (coordinate < 0) {
                        newCoordinate.coordinates[i] += surplus;
                        surplus -= Math.abs(coordinate);
                    }
            }
        }

        return newCoordinate;
    }

    public int getNorm() {
        return Math.abs(getX()) + Math.abs(getY());
    }

    public int getX() {
        return coordinates[0];
    }

    public int getY() {
        return coordinates[1];
    }

    @Override
    public String toString() {
        return "[" + coordinates[0] + "," + coordinates[1] + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinate)) return false;
        Coordinate coordinate = (Coordinate) obj;
        return getX() == coordinate.getX() && getY() == coordinate.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}