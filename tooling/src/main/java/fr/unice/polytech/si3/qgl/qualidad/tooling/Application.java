
package fr.unice.polytech.si3.qgl.qualidad.tooling;

import fr.unice.polytech.si3.qgl.qualidad.Cockpit;
import fr.unice.polytech.si3.qgl.qualidad.init.game.Checkpoint;
import fr.unice.polytech.si3.qgl.qualidad.init.game.ship.Sailor;
import fr.unice.polytech.si3.qgl.qualidad.type.EntityType;
import fr.unice.polytech.si3.qgl.qualidad.type.SideType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        Cockpit cockpit = new Cockpit();
        String initGame = new String(Files.readAllBytes(Paths.get("tooling/src/main/java/fr/unice/polytech/si3/qgl/qualidad/tooling/week9/initGame.json")));
        String nextRound1 = new String(Files.readAllBytes(Paths.get("tooling/src/main/java/fr/unice/polytech/si3/qgl/qualidad/tooling/week9/nextRound3.json")));

        cockpit.initGame(initGame);
        Sailor captain = cockpit.getCrewChief().getCrew().getSailorById().get(0);

        System.out.println(SideType.NO_SIDE);
        System.out.println("\tSailor ID: " + captain.getId() + "\n\t\tRudder :" + captain.getEntityCoordinate(EntityType.RUDDER) + "\n\t\tActual coordinates :" + captain.getCoordinate());

        System.out.println(SideType.PORTSIDE);
        for (Sailor s : cockpit.getCrewChief().getCrew().getRowersBySides().get(SideType.PORTSIDE))
            System.out.println("\tSailor ID: " + s.getId() + "\n\t\tOar :" + s.getEntityCoordinate(EntityType.OAR) + "\n\t\tAlternative Oar :" + ((s.hasAssignment(EntityType.ALTERNATIVE_OAR)) ? s.getEntityCoordinate(EntityType.ALTERNATIVE_OAR) : "No alternative oar") + "\n\t\tActual coordinates :" + s.getCoordinate());

        System.out.println(SideType.STARBOARD);
        for (Sailor s : cockpit.getCrewChief().getCrew().getRowersBySides().get(SideType.STARBOARD))
            System.out.println("\tSailor ID: " + s.getId() + "\n\t\tOar :" + s.getEntityCoordinate(EntityType.OAR) + "\n\t\tAlternative Oar :" + ((s.hasAssignment(EntityType.ALTERNATIVE_OAR)) ? s.getEntityCoordinate(EntityType.ALTERNATIVE_OAR) : "No alternative oar") + "\n\t\tActual coordinates :" + s.getCoordinate());

        System.out.println("\n" + cockpit.nextRound(nextRound1));
    }
}