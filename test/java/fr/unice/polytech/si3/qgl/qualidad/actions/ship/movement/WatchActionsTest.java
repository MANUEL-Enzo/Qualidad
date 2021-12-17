package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.Action;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.FireAction;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class WatchActionsTest {
    Sailor watcher;

    @Test
    void getWatchActions() {
        watcher = Mockito.spy(new Sailor(1,0,0,"sailor"));
        doReturn(false,true).when(watcher).canMoveInOneTurn(any());
        doReturn(mock(MovingAction.class)).when(watcher).getMoveToGoToAssignment(any());
        assertEquals(1, WatchActions.getWatchActions(watcher).size());
        assertEquals(2, WatchActions.getWatchActions(watcher).size());
    }
}