package school.coda.remy_axel_ethan.projet_java.game.placement;

import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

public class PlayerPlacement {
    private final Case[][] cases;

    public PlayerPlacement(Case[][] cases) {
        this.cases = cases;
    }

    public boolean attemptPlacement(Boat selectedBoat, boolean isHorizontal, Case target) {
        if (selectedBoat == null) {
            return false;
        }

        CreationBoat creation = new CreationBoat(selectedBoat, isHorizontal, cases);
        return creation.tryPlacement(target);
    }
}
