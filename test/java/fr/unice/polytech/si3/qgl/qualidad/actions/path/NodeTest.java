package fr.unice.polytech.si3.qgl.qualidad.actions.path;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Circle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Point;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Polygon;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    void getterTests(){

        Position p = new Position(1.0 , 1.0 , 0);
        Rectangle rectangle = new Rectangle(5.0 , 6.0 , 0);
        Node node = new Node(p , rectangle);

        assertEquals(node.getPosition() , p);
        assertEquals(node.getShape() , rectangle);
        assertFalse(node.isVisited());
        assertEquals(node.getAdjacentNodes() , new HashMap<>());
        assertEquals(node.getShortestPath() , new LinkedList<>());

    }

//    @Test
//    void addDestinationShapeRectangleTest() {
//
//        Position p = new Position(1.0, 1.0, 0);
//        Rectangle rectangle = new Rectangle(6.0, 4.0, 0);
//        Node node = new Node(p, rectangle);
//
//        Position p1 = new Position(2.0, 2.0, 0);
//        Circle c1 = new Circle(8);
//        Node node1 = new Node(p1, c1);
//
//        Position p3 = new Position(8.0, 9.0, 0);
//        Circle c2 = new Circle(1);
//        Node node3 = new Node(p3, c2);
//
//        Position p2 = new Position(1.0, 1.0, 0);
//        Rectangle rectangle1 = new Rectangle(5.0, 6.0, 0);
//        Node node2 = new Node(p2, rectangle1);
//
//        Position p4 = new Position(10.0, 11.0, 0);
//        Rectangle rectangle2 = new Rectangle(6.0, 8.0, 0);
//        Node node4 = new Node(p4, rectangle2);
//
//        Set<Node> set = new HashSet<>();
//        set.add(node1);
//        set.add(node2);
//        set.add(node3);
//        set.add(node4);
//
//
//        node.addDestination(set);
//
//        assertTrue(node.getAdjacentNodes().containsKey(node1));
//        assertTrue(node.getAdjacentNodes().containsKey(node2));
//        assertFalse(node.getAdjacentNodes().containsKey(node3));
//        assertFalse(node.getAdjacentNodes().containsKey(node4));
//
//    }

    @Test
    void addDestinationShapeCircleTest(){
        Position p = new Position(1.0, 1.0, 0);
        Rectangle rectangle = new Rectangle(6.0, 4.0, 0);
        Node node1 = new Node(p, rectangle);

        Position p1 = new Position(2.0, 2.0, 0);
        Circle c1 = new Circle(3);
        Node node = new Node(p1, c1);

        Position p3 = new Position(8.0, 9.0, 0);
        Circle c2 = new Circle(1);
        Node node3 = new Node(p3, c2);

        Position p2 = new Position(1.0, 1.0, 0);
        Rectangle rectangle1 = new Rectangle(5.0, 6.0, 0);
        Node node2 = new Node(p2, rectangle1);

        Position p4 = new Position(10.0, 11.0, 0);
        Point point = new Point(1  , 1);
        List l = new ArrayList();
        l.add(point);
        Polygon po  = new Polygon(0 , l);
        Node node4 = new Node(p4, po);

        Set<Node> set = new HashSet<>();
        set.add(node1);
        set.add(node2);
        set.add(node3);
        set.add(node4);

        node.addDestination(set);

        assertTrue(node.getAdjacentNodes().containsKey(node1));
        assertTrue(node.getAdjacentNodes().containsKey(node2));
        assertFalse(node.getAdjacentNodes().containsKey(node3));
        assertFalse(node.getAdjacentNodes().containsKey(node4));

    }

    @Test
    void compareTo() {
        Node n1 = new Node(
                new Position(100,100),
                new Rectangle(100,100,100)
        );
        n1.setDistance(0.0);
        Node n11 = new Node(
                new Position(100,100),
                new Rectangle(100,100,100)
        );
        n11.setDistance(0.0);
        Node n2 = new Node(
                new Position(1000,1000),
                new Rectangle(100,100,100)
        );
        n2.setDistance(100.0);

        assertEquals(1, n1.compareTo(new Object()));
        assertEquals(0, n1.compareTo(n1));
        assertEquals(0, n1.compareTo(n11));
        assertEquals(-1, n1.compareTo(n2));
    }
}
