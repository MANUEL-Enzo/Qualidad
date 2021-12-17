package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PhysicTest {
    Polygon polygon1, polygon2;
    Rectangle rectangle1, rectangle2;
    Circle circle1, circle2;
    Position position1, position2, position3;

    @Nested
    class circlesAreColliding {
        @BeforeEach
        void setUp() {
            circle1 = new Circle(5);
            position1 = new Position();
            circle2 = new Circle(5);
        }

        @Test
        void upDownLeftRightCollisions() {
            position2 = new Position(10, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(7, 7);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(0, 10);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(-7, 7);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(-10, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(-7, -7);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(0, -10);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));

            position2 = new Position(7, -7);
            assertTrue(Physic.shapesAreColliding(circle1, position1, circle2, position2));
        }
    }

    @Nested
    class rectangleIsCollidingCircleTest {
        @BeforeEach
        void setUp() {
            rectangle1 = new Rectangle(2, 4, 0);
            rectangle2 = new Rectangle(2, 4, Math.PI / 2);
            position1 = new Position();
            position3 = new Position(0, 0, -Math.PI / 2);
            circle1 = new Circle(5);
        }

        @Test
        void upDownLeftRightCollisions() {
            position2 = new Position(0, 6);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, circle1, position2));

            position2 = new Position(0, 6.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position2 = new Position(0, -6);
            assertTrue(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position2 = new Position(0, -6.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position2 = new Position(7, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position2 = new Position(7.001, 0);
            assertFalse(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position2 = new Position(-7, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position2 = new Position(-7.001, 0);
            assertFalse(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            //With shape orientation

            position1 = new Position(0, 0, Math.PI / 4);
            rectangle1 = new Rectangle(2, 4, Math.PI / 4);
            position2 = new Position(0, 7);
            circle1 = new Circle(5.1);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, circle1, position2));
            assertTrue(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            circle1 = new Circle(4.9);
            assertFalse(Physic.shapesAreColliding(rectangle1, position1, circle1, position2));
            assertFalse(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            position1 = new Position(0, 0, 0);
            rectangle1 = new Rectangle(2, 4, Math.PI / 4);
            circle1 = new Circle(2.7);
            position2 = new Position(2.9, 2.9);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, circle1, position2));
            assertTrue(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));

            rectangle1 = new Rectangle(2, 3, Math.PI / 4);
            position2 = new Position(3, 3);
            assertFalse(Physic.shapesAreColliding(rectangle1, position1, circle1, position2));
            assertFalse(Physic.shapesAreColliding(circle1, position2, rectangle1, position1));
        }
    }

    @Nested
    class polygonIsCollidingCircleTest {
        /**
         * Polygon <code>polygon1</code> tested :
         * [-1,1]____________________________________[1,1]
         * !                                           \
         * !                                            \
         * !                                             \
         * !                                              \
         * !                                               \
         * !                                                \
         * !                                               [2,0]
         * !                                                /
         * !                                               /
         * !                                              /
         * !                                             /
         * !                                            /
         * !                                           /
         * [-1,-1]__________________________________[1,-1]
         */

        @BeforeEach
        void setUp() {
            polygon1 = new Polygon(0.0, new Point[]{
                    new Point(-1, 1),
                    new Point(1, 1),
                    new Point(2, 0),
                    new Point(1, -1),
                    new Point(-1, -1)
            });
            position1 = new Position();
            circle1 = new Circle(5);
        }

        @Test
        void upDownLeftRightCollisions() {
            position2 = new Position(7, 0);
            assertTrue(Physic.shapesAreColliding(polygon1, position1, circle1, position2));

            position2 = new Position(7.001, 0);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(5, 4);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(5.1, 4.1);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(5, -4);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(5.1, -4.1);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-6, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-6.001, 0);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, 6);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, 6.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, -6);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, -6.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));
        }

        @Test
        void centerCircleInPolygon() {
            position2 = new Position(0, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(2, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(1.99, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-1, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-0.99, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, 1);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, 0.99);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, -1);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(0, -0.99);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));
        }

        @Test
        void centerCircleCornerPolygon() {
            position2 = new Position(1, 1);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(1, -1);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(2, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-1, 1);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-1, -1);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));
        }

        @Test
        void cornerPolygonInCircle() {
            position2 = new Position(4, 5);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(4.001, 5.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-4, 5);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-4.001, 5.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-4, -5);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(-4.001, -5.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(4, -5);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(4.001, -5.001);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(7, 0);
            assertTrue(Physic.shapesAreColliding(circle1, position2, polygon1, position1));

            position2 = new Position(7.001, 0);
            assertFalse(Physic.shapesAreColliding(circle1, position2, polygon1, position1));
        }
    }

    @Nested
    class rectangleAreCollidingTest {
        @BeforeEach
        void setUp() {
            rectangle1 = new Rectangle(2, 4, 0);
            position1 = new Position();
            rectangle2 = new Rectangle(2, 4, 0);
        }

        @Test
        void upDownLeftRightCollisions() {
            position2 = new Position(4, 0);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(4.001, 0);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-4, 0);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-4.001, 0);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(0, 2);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(0, 2.001);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(0, -2);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(0, -2.001);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));
        }

        @Test
        void cornerAreColliding() {
            position2 = new Position(4, 2);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(4.001, 2.001);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-4, 2);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-4.001, 2.001);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-4, -2);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-4.001, -2.001);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(4, -2);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(4.001, -2.001);
            assertFalse(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));
        }

        @Test
        void cornerInRectangle() {
            position2 = new Position(0, 0);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(2, 0);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-2, 0);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(0, 1);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(0, -1);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(2, 1);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-2, 1);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(-2, -1);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));

            position2 = new Position(2, -1);
            assertTrue(Physic.shapesAreColliding(rectangle2, position2, rectangle1, position1));
        }
    }

    @Nested
    class polygonAreCollidingTest {
        @BeforeEach
        void setUp() {
            polygon1 = new Polygon(0.0, new Point[]{
                    new Point(-1, 1),
                    new Point(1, 1),
                    new Point(2, 0),
                    new Point(1, -1),
                    new Point(-1, -1),
                    new Point(-2, 0)
            });
            position1 = new Position();
            polygon2 = new Polygon(0.0, new Point[]{
                    new Point(-1, 1),
                    new Point(1, 1),
                    new Point(2, 0),
                    new Point(1, -1),
                    new Point(-1, -1),
                    new Point(-2, 0)
            });
        }

        @Test
        void sidesCollisions() {

            position2 = new Position(0, 2);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(0, -2);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(3, 1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-3, 1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-3, -1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(3, -1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));
        }

        @Test
        void cornerAreColliding() {
            position2 = new Position(2, 2);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-2, 2);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-2, -2);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(2, -2);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(4, 0);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-4, 0);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));
        }

        @Test
        void cornerInPolygon() {
            position2 = new Position(0, 0);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(3, 0);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-3, 0);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(0, 1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(0, -1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(2, 1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-2, 1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(-2, -1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));

            position2 = new Position(2, -1);
            assertTrue(Physic.shapesAreColliding(polygon2, position2, polygon1, position1));
        }

        @Test
        void sidesAreColliding() {
            assertTrue(Physic.sidesAreColliding(Arrays.asList(new Position(1,3), new Position(1, -3), new Position(-1, -3), new Position(-1, 3)),
                    Arrays.asList(new Position(3,1), new Position(3, -1), new Position(-3, -1), new Position(-3, 1))));

            assertTrue(Physic.sidesAreColliding(Arrays.asList(new Position(1,3), new Position(1, -3), new Position(-1, -3), new Position(-1, 3)),
                    Arrays.asList(new Position(3,1), new Position(3, -1), new Position(0, -1), new Position(0, 1))));

            //TODO others tests
        }
    }


    @Nested
    class polygonAndRectangleAreCollidingTest {
        @BeforeEach
        void setUp() {
            polygon1 = new Polygon(0.0, new Point[]{
                    new Point(-1, 1),
                    new Point(1, 1),
                    new Point(2, 0),
                    new Point(1, -1),
                    new Point(-1, -1),
                    new Point(-2, 0)
            });
            position1 = new Position();
            rectangle1 = new Rectangle(2, 2, 0);
        }

        @Test
        void sidesCollisions() {
            position2 = new Position(0, 2);
            assertTrue(Physic.shapesAreColliding(polygon1, position1, rectangle1, position2));

            position2 = new Position(0, -2);
            assertTrue(Physic.shapesAreColliding(polygon1, position1, rectangle1, position2));

            position2 = new Position(3, 0);
            assertTrue(Physic.shapesAreColliding(polygon1, position1, rectangle1, position2));

            position2 = new Position(-2, 0);
            assertTrue(Physic.shapesAreColliding(polygon1, position1, rectangle1, position2));
        }

        @Test
        void cornerCollisions() {
            position2 = new Position(2, 2);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, polygon1, position2));

            position2 = new Position(2, -2);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, polygon1, position2));

            position2 = new Position(-2, -2);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, polygon1, position2));

            position2 = new Position(-2, 2);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, polygon1, position2));

            position2 = new Position(-3, 1);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, polygon1, position2));

            position2 = new Position(-3, -1);
            assertTrue(Physic.shapesAreColliding(rectangle1, position1, polygon1, position2));
        }
    }

    @Test
    void randomTest(){
        Position reefPosition = new Position(3023.3333333333335,-4403.333333333333,0);
        Shape reef = new Polygon(0,Arrays.asList(
                new Point(-583.3333333333335,-96.66666666666697),
                new Point(-223.33333333333348,643.333333333333),
                new Point(120.66666666666652,320.33333333333303),
                new Point(376.6666666666665,43.33333333333303),
                new Point(386.6666666666665,-196.66666666666697),
                new Point(36.666666666666515,-676.666666666667)
        ));
        Position trajectoryPosition = new Position(3262.569454729606,-3957.85634006938,2.16219218532416);
        Shape trajectory = new Rectangle(2.9154759474226504,832.4855730125017,0);

        assertFalse(Physic.shapesAreColliding(trajectory, trajectoryPosition, reef, reefPosition));
    }

}