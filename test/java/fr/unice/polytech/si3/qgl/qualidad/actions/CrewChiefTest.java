package fr.unice.polytech.si3.qgl.qualidad.actions;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.path.Node;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.InitGame;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Position;
import fr.unice.polytech.si3.qgl.qualidad.init.game.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Rectangle;
import fr.unice.polytech.si3.qgl.qualidad.init.game.shape.Shape;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Deck;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Ship;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.entity.Entity;
import fr.unice.polytech.si3.qgl.qualidad.next.round.NextRound;
import fr.unice.polytech.si3.qgl.qualidad.next.round.visible.entity.Reef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class CrewChiefTest {


    CrewChief crewChief;
    Shape nodeShape;

    @BeforeEach
    void setUp(){
        crewChief = new CrewChief();
        nodeShape = new Rectangle(100,100,0);
    }

    @Test
    void listNodesToListCheckpointsTest() {
        Node node = new Node(new Position(0,0,0.0), new Rectangle(0,0, 0.0));
        Node node2 = new Node(new Position(100,0,0.0), new Rectangle(50,50, 0.0));
        List<Node> listNode = new ArrayList<>();
        listNode.add(node);
        listNode.add(node2);
        List<Checkpoint> checkpoints = crewChief.listNodesToListCheckpoints(listNode);
        assertTrue(listNode.size() == checkpoints.size());
        assertTrue(new Checkpoint(new Position(0,0,0.0), new Rectangle(0,0, 0.0)).getPosition().equals(checkpoints.get(0).getPosition()));
        assertTrue(new Checkpoint(new Position(0,0,0.0), new Rectangle(0,0, 0.0)).getShape().getType().equals(checkpoints.get(0).getShape().getType()));
        assertTrue(new Checkpoint(new Position(0,0,0.0), new Rectangle(0,0, 0.0)).getShape().getRadius() == checkpoints.get(0).getShape().getRadius());

        Position p1,p2,p3,p4;
        List<Node> nodeList = Arrays.asList(
                new Node(p1 = new Position(0,0), nodeShape),
                new Node(p2 = new Position(500,0), nodeShape),
                new Node(p3 = new Position(500,500), nodeShape),
                new Node(p4 = new Position(1000,1000), nodeShape)
        );
        List<Checkpoint> checkpointList = crewChief.listNodesToListCheckpoints(nodeList);
        assertEquals(Arrays.asList(
                new Checkpoint(p1,nodeShape),
                new Checkpoint(p2,nodeShape),
                new Checkpoint(p3,nodeShape),
                new Checkpoint(p4,nodeShape)
        ), checkpointList);
        assertEquals(new ArrayList<>(), crewChief.listNodesToListCheckpoints(new ArrayList<>()));
        assertEquals(null, crewChief.listNodesToListCheckpoints(null));
    }

    @Test
    void initGame() {
        crewChief = new CrewChief();
        crewChief.initGame(new InitGame(
                new RegattaGoal(new Checkpoint[]{}),
                new Ship(
                        "test",
                        100,
                        mock(Position.class),
                        "test",
                        mock(Deck.class),
                        new Entity[]{},
                        new Rectangle(100,100,0)
                ),
                new Sailor[]{
                        new Sailor(0, 0,0, "jack"),
                        new Sailor(1,0,0,"jean")
                },
                1
        ));
        assertEquals(2, crewChief.getCrew().getNbSailors());
    }


    //Integration tests
    @Test
    void nextRoundTestNoCheckpoint() throws IOException{
        String nextRound = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/nextRound.json")));
        String initGame = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/initGame.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        crewChief.initGame(objectMapper.readValue(initGame, InitGame.class));
        assertEquals(0, crewChief.nextRound(objectMapper.readValue(nextRound, NextRound.class)).size());
    }

    @Test
    void nextRoundTest() throws IOException{
        String nextRound = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/nextRound.json")));
        String nextRoundAddReef = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/nextRoundAddReef.json")));
        String initGame = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/initGameCheckpoint.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        crewChief.initGame(objectMapper.readValue(initGame, InitGame.class));
        List<Action> list = crewChief.nextRound(objectMapper.readValue(nextRound, NextRound.class));
        assertEquals(21, list.size());

        assertEquals(21, crewChief.nextRound(objectMapper.readValue(nextRoundAddReef, NextRound.class)).size());
    }

    @Test
    void isNewVisibleEntitiesTest() throws IOException {
        String initGame = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/initGameCheckpoint.json")));
        String nextRound = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/nextRound.json")));
        String nextRoundAddReef = new String(Files.readAllBytes(Paths.get("src/test/java/fr/unice/polytech/si3/qgl/qualidad/actions/testJson/nextRoundAddReef.json")));
        ObjectMapper objectMapper = new ObjectMapper();
        assertFalse(crewChief.newVisibleEntities);
        crewChief.initGame(objectMapper.readValue(initGame, InitGame.class));
        crewChief.nextRound(objectMapper.readValue(nextRound, NextRound.class));
        assertTrue(crewChief.newVisibleEntities);
        crewChief.nextRound(objectMapper.readValue(nextRound, NextRound.class));
        assertFalse(crewChief.newVisibleEntities);
        crewChief.nextRound(objectMapper.readValue(nextRoundAddReef, NextRound.class));
        assertTrue(crewChief.newVisibleEntities);
    }
}