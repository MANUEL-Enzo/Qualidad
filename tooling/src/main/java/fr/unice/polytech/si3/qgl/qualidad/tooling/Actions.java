package fr.unice.polytech.si3.qgl.qualidad.tooling;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Actions {
    private final List<Action> actions;

    @JsonCreator
    public Actions(@JsonProperty("actions") Action[] actions) {
        this.actions = new ArrayList<>(Arrays.asList(actions));
    }

    public List<Action> getActions() {
        return actions;
    }
}