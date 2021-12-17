package fr.unice.polytech.si3.qgl.qualidad.actions.path;

import fr.unice.polytech.si3.qgl.qualidad.actions.Physic;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Reef;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Stream;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;

import java.util.*;

public class Graph {
    public static final double GRID_COEF = 1;
    public static final double MAX_SPEED = 100;
    public static final double GRID_PRECISION = GRID_COEF*MAX_SPEED;
    private Set<Node> nodes = new HashSet<>();
    Node destNode; //aimedPosition
    Node sourceNode; //shipPosition

    public Graph(Position shipPosition, Checkpoint aim, List<VisibleEntity> visibleEntities){
        destNode = new Node(aim.getPosition(), aim.getShape());
        sourceNode = new Node(shipPosition, new Rectangle(GRID_PRECISION, GRID_PRECISION, 0));
        nodes.add(sourceNode);
        nodes.add(destNode);
        if(visibleEntities == null){
            visibleEntities = new ArrayList<>();
        }
        initializeGrid(shipPosition, aim.getPosition(), GRID_PRECISION, visibleEntities);
        Dijsktra.calculateShortestPathFromSource(this, sourceNode);
    }

    /**
     * We get all nodes in the Set<Node> nodes which not colliding any reefs
     * @param shipPosition
     * @param aimedPosition
     * @param gridPrecision
     * @param reefsStreams
     */
    void initializeGrid(Position shipPosition, Position aimedPosition, double gridPrecision, List<VisibleEntity> reefsStreams){
        int coefX = aimedPosition.getX() - shipPosition.getX() < 0 ? -1 : 1;
        int coefY = aimedPosition.getY() - shipPosition.getY() < 0 ? -1 : 1;
        for (double y=0 ; y<=Math.abs(aimedPosition.getY() - shipPosition.getY())+gridPrecision ; y+= gridPrecision){
            for (double x=0 ; x<=Math.abs(aimedPosition.getX() - shipPosition.getX())+gridPrecision ; x+= gridPrecision){
                Position position = new Position(shipPosition, new Position(coefX*x, coefY*y));
                Shape shape = new Rectangle(gridPrecision, gridPrecision, 0);
                Node node = new Node(position, shape);
                addAdjacentNodesToReefs(reefsStreams, node);
            }
        }
        for(Node node : nodes){
            node.addDestination(nodes);
        }
    }

    void addAdjacentNodesToReefs(List<VisibleEntity> reefsStreams, Node node){
        boolean collide = false;
        for (VisibleEntity reefStream : reefsStreams){
            if (Physic.shapesAreColliding(node.getShape(), node.getPosition(), reefStream.getShape(), reefStream.getPosition())){
                // pour chaque node en collision avec un reef, on ajoute les 8 nodes adjacents pour etre sur d'avoir un chemin
                addAdjacentNodesToReef(nodes, node, reefStream, new ArrayList<Node>());
                collide = true;
            }
        }
        if(!collide) nodes.add(node);
    }

     void addAdjacentNodesToReef(Set<Node> nodes, Node node, VisibleEntity reefStream, List<Node> evaluatedReefNodes) {
        evaluatedReefNodes.add(node);
        for (Node adjNode : getNotEvaluatedAdjacentNodes(node,evaluatedReefNodes)){
            if (Physic.shapesAreColliding(adjNode.getShape(), adjNode.getPosition(), reefStream.getShape(), reefStream.getPosition())){
                addAdjacentNodesToReef(nodes,adjNode,reefStream, evaluatedReefNodes);
            }else{
                nodes.add(adjNode);
            }
        }
    }

    List<Node> getNotEvaluatedAdjacentNodes(Node node, List<Node> evaluatedReefNodes){
        List<Node> adjacentNodes = new ArrayList<>();
        for(double i= node.getPosition().getX()-GRID_PRECISION; i<=node.getPosition().getX() + GRID_PRECISION; i += GRID_PRECISION){
            for(double j=node.getPosition().getY()-GRID_PRECISION; j<=node.getPosition().getY()+GRID_PRECISION; j += GRID_PRECISION){
                Node adjNode = new Node(new Position(i, j,0), new Rectangle(GRID_PRECISION,GRID_PRECISION,0));
                if (!evaluatedReefNodes.contains(adjNode)){
                    adjacentNodes.add(adjNode);
                }
            }
        }
        return adjacentNodes;
    }


    /**
     * On cherche à trouver tout les checkpoints intermédiaires à notre chemin pour avancer le plus vite possible
     * On retourne la liste shortestPathFromSource totalement réduite
     * La manière de procéder est la suivante: à chaque noeud adjacent disponible, on checke en ligne droite jusqu'ou il y a un autre noeud
     *
     */
    public List<Node> getMediumNodes(){
        //On récupère le chemin le plus court d'après le Dijsktra
        List<Node> shortestPathFromSource = destNode.getShortestPath();
        shortestPathFromSource.add(destNode);
        List<Node> mediumNodes = (List<Node>) ((LinkedList)shortestPathFromSource).clone();
        //On boucle pour chaque noeud du chemin. La subtilité étant que nous allons supprimé les noeuds inutiles à chaque boucle
        int mediumCheckpointsCounter = 0;
        for(Node actualNode : shortestPathFromSource){
            //On cherche le noeud le plus loin du noeud actuel tout en respectant le chemin
            if(!mediumNodes.contains(actualNode)){
                continue;
            }
            //On teste donc chaque noeud adjacent disponible
            Node farthestNode = farthestNodeFromAdjacentNodes(actualNode.getAdjacentNodes().keySet(), actualNode, shortestPathFromSource);
            ListIterator<Node> iterator = mediumNodes.listIterator(mediumCheckpointsCounter+1);
            while(iterator.hasNext()) {
                Node nextNode = iterator.next();
                if (nextNode.equals(farthestNode)){
                    break;
                }
                shortestPathFromSource.get(shortestPathFromSource.indexOf(nextNode)).setVisited((true));
                iterator.remove();
            }
            mediumCheckpointsCounter++;
        }
        mediumNodes.remove(0);
        return mediumNodes;
    }

    Node farthestNodeFromAdjacentNodes(Set<Node> adjacentNodesToActualNode, Node actualNode, List<Node> shortestPathFromSource){
        double longest = 0;
        Node farthestNode = null;
        for(Node adjacentNode : adjacentNodesToActualNode){
            if(!adjacentNode.isVisited() && shortestPathFromSource.contains(adjacentNode)) {
                //On récupère le noeud le plus loin en ligne droite en partant du noeud adjacent
                Node testNode = farthestNodeFromAdjacentNode(actualNode, adjacentNode, shortestPathFromSource);
                if(testNode.getPosition().getDistance(actualNode.getPosition()) > longest){
                    longest = testNode.getPosition().getDistance(actualNode.getPosition());
                    farthestNode = testNode;
                }
            }
        }
        return farthestNode;
    }

    /**
     * Récupérer le noeud du chemin le plus loin par rapport à la droite entre actualNode et adjacentNode
     * @param actualNode
     * @param adjacentNode
     * @return
     */
    Node farthestNodeFromAdjacentNode(Node actualNode, Node adjacentNode, List<Node> shortestPath) {
        Node farthestNode = null;
        Position vector = actualNode.getPosition().getVector(adjacentNode.getPosition());
        boolean find = false;
        actualNode.setVisited(true);
        adjacentNode.setVisited(true);
        while (!find) {
            farthestNode = null;
            double angleError = 0.01;
            //On récupère le noeud étant dans la continuité de la droite entre actualNode et adjacentNode
            for (Node nextNode : adjacentNode.getAdjacentNodes().keySet()) {
                if (nextNode.isVisited()) continue;
                if (nextNode.getDistance() >= adjacentNode.getDistance() && Position.getAngleBetween(actualNode.getPosition().getVector(nextNode.getPosition()).getOrientation(), vector.getOrientation()) <= angleError) {
                    farthestNode = nextNode;
                    break;
                }
            }
            //Si le noeud est null ou qu'il n'est pas contenu dans le path, on récupère le dernier noeud dans le chemin et on s'arrête
            if (farthestNode == null || !shortestPath.contains(farthestNode)) {
                farthestNode = adjacentNode;
                find = true;
            }
            //Sinon on avance d'un noeud
            else {
                adjacentNode = farthestNode;
            }
        }
        return farthestNode;
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    // getters and setters

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Node getDestNode() {
        return destNode;
    }

    public Node getSourceNode() {
        return sourceNode;
    }
}
