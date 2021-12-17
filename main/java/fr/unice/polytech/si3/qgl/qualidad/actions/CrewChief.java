package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.path.Graph;
import fr.unice.polytech.si3.qgl.qualidad.actions.path.Node;
import fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement.PositionDeterminer;
import fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement.ShipActionsDeterminer;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.InitGame;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.qualidad.next.round.NextRound;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Reef;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;

import java.util.ArrayList;
import java.util.List;

public class CrewChief {
    private Crew crew;
    public List<Checkpoint> checkpoints;
    public static Position lastWatchPosition = new Position(Double.MAX_VALUE, Double.MAX_VALUE);
    List<VisibleEntity> visibleEntities = new ArrayList<>();
    boolean newVisibleEntities = false;


    public void initGame(InitGame initGame) {
        crew = new Crew(initGame.getSailors());
        crew.giveAssignments(new Equipment(initGame.getShip().getEntities(), initGame.getShip().getDeck().getWidth()).getSets(crew.getNbSailors()));
        checkpoints = ((RegattaGoal) initGame.getGoal()).getCheckpoints();
    }

    public List<Action> nextRound(NextRound nextRound) {
        if(visibleEntities.size() == 0){
            newVisibleEntities = true;
            visibleEntities = nextRound.getVisibleEntities();
        }
        else{
            newVisibleEntities = false;
            for(VisibleEntity v : nextRound.getVisibleEntities()){
                if(!visibleEntities.contains(v)){
                    visibleEntities.add(v);
                    newVisibleEntities = true;
                }
            }
        }
        Position aimedPosition = PositionDeterminer.getBestPosition(checkpoints, nextRound.getShip().getPosition(), nextRound.getShip().getShape(), visibleEntities);

        if (aimedPosition == null)
            return new ArrayList<>();
        else
            return ShipActionsDeterminer.getShipActions(
                aimedPosition,
                nextRound.getShip().getPosition(),
                nextRound.getWind(),
                crew,
                nextRound.getShip().getEntities()
        );
    }

    List<Checkpoint> listNodesToListCheckpoints(List<Node> nodes){
        if (nodes == null){
            return null;
        }
        List<Checkpoint> mediumCheckpoints = new ArrayList<>();
        for(Node node : nodes){
            mediumCheckpoints.add(node.toCheckPoint());
        }
        return mediumCheckpoints;
    }


    public Crew getCrew() {
        return crew;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}