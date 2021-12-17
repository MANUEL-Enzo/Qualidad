package fr.unice.polytech.si3.qgl.qualidad.init.game.goal;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BattleGoal extends Goal {

    @JsonCreator
    public BattleGoal() {
        super("BATTLE");
    }
}