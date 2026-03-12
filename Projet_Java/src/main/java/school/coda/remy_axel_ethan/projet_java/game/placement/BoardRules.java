package school.coda.remy_axel_ethan.projet_java.game.placement;

import school.coda.remy_axel_ethan.projet_java.tools.Case;
import static school.coda.remy_axel_ethan.projet_java.tools.Grille.GRID_SIZE;

public class BoardRules {
    public boolean isHorizontal = true;
    public Case[][] cases;

    public boolean isInGrid(int x, int y, int size) {
        if (isHorizontal) {
            return (x + size) <= GRID_SIZE;
        }
        return (y + size) <= GRID_SIZE;
    }

    public boolean isCasesFree(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            int checkX = isHorizontal ? x + i : x;
            int checkY = isHorizontal ? y : y + i;

            Case checkTarget = cases[checkX][checkY];

            if (isntCaseValide(checkTarget)) {
                return false;
            }
        }
        return true;
    }

    public boolean isntCaseValide(Case target) {
        if (target.getOccupiedBy() != null) {
            IO.println("Au moins l'une des cases est déjà occupée !");
            return true;
        }
        return false;
    }
}