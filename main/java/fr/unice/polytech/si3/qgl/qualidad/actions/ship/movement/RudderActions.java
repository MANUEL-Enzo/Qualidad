package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.TurnAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RudderActions {
    static List<Action> getRuddersActions(double aimedOrientation, double shipOrientation, Sailor captain) {
        return getRudderActions(captain, getBestRudderAngle(aimedOrientation, shipOrientation));
    }

    static double getBestRudderAngle(double aimedOrientation, double shipOrientation) {
        double angle = Position.getAngleBetween(aimedOrientation, shipOrientation);
        double turnPositive = Position.getAngleBetween(aimedOrientation, shipOrientation + Math.PI / 4);
        double turnNegative = Position.getAngleBetween(aimedOrientation, shipOrientation - Math.PI / 4);

        if (angle > Math.PI / 4)
            return (Math.PI / 4) * ((turnPositive <= turnNegative) ? 1 : -1);
        else if (turnPositive <= turnNegative)
            return Math.PI / 4 - turnPositive;
        else
            return turnNegative - Math.PI / 4;
    }

    static List<Action> getRudderActions(Sailor sailor, double angle) {
        if (!sailor.canMoveInOneTurn(EntityType.RUDDER))
            return Collections.singletonList(sailor.getMoveToGoToAssignment(EntityType.RUDDER));

        return Arrays.asList(sailor.getMoveToGoToAssignment(EntityType.RUDDER), new TurnAction(sailor.getId(), angle));
    }
}