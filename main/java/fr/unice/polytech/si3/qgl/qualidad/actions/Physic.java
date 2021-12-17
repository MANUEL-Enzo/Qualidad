package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.*;

import java.util.ArrayList;
import java.util.List;

public class Physic {

    public static boolean shapesAreColliding(Shape shape1, Position position1, Shape shape2, Position position2) {
        switch (shape1.getType()) {
            case CIRCLE:
                switch (shape2.getType()) {
                    case CIRCLE:
                        return circlesAreColliding((Circle) shape1, position1, (Circle) shape2, position2);
                    case RECTANGLE:
                        return shapeIsCollidingCircle((Circle) shape1, position1, ((Rectangle) shape2).getVertices(), position2, ((Rectangle) shape2).getOrientation());
                    case POLYGON:
                        return shapeIsCollidingCircle((Circle) shape1, position1, ((Polygon) shape2).getVertices(), position2, ((Polygon) shape2).getOrientation());
                }
                break;
            case RECTANGLE:
                switch (shape2.getType()) {
                    case CIRCLE:
                        return shapeIsCollidingCircle((Circle) shape2, position2, ((Rectangle) shape1).getVertices(), position1, ((Rectangle) shape1).getOrientation());
                    case RECTANGLE:
                        return polygonsAreColliding(((Rectangle) shape1).getVertices(), position1, ((Rectangle) shape1).getOrientation(), ((Rectangle) shape2).getVertices(), position2, ((Rectangle) shape2).getOrientation());
                    case POLYGON:
                        return polygonsAreColliding(((Rectangle) shape1).getVertices(), position1, ((Rectangle) shape1).getOrientation(), ((Polygon) shape2).getVertices(), position2, ((Polygon) shape2).getOrientation());
                }
                break;
            case POLYGON:
                switch (shape2.getType()) {
                    case CIRCLE:
                        return shapeIsCollidingCircle((Circle) shape2, position2, ((Polygon) shape1).getVertices(), position1, ((Polygon) shape1).getOrientation());
                    case RECTANGLE:
                        return polygonsAreColliding(((Rectangle) shape2).getVertices(), position2, ((Rectangle) shape2).getOrientation(), ((Polygon) shape1).getVertices(), position1, ((Polygon) shape1).getOrientation());
                    case POLYGON:
                        return polygonsAreColliding(((Polygon) shape1).getVertices(), position1, ((Polygon) shape1).getOrientation(), ((Polygon) shape2).getVertices(), position2, ((Polygon) shape2).getOrientation());
                }
                break;
            default:
                break;
        }
        throw new IllegalArgumentException("Wrong shape type asked");
    }

    static boolean circlesAreColliding(Circle shape1, Position position1, Circle shape2, Position position2) {
        return position1.getDistance(position2) <= (shape1.getRadius() + shape2.getRadius());
    }

    static boolean polygonsAreColliding(List<Point> vertices1, Position position1, double shape1Orientation, List<Point> vertices2, Position position2, double shape2Orientation) {
        List<Position> positions1 = getVertices(position1, vertices1, shape1Orientation);
        List<Position> positions2 = getVertices(position2, vertices2, shape2Orientation);

        if (shapeContainPoints(position1, positions1, positions2) || shapeContainPoints(position2, positions2, positions1))
            return true;

        return sidesAreColliding(positions1, positions2);
    }

    static boolean sidesAreColliding(List<Position> positions1, List<Position> positions2){
        for (int i = 0; i < positions1.size(); i++) {
            Position currentPosition1 = positions1.get(i);
            Position nextPosition1 = positions1.get((i + 1) % positions1.size());

            for (int j = 0; j < positions2.size(); j++) {
                Position currentPosition2 = positions2.get(j);
                Position nextPosition2 = positions2.get((j + 1) % positions2.size());

                if(currentPosition1.getVector(nextPosition1).vectorialZComposant(currentPosition2.getVector(nextPosition2)) != 0 &&
                        Position.hasJunction(currentPosition1, nextPosition1, currentPosition2, nextPosition2))
                {
                    return true;
                }

            }
        }
        return false;
    }

    static boolean shapeContainPoints(Position position1, List<Position> vertices1, List<Position> vertices2) {
        for (int i = 0; i < vertices1.size(); i++) {
            Position currentPosition = vertices1.get(i);
            Position nextPosition = vertices1.get((i + 1) % vertices1.size());
            double currentSegmentArea = Math.abs(
                    ( (currentPosition.getX() - position1.getX()) * (nextPosition.getY() - position1.getY()) -
                      (nextPosition.getX() - position1.getX()) * (currentPosition.getY() - position1.getY()) )
                    / 2);

            for (Position position : vertices2) {
                double AreaWithoutCurrentPosition = Math.abs(
                        ( (position.getX() - position1.getX()) * (nextPosition.getY() - position1.getY()) -
                          (nextPosition.getX() - position1.getX()) * (position.getY() - position1.getY()) )
                        / 2);
                double AreaWithoutNextPosition = Math.abs(
                        ( (currentPosition.getX() - position1.getX()) * (position.getY() - position1.getY()) -
                          (position.getX() - position1.getX()) * (currentPosition.getY() - position1.getY()) )
                        / 2);
                double AreaWithoutCenter = Math.abs(
                        ( (currentPosition.getX() - position.getX()) * (nextPosition.getY() - position.getY()) -
                          (nextPosition.getX() - position.getX()) * (currentPosition.getY() - position.getY()) )
                        / 2);

                if ( Math.abs(AreaWithoutCenter + AreaWithoutCurrentPosition + AreaWithoutNextPosition - currentSegmentArea) < 1E-10 )
                    return true;
            }
        }
        return false;
    }

    static boolean shapeIsCollidingCircle(Circle shape1, Position position1, List<Point> vertices, Position position2, double shape2Orientation) {
        if (position1.getDistance(position2) <= shape1.getRadius())
            return true;

        List<Position> positions = getVertices(position2, vertices, shape2Orientation);

        for (int i = 0; i < positions.size(); i++) {
            Position currentPosition = positions.get(i);
            Position nextPosition = positions.get((i + 1) % positions.size());

            if (currentPosition.getDistance(position1) <= shape1.getRadius())
                return true;
            else {
                Position projection = currentPosition.getProjection(position1, nextPosition);

                if ( Math.abs(currentPosition.getDistance(projection) + nextPosition.getDistance(projection) - currentPosition.getDistance(nextPosition)) < 1E-10 &&
                        projection.getDistance(position1) <= shape1.getRadius())
                {
                    return true;
                }
            }
        }

        return false;
    }

    static List<Position> getVertices(Position referencePosition, List<Point> vertices, double shapeOrientation) {
        List<Position> positions = new ArrayList<>();

        for (Point point : vertices)
            positions.add(getPositionOfPoint(referencePosition, point, shapeOrientation));

        return positions;
    }

    static Position getPositionOfPoint(Position referencePosition, Point point, double shapeOrientation) {
        double distance = point.getNorm();
        double orientationOffset = Math.acos(point.getX() / distance);

        if (point.getY() < 0)
            orientationOffset = -orientationOffset;

        return new Position(
                referencePosition,
                new Position(
                        distance * Math.cos(referencePosition.getOrientation() + shapeOrientation + orientationOffset),
                        distance * Math.sin(referencePosition.getOrientation() + shapeOrientation + orientationOffset)));
    }
}