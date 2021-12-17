package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.Crew;
import fr.unice.polytech.si3.qgl.qualidad.actions.CrewChief;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.TurnAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.SailEntity;
import fr.unice.polytech.si3.qgl.qualidad.next.round.Wind;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;
import fr.unice.polytech.si3.qgl.qualidad.type.ActionType;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShipActionsDeterminer {
    public static List<Action> getShipActions(Position aimedPosition, Position shipPosition, Wind wind, Crew crew, List<Entity> entities) {
        List<Action> actions = new ArrayList<>();
        if (crew.hasWatcher() && CrewChief.lastWatchPosition.getDistance(shipPosition) > 1000) {
            CrewChief.lastWatchPosition = shipPosition;
            addActions(
                    actions,
                    WatchActions.getWatchActions(
                            crew.getWatcher()
                    )
            );
        }
        if (crew.hasSail())
            addActions(
                    actions,
                    SailActions.getSailsActions(
                            entities.stream().filter(entity -> entity.getEntityType().equals(EntityType.SAIL)).map(entity -> (SailEntity) entity).collect(Collectors.toList()),
                            crew.getSailorsWithSail(),
                            wind.getOrientation(),
                            wind.getStrength(),
                            shipPosition.getOrientation())
            );

        if (crew.hasRudder())
            addActions(
                    actions,
                    RudderActions.getRuddersActions(
                            shipPosition.getVector(aimedPosition).getOrientation(),
                            shipPosition.getOrientation(),
                            crew.getRudder())
            );

        addActions(
                actions,
                OarActions.getOarsActions(
                        aimedPosition,
                        getShipPosition(actions, shipPosition),
                        crew.getNbRowersPortside(),
                        crew.getNbRowersStarboard(),
                        crew.getNbTotalRowers(),
                        (int) entities.stream().filter(entity -> entity.isEntity(EntityType.OAR)).count(),
                        crew.getRowersBySides(),
                        wind.getOrientation(),
                        wind.getStrength(),
                        (int) entities.stream().filter(entity -> entity.isEntity(EntityType.SAIL) && ((SailEntity) entity).isOpenned()).count())
        );

        execMovingActions(
                actions,
                crew.getSailorById()
        );

        return actions;
    }

    static void addActions(List<Action> actions, List<Action> actionsToAdd) {
        List<Integer> ids = new ArrayList<>();

        for (Action action : actions)
            ids.add(action.getSailorId());

        for (Action action : actionsToAdd)
            if (!ids.contains(action.getSailorId()))
                actions.add(action);
    }

    static Position getShipPosition(List<Action> actions, Position shipCurrentPosition) {
        for (Action action : actions)
            if (action.getType().equals(ActionType.TURN))
                return new Position(shipCurrentPosition.getX(),
                        shipCurrentPosition.getY(),
                        shipCurrentPosition.getOrientation() + ((TurnAction) action).getRotation()
                );

        return shipCurrentPosition;
    }

    static void execMovingActions(List<Action> actions, Map<Integer, Sailor> sailorById) {
        for (Action action : actions)
            if (action.getType().equals(ActionType.MOVING))
                sailorById.get(action.getSailorId()).move(((MovingAction) action).getXdistance(),
                        ((MovingAction) action).getYdistance()
                );
    }
}