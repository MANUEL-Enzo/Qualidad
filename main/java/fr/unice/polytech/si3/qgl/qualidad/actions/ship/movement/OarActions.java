package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.OarAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

import java.util.*;

public class OarActions {
    static List<Action> getOarsActions(Position aimedPosition, Position shipPosition, int nbRowersPortside, int nbRowerStarboard, int nbTotalRowers, int nbTotalOars, Map<SideType, List<Sailor>> sailorsBySide, double windOrientation, double windStrength, double nbOpenedSail) {
        return getRowersActionsOfShip(getBestOarCombination(aimedPosition, shipPosition, nbRowersPortside, nbRowerStarboard, nbTotalRowers, nbTotalOars, windOrientation, windStrength, nbOpenedSail), sailorsBySide);
    }

    static OarCombination getBestOarCombination(Position aimedPosition, Position shipPosition, int nbRowersPortside, int nbRowerStarboard, int nbTotalRowers, int nbTotalOars, double windOrientation, double windStrength, double nbOpenedSail) {
        Map<AngleAndSpeed, OarCombination> oarCombinationsByAngleAndSpeed = getOarCombinationsByAngleAndSpeed(nbRowersPortside, nbRowerStarboard, nbTotalRowers, nbTotalOars);

        AngleAndSpeed bestAngleAndSpeed = new AngleAndSpeed();
        AngleAndDistance bestAngleAndDistance = new AngleAndDistance();

        for (AngleAndSpeed angleAndSpeed : oarCombinationsByAngleAndSpeed.keySet()) {
            Position newPosition = shipPosition.getNextPosition(angleAndSpeed.getAngle(), angleAndSpeed.getSpeed(), windOrientation, windStrength, nbOpenedSail);
            AngleAndDistance newAngleAndDistance = new AngleAndDistance(Position.getAngleBetween(newPosition.getOrientation(), shipPosition.getVector(aimedPosition).getOrientation()), newPosition.getDistance(aimedPosition));
            if (isBestAngleAndDistance(bestAngleAndDistance, newAngleAndDistance)) {
                bestAngleAndSpeed = angleAndSpeed;
                bestAngleAndDistance = newAngleAndDistance;
            }
        }

        return oarCombinationsByAngleAndSpeed.get(bestAngleAndSpeed);
    }

    static Map<AngleAndSpeed, OarCombination> getOarCombinationsByAngleAndSpeed(int nbRowersPortside, int nbRowerStarboard, int nbTotalRowers, int nbTotalOars) {
        Map<AngleAndSpeed, OarCombination> oarCombinationsByAngleAndSpeed = new HashMap<>();

        for (int nbLeftOar = 0; nbLeftOar <= nbRowersPortside; nbLeftOar++)
            for (int nbRightOar = 0; nbRightOar <= nbRowerStarboard; nbRightOar++)
                if (nbLeftOar + nbRightOar <= nbTotalRowers && nbLeftOar + nbRightOar > 0)
                    oarCombinationsByAngleAndSpeed.put(new AngleAndSpeed(nbLeftOar, nbRightOar, nbTotalOars), new OarCombination(nbLeftOar, nbRightOar));

        return oarCombinationsByAngleAndSpeed;
    }

    static boolean isBestAngleAndDistance(AngleAndDistance initialAngleAndDistance, AngleAndDistance newAngleAndDistance) {
        return (!initialAngleAndDistance.hasBeenInitialized()) ||
                (newAngleAndDistance.getAngle() < initialAngleAndDistance.getAngle()) ||
                ((newAngleAndDistance.getAngle() == initialAngleAndDistance.getAngle()) && (newAngleAndDistance.getDistance() < initialAngleAndDistance.getDistance()));
    }

    static List<Action> getRowersActionsOfShip(OarCombination oarCombination, Map<SideType, List<Sailor>> sailorsBySide) {
        List<Action> actions = new ArrayList<>();

        actions.addAll(getRowersActionsBySide(oarCombination.getNbOarsLeft(), SideType.PORTSIDE, sailorsBySide.get(SideType.PORTSIDE)));
        actions.addAll(getRowersActionsBySide(oarCombination.getNbOarsRight(), SideType.STARBOARD, sailorsBySide.get(SideType.STARBOARD)));

        return actions;
    }

    static List<Action> getRowersActionsBySide(int nbRowers, SideType sideType, List<Sailor> rowers) {
        List<Action> actions = new ArrayList<>();

        for (Sailor sailor : rowers)
            if (nbRowers > 0) {
                if (sailor.getSideType().equals(sideType))
                    actions.addAll(getRowerActions(EntityType.OAR, sailor));
                else
                    actions.addAll(getRowerActions(EntityType.ALTERNATIVE_OAR, sailor));

                nbRowers--;
            }

        return actions;
    }

    static List<Action> getRowerActions(EntityType entityType, Sailor sailor) {
        if (!sailor.canMoveInOneTurn(entityType))
            return Collections.singletonList(sailor.getMoveToGoToAssignment(entityType));

        return Arrays.asList(sailor.getMoveToGoToAssignment(entityType), new OarAction(sailor.getId()));
    }
}