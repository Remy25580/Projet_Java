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
    private final BoardRules rules;
    private final GridPane grid;

    public CreationBoat(Boat selectedBoat, BoardRules rules, GridPane grid) {
        this.selectedBoat = selectedBoat;
        this.rules = rules;
        this.grid = grid;
    }

    public boolean tryPlacement(Case target) {
        int x = target.getPos()[0];
        int y = target.getPos()[1];
        int size = selectedBoat.getSize();

        if (!rules.isInGrid(x, y, size)) {
            IO.println("Le bateau sort de la grille !");
            return false;
        }
        if (!rules.isCasesFree(x, y, size)) {
            return false;
        }

        applyPlacement(x, y, size);

        return true;
    }

    private void applyPlacement(int x, int y, int size) {
        for (int i = 0; i < size; i++) {
            int placeX = rules.isHorizontal ? x + i : x;
            int placeY = rules.isHorizontal ? y : y + i;

            placeBoat(rules.cases[placeX][placeY]);
        }
    }

    public void placeBoat(Case target){
        target.setOccupiedBy(selectedBoat);
        IO.println(Arrays.toString(target.getPos()));
        getButtonFromACase(target).setStyle("-fx-background-color: red;" +
                "-fx-border-color: black;" +
                "-fx-border-radius: 0;");
    }

    public Button getButtonFromACase(Case target){
        Button button = null;
        int xButton;
        int yButton;
        int xTarget = target.getPos()[0];
        int yTarget = target.getPos()[1];

        for(Node node : grid.getChildren()){
            button = (Button) node;
            xButton = (int) button.getProperties().get(CASE_X_POSITION);
            yButton = (int) button.getProperties().get(CASE_Y_POSITION);
            if(xButton == xTarget && yButton == yTarget){
                return button;
            }
        }
        return button;
    }
}
