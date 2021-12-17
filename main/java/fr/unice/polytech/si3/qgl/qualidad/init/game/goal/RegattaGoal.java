package fr.unice.polytech.si3.qgl.qualidad.init.game.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegattaGoal extends Goal {
    private transient final List<Checkpoint> checkpoints;

    @JsonCreator
    public RegattaGoal(@JsonProperty("checkpoints") Checkpoint[] checkpoints) {
        super("REGATTA");
        this.checkpoints = new ArrayList<>(Arrays.asList(checkpoints));
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}