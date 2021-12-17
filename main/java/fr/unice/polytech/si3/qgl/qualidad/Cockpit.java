package fr.unice.polytech.si3.qgl.qualidad;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.qualidad.actions.CrewChief;
import fr.unice.polytech.si3.qgl.qualidad.init.game.InitGame;
import fr.unice.polytech.si3.qgl.qualidad.next.round.NextRound;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

import java.util.ArrayList;
import java.util.List;

public class Cockpit implements ICockpit {
    private final List<String> logs;
    private final ObjectMapper objectMapper;
    private final CrewChief crewChief;

    public Cockpit() {
        logs = new ArrayList<>();
        objectMapper = new ObjectMapper();
        crewChief = new CrewChief();
    }

    public void initGame(String game) {
        try {
            crewChief.initGame(objectMapper.readValue(game, InitGame.class));
        } catch (JsonProcessingException ignored) {

        }
    }

    public String nextRound(String round) {
        try {
            return objectMapper.writeValueAsString(crewChief.nextRound(objectMapper.readValue(round, NextRound.class)));
        } catch (JsonProcessingException ignored) {
            return "[]";
        }
    }

    public CrewChief getCrewChief() {
        return crewChief;
    }

    public List<String> getLogs() {
        return logs;
    }
}