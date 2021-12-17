package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.Physic;
import fr.unice.polytech.si3.qgl.qualidad.actions.path.Graph;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.VisibleEntityType;

import java.util.List;

public class PositionDeterminer {
    public static Position getBestPosition(List<Checkpoint> checkpoints, Position shipPosition, Shape shipShape, List<VisibleEntity> visibleEntities) {
        if (!checkpoints.isEmpty())
            if (Physic.shapesAreColliding(checkpoints.get(0).getShape(), checkpoints.get(0).getPosition(), shipShape, shipPosition))
                checkpoints.remove(0);

        Position aimedPosition;

        if (!checkpoints.isEmpty())
            if (checkpoints.size() == 1)
                aimedPosition = checkpoints.get(0).getPosition();
            else
                aimedPosition = getBestPositionBetweenTwoCheckpoints(shipPosition, checkpoints.get(0).getPosition(), checkpoints.get(0).getShape().getRadius(), checkpoints.get(1).getPosition());
        else
            return null;

        return getBestPositionToAvoidReefs(shipPosition, shipShape, aimedPosition, visibleEntities);
    }

    static Position getBestPositionToAvoidReefs(Position shipPosition, Shape shipShape, Position aimedPosition, List<VisibleEntity> visibleEntities) {
        Shape trajectory = new Rectangle( shipShape.getRadius() * 2, shipPosition.getDistance(aimedPosition), 0);
        Position trajectoryPosition = new Position( (shipPosition.getX() + aimedPosition.getX()) / 2,
                                                    (shipPosition.getY() + aimedPosition.getY()) / 2,
                                                    shipPosition.getVector(aimedPosition).getOrientation());

        Shape closerShape = null;
        Position closerShapePosition = null;

        for (VisibleEntity visibleEntity : visibleEntities)
            if (visibleEntity.getType().equals(VisibleEntityType.REEF))
                if( Physic.shapesAreColliding(trajectory, trajectoryPosition, visibleEntity.getShape(), visibleEntity.getPosition()))
                    if (closerShapePosition == null || closerShapePosition.getDistance(shipPosition) > visibleEntity.getPosition().getDistance(shipPosition)) {
                        closerShape = visibleEntity.getShape();
                        closerShapePosition = visibleEntity.getPosition();
                    }

        if (closerShapePosition == null)
            return aimedPosition;
        else
            return getBestPositionToAvoidReef(shipPosition, shipShape, closerShapePosition, closerShape, aimedPosition, visibleEntities);
    }

    static Position getBestPositionToAvoidReef(Position shipPosition, Shape shipShape, Position closerShapePosition, Shape closerShape, Position aimedPosition, List<VisibleEntity> visibleEntities) {
        double closerShapeRadius = closerShape.getRadius();
        double shipShapeRadius = shipShape.getRadius();

        Position projection = shipPosition.getProjection(closerShapePosition,aimedPosition);
        Position vectorForDodgingPosition = closerShapePosition.getVector(projection).times( (closerShapeRadius + shipShapeRadius * 2) / closerShapePosition.getDistance(projection));
        return getBestPositionToAvoidReefs(shipPosition,shipShape,new Position(closerShapePosition,vectorForDodgingPosition),visibleEntities);
    }

    static Position getBestPositionBetweenTwoCheckpoints(Position shipPosition, Position aimedCheckpoint, double checkpointRadius, Position nextCheckpoint) {
        Position vectorC1C2 = aimedCheckpoint.getVector(nextCheckpoint);
        Position vectorC1S = aimedCheckpoint.getVector(shipPosition);

        Position pointOnC1FromC2 = new Position(aimedCheckpoint, vectorC1C2.times(checkpointRadius / aimedCheckpoint.getDistance(nextCheckpoint)));
        Position pointOnC1FromS = new Position(aimedCheckpoint, vectorC1S.times(checkpointRadius / aimedCheckpoint.getDistance(shipPosition)));

        return new Position((pointOnC1FromC2.getX() + pointOnC1FromS.getX()) / 2, (pointOnC1FromC2.getY() + pointOnC1FromS.getY()) / 2);
    }
}