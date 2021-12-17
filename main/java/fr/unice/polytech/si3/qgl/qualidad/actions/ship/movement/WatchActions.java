package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.UseWatchAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WatchActions {
    public static List<Action> getWatchActions(Sailor sailor){
        if (!sailor.canMoveInOneTurn(EntityType.WATCH))
            return Collections.singletonList(sailor.getMoveToGoToAssignment(EntityType.WATCH));

        return Arrays.asList(sailor.getMoveToGoToAssignment(EntityType.WATCH), new UseWatchAction(sailor.getId()));
    }
}