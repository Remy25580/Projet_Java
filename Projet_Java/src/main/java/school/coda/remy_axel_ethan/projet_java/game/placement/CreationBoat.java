package school.coda.remy_axel_ethan.projet_java.game.placement;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.util.Arrays;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.CASE_X_POSITION;
import static school.coda.remy_axel_ethan.projet_java.tools.Grille.CASE_Y_POSITION;

public class CreationBoat {

    private final Label selectedBoatLabel;
    private Boat selectedBoat;
    private final BoardRules rules;
    private Button currentBoatButton;
    private final GridPane grid;
    private final Button gameStartButton;
    private int nbBoatPlaced;
    private final Button PATROUILLEUR;
    private final Button SOUS_MARIN;
    private final Button DESTROYER;
    private final Button CUIRASSE;
    private final Button PORTE_AVION;



    public CreationBoat (Boat selectedBoat, BoardRules rules, Button currentBoatButton, GridPane grid, Label selectedBoatLabel, Button gameStartButton, int nbBoatPlaced, Button patrouilleur, Button destroyer, Button sous_marin, Button cuirasse, Button porte_avion) {
        this.selectedBoat = selectedBoat;
        this.rules = rules;
        this.currentBoatButton = currentBoatButton;
        this.grid = grid;
        this.selectedBoatLabel = selectedBoatLabel;
        this.gameStartButton = gameStartButton;
        this.nbBoatPlaced = nbBoatPlaced;
        PATROUILLEUR = patrouilleur;
        SOUS_MARIN = sous_marin;
        DESTROYER = destroyer;
        CUIRASSE = cuirasse;
        PORTE_AVION = porte_avion;
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

        if (currentBoatButton != null) {
            currentBoatButton.setDisable(true);
        }

        this.selectedBoatLabel.setText("Bateau sélectionné : aucun");

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

    @FXML
    public void resetGame() {
        for (Case[] aCase : rules.cases) {
            for (Case currentCase : aCase) {
                currentCase.setOccupiedBy(null);

                Button btn = getButtonFromACase(currentCase);
                if (btn != null) {
                    btn.setStyle("-fx-background-color: blue;" +
                            "-fx-border-color: black;" +
                            "-fx-border-radius: 0;");
                }
            }
        }

        Button[] boatButtons = {PATROUILLEUR, SOUS_MARIN, DESTROYER, CUIRASSE, PORTE_AVION};
        for (Button b : boatButtons) {
            if (b != null) b.setDisable(false);
        }

        this.nbBoatPlaced = 0;
        this.selectedBoat = null;
        this.currentBoatButton = null;
    }
}
