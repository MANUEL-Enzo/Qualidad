package fr.unice.polytech.si3.qgl.qualidad.actions.path;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;

import java.util.*;

public class Node implements Comparable{
    private Position position;
    private Shape shape;
    private boolean visited;

    private List<Node> shortestPath = new LinkedList<>();

    private Double distance = Double.MAX_VALUE;

    Map<Node, Double> adjacentNodes = new HashMap<>();

    /**
     * Get all nodes next to the actual node, contained in nodes set
     */
    public void addDestination(Set<Node> nodes) {
        for (Node node : nodes) {
            double distance = this.position.getDistance(node.position);
            if(node.equals(this)) continue;
            if ((distance <= this.shape.getRadius() * 2)) {
                adjacentNodes.put(node, distance);
            }
        }
    }

    public Node(Position position, Shape shape){
        this.position = position;
        this.shape = shape;
        this.visited = false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        //if(this.getPosition().equals(node.getPosition())) return true;
        return Objects.equals(position, node.position);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Node){
            if (o.equals(this)) return 0;
            if(((Node) o).getPosition().equals(this.getPosition())) return 0;
            return ((Node) o).getDistance() < getDistance() ? 1 : -1;
        }
        return 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Node{" +
                "position=" + position +
                ", distance=" + distance +
                '}';
    }

    public Checkpoint toCheckPoint(){
        return new Checkpoint(this.position, this.shape);
    }
}
