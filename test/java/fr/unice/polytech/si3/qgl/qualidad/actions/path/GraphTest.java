package fr.unice.polytech.si3.qgl.qualidad.actions.path;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Circle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Reef;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Stream;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.VisibleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph graph;
    @BeforeEach
    void setUp(){
        //Checkpoint checkpoint = new Checkpoint(new Position(1000, 0, 0.0), new Circle(150.0));
        //List<Reef> reefs = Arrays.asList(new Reef(new Position(500, 0, 0.0),new Rectangle(100,10,0)));
        //graph = new Graph(new Position(0, 0, 0), checkpoint.getPosition(), null, null, null);
    }

//    @Test
//    void addAdjacentNodesToReefTest() {
//        Reef reef = new Reef(new Position(0, 0, 0.0), new Rectangle(30, 30, 0));
//        Set<Node> nodes = new HashSet<>();
//        Shape nodeShape = new Rectangle(Graph.GRID_PRECISION, Graph.GRID_PRECISION, 0);
//        Node initialNode = new Node(new Position(0, 0, 0), nodeShape);
//        graph.addAdjacentNodesToReef(nodes, initialNode, reef, new ArrayList<Node>());
//        Iterator i = nodes.iterator();
//        while (i.hasNext()) {
//            System.out.println((Node) i.next());
//        }
//    }



    @Test
    void getShortestPathWithReefTest(){
        Checkpoint checkpoint = new Checkpoint(new Position(5000, 0, 0.0), new Circle(50.0));
        List<VisibleEntity> reefs = new ArrayList<>();
        reefs.add(new Reef(new Position(2500, 0, 0.0), new Rectangle(50, 50, 0.0)));
        Graph g = new Graph(new Position(0, 0, 0), checkpoint, reefs);
//        for(Node n : g.getDestNode().getShortestPath()) {
//            System.out.println(n);
//        }
//        System.out.println("On cherche les checkpoints intermédiaire : ");
        List<Node> mediumCheckpoints = g.getMediumNodes();
//        for(Node n : mediumCheckpoints){
//            System.out.println(n);
//        }
        assertEquals(4, mediumCheckpoints.size());
    }

    @Test
    void getShortestPathWithReefsTest(){
        Checkpoint checkpoint = new Checkpoint(new Position(5000, 0, 0.0), new Circle(50.0));
        List<VisibleEntity> reefs = new ArrayList<>();
        reefs.add(new Reef(new Position(2500, 0, 0.0), new Rectangle(50, 50, 0.0)));
        reefs.add(new Reef(new Position(3000, 0, 0.0), new Rectangle(1000, 1000, 0.0)));
        Graph g = new Graph(new Position(0, 0, 0), checkpoint, reefs);
//        for(Node n : g.getDestNode().getShortestPath()) {
//            System.out.println(n);
//        }
//        System.out.println("On cherche les checkpoints intermédiaire : ");
        List<Node> mediumCheckpoints = g.getMediumNodes();
//        for(Node n : mediumCheckpoints){
//            System.out.println(n);
//        }
        assertEquals(10, mediumCheckpoints.size());
    }

    @Test
    void getShortestPathStraightLine(){
        Checkpoint checkpoint = new Checkpoint(new Position(3000, 0, 0.0), new Circle(50.0));
        List<VisibleEntity> reefs = new ArrayList<>();
        Graph g = new Graph(new Position(0, 0, 0), checkpoint, reefs);
//        for(Node n : g.getDestNode().getShortestPath()) {
//            System.out.println(n);
//        }
//        System.out.println("On cherche les checkpoints intermédiaire : ");
        List<Node> mediumCheckpoints = g.getMediumNodes();
//        for(Node n : mediumCheckpoints){
//            System.out.println(n);
//        }
        assertEquals(1, mediumCheckpoints.size());
    }

    @Test
    void getShortestPathDiagoCheckpoint(){
        Checkpoint checkpoint = new Checkpoint(new Position(5000, 5000, 0.0), new Circle(100.0));
        List<VisibleEntity> reefs = new ArrayList<>();
        Graph g = new Graph(new Position(0, 0, 0), checkpoint, reefs);
//        for(Node n : g.getDestNode().getShortestPath()) {
//            System.out.println(n);
//        }
//        System.out.println("On cherche les checkpoints intermédiaire : ");
        List<Node> mediumCheckpoints = g.getMediumNodes();
//        for(Node n : mediumCheckpoints){
//            System.out.println(n);
//        }
        assertEquals(1, mediumCheckpoints.size());
    }

//TODO: Ne peut pas être testé sans instancier un graphe (sauf que initialisation inutile). Methode en static?

//    @Test
//    void getNotEvaluatedAdjacentNodesTest(){
//        double precision = Graph.GRID_PRECISION;
//        Node currentNode = new Node(new Position(0,0,0), new Rectangle(precision,precision,0));
//        Node reefNode = new Node(new Position(precision,0,0), new Rectangle(precision,precision,0));
//        List<Node> evaluatedReefNodes = new ArrayList<Node>();
//        evaluatedReefNodes.add(reefNode);
//        evaluatedReefNodes.add(currentNode);
//        assertEquals(7, Graph.getNotEvaluatedAdjacentNodes(currentNode, evaluatedReefNodes).size());
//
//        Node reefNode2 = new Node(new Position(precision,precision,0), new Rectangle(precision,precision,0));
//        evaluatedReefNodes.add(reefNode2);
//        assertEquals(6, Graph.getNotEvaluatedAdjacentNodes(reefNode, evaluatedReefNodes).size());
//    }

    //Integration test

//    @Test
//    void findAPathWithStreams(){
//        Checkpoint checkpoint = new Checkpoint(new Position(800, 0, 0.0), new Circle(100.0));
//        List<Reef> reefs = new ArrayList<>();
//        reefs.add(new Reef(new Position(100,0,0.0), new Rectangle(400, 20, 0.0)));
//        reefs.add(new Reef(new Position(-100,0,0.0), new Rectangle(400, 20, 0.0)));
//        reefs.add(new Reef(new Position(0,-100,0), new Rectangle(20, 400, 0.0)));
//        reefs.add(new Reef(new Position(0,100,0), new Rectangle(20, 400, 0.0)));
//        List<Stream> streams = new ArrayList<>();
//        streams.add(new Stream(new Position(0,100,0), new Rectangle(20, 400, 0.0),100));
//        Graph g = new Graph(new Position(0, 0, 0), checkpoint, reefs, streams);
//        Node lastNode = g.destNode.getShortestPath().get(g.destNode.getShortestPath().size()-1);
//        for(Node n : g.getDestNode().getShortestPath()) {
//            System.out.println("Noeud " + n);
//        }
//        assertTrue(g.destNode.getAdjacentNodes().containsKey(lastNode));
//    }

    @Test
    void farthestNode(){
        Checkpoint checkpoint = new Checkpoint(new Position(800, 0, 0.0), new Circle(100.0));
        Graph g = new Graph(new Position(0,0,0),checkpoint, null);
        Node farthestNode = g.farthestNodeFromAdjacentNode(g.destNode.getShortestPath().get(0), g.destNode.getShortestPath().get(1), g.destNode.getShortestPath());
        assertEquals(g.destNode.getShortestPath().get(7), farthestNode);
    }

    @Test
    void farthestDiagoNode(){
        Checkpoint checkpoint = new Checkpoint(new Position(800, 800, 0.0), new Circle(100.0));
        Graph g = new Graph(new Position(0,0,0),checkpoint, null);
        Node farthestNode = g.farthestNodeFromAdjacentNode(g.destNode.getShortestPath().get(0), g.destNode.getShortestPath().get(1), g.destNode.getShortestPath());
        assertEquals(g.destNode.getShortestPath().get(7), farthestNode);
    }

    @Test
    void initializeGrid() {
        graph = new Graph(new Position(1000,1000,0), new Checkpoint(new Position(0,0),new Circle(100)), null);
        assertEquals(144, graph.getNodes().size());
        graph = new Graph(new Position(0,0,0), new Checkpoint(new Position(1000,1000),new Circle(100)), null);
        assertEquals(144, graph.getNodes().size());
        graph = new Graph(new Position(0,0,0), new Checkpoint(new Position(1000,0),new Circle(100)), null);
        assertEquals(24, graph.getNodes().size());
        graph = new Graph(new Position(0,0,0), new Checkpoint(new Position(0,0),new Circle(100)), null);
        assertEquals(4, graph.getNodes().size());
    }
}