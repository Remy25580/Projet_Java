package school.coda.remy_axel_ethan.projet_java.placement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.boat.BoatType;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.*;

public class PlacementController implements Initializable {
    @FXML
    private GridPane grid;
    @FXML
    private Label selectedBoatLabel;
    @FXML
    private Button orientationButton;

    private Boat selectedBoat = null;
    private boolean isHorizontal = true;
    private Case[][] cases;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cases = createGrid(grid);
        for (Node child : grid.getChildren()) {
            Button button = (Button) child;
            button.setOnMouseClicked(e -> {
                if(selectedBoat != null) {
                    Button targetButton = (Button)e.getTarget();
                    int x = (int) targetButton.getProperties().get(CASE_X_POSITION);
                    int y = (int) targetButton.getProperties().get(CASE_Y_POSITION);
                    Case target = cases[x][y];
                    creationBoat(target);
                }
            });
        }
    }

    public void creationBoat(Case target) {
        int x = target.getPos()[0];
        int y = target.getPos()[1];
        int size = selectedBoat.getSize();

        if (!isInGrid(x, y, size)) {
            IO.println("Le bateau sort de la grille !");
            return;
        }
        if (!isCasesFree(x, y, size)) {
            return;
        }

        applyPlacement(x, y, size);
    }

    private boolean isInGrid(int x, int y, int size) {
        if (isHorizontal) {
            return (x + size) <= GRID_SIZE;
        } else {
            return (y + size) <= GRID_SIZE;
        }
    }

    private boolean isCasesFree(int x, int y, int size) {
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

    private boolean isntCaseValide(Case target) {
        if (target.getOccupiedBy() != null) {
            IO.println("Au moins l'une des cases est déjà occupée !");
            return true;
        }
        return false;
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
        getButtonFromACase(target).setStyle("-fx-background-color: red;");
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

    public void selectBoat(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        String boatId = clickedButton.getId();
        BoatType type = BoatType.valueOf(boatId);
        changeSelectedBoat(type);
    }

    private void changeSelectedBoat(BoatType type) {
        this.selectedBoat = new Boat(type);
        this.selectedBoatLabel.setText("Bateau sélectionné : " + type.getType() + " (Taille: " + type.getSize() + ")");
    }

    @FXML
    protected void toggleOrientation() {
        isHorizontal = !isHorizontal;
        if (isHorizontal) {
            orientationButton.setText("Orientation: Horizontale");
        } else {
            orientationButton.setText("Orientation: Verticale");
        }
    }
}
