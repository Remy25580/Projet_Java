package school.coda.remy_axel_ethan.projet_java.game.placement;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.Arrays;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.CASE_X_POSITION;
import static school.coda.remy_axel_ethan.projet_java.tools.Grille.CASE_Y_POSITION;

public class CreationBoat {

    private final Boat selectedBoat;
    private final Case[][] cases;
    private final boolean isHorizontal;

    public CreationBoat(Boat selectedBoat, boolean isHorizontal, Case[][] cases) {
        this.selectedBoat = selectedBoat;
        this.isHorizontal = isHorizontal;
        this.cases = cases;
    }

    public boolean tryPlacement(Case target) {
        int x = target.getPos()[0];
        int y = target.getPos()[1];
        int size = selectedBoat.getSize();

        if (!BoardRules.isInGrid(x, y, size, isHorizontal)) {
            IO.println("Le bateau sort de la grille !");
            return false;
        }
        if (!BoardRules.isCasesFree(x, y, size, isHorizontal, cases)) {
            return false;
        }

        applyPlacement(x, y, size);

        return true;
    }

    private void applyPlacement(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            int placeX = isHorizontal ? x + i : x;
            int placeY = isHorizontal ? y : y + i;

            placeBoat(cases[placeX][placeY]);
        }
    }

    public void placeBoat(Case target){
        target.setOccupiedBy(selectedBoat);
        IO.println(Arrays.toString(target.getPos()));
    }
}
