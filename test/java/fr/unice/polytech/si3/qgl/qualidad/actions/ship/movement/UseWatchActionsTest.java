package fr.unice.polytech.si3.qgl.qualidad.actions.ship.movement;

import fr.unice.polytech.si3.qgl.qualidad.actions.action.MovingAction;
import fr.unice.polytech.si3.qgl.qualidad.actions.action.UseWatchAction;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class UseWatchActionsTest {

    @Test
   public void getWatchActionsTestCaseTrue() {

        Sailor sailor = Mockito.mock(Sailor.class);
        when(sailor.canMoveInOneTurn(EntityType.WATCH)).thenReturn(true);
        when(sailor.getMoveToGoToAssignment(EntityType.WATCH)).thenReturn(new MovingAction(0, 0, 0));
        when(sailor.getId()).thenReturn(0);

        List watchactions = new ArrayList();
        watchactions =  WatchActions.getWatchActions(sailor);

        MovingAction mv = new MovingAction(0 , 0, 0);

        assertEquals(watchactions.size() , 2);
        assertTrue(watchactions.get(0).equals(mv));
        assertTrue(watchactions.get(1).equals( new UseWatchAction(0)));

    }

    @Test
    public void getWatchActionsTestCaseFalse(){
        Sailor sailor = Mockito.mock(Sailor.class);
        when(sailor.canMoveInOneTurn(EntityType.WATCH)).thenReturn(false);
        when(sailor.getMoveToGoToAssignment(EntityType.WATCH)).thenReturn(new MovingAction(0, 0, 0));
        when(sailor.getId()).thenReturn(0);

        List watchactions = new ArrayList();
        watchactions =  WatchActions.getWatchActions(sailor);

        MovingAction mv = new MovingAction(0 , 0, 0);

        assertEquals(watchactions.size() , 1);
        assertTrue(watchactions.get(0).equals(mv));

    }
}
