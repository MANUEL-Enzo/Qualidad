package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.LiftSailAction;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.LowerSailAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.SailEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SailActions {
    static List<Action> getSailsActions(List<SailEntity> sails, List<Sailor> sailorsWithSail, double windOrientation, double windStrength, double shipOrientation) {
        List<Action> actions = new ArrayList<>();

        boolean wantedState = getWantedStateSail(windOrientation, windStrength, shipOrientation);

        for (SailEntity sail : sails)
            if (sail.isOpenned() != wantedState)
                for (Sailor sailor : sailorsWithSail)
                    if (sailor.getEntityCoordinate(EntityType.SAIL).equals(sail.getCoordinate())) {
                        actions.addAll(getSailActions(sailor, wantedState));
                        break;
                    }

        return actions;
    }

    static boolean getWantedStateSail(double windOrientation, double windStrength, double shipOrientation) {
        return Position.getAngleBetween(windOrientation, shipOrientation) < Math.PI / 2 && windStrength > 0;
    }

    static List<Action> getSailActions(Sailor sailor, boolean wantedState) {
        if (wantedState)
            return getLiftSailActions(sailor);
        else
            return getLowerSailActions(sailor);
    }

    static List<Action> getLowerSailActions(Sailor sailor) {
        if (!sailor.canMoveInOneTurn(EntityType.SAIL))
            return Collections.singletonList(sailor.getMoveToGoToAssignment(EntityType.SAIL));

        return Arrays.asList(sailor.getMoveToGoToAssignment(EntityType.SAIL), new LowerSailAction(sailor.getId()));
    }

    static List<Action> getLiftSailActions(Sailor sailor) {
        if (!sailor.canMoveInOneTurn(EntityType.SAIL))
            return Collections.singletonList(sailor.getMoveToGoToAssignment(EntityType.SAIL));

        return Arrays.asList(sailor.getMoveToGoToAssignment(EntityType.SAIL), new LiftSailAction(sailor.getId()));
    }
}