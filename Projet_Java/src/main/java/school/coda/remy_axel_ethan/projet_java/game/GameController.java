package school.coda.remy_axel_ethan.projet_java.game;

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
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.*;

public class GameController implements Initializable {
    @FXML
    protected GridPane grid;
    @FXML
    private Label selectedBoatLabel;
    @FXML
    private Button orientationButton;
    @FXML
    private Button PATROUILLEUR, SOUS_MARIN, DESTROYER, CUIRASSE, PORTE_AVION;
    @FXML
    private Label nbBoatLabel;
    @FXML
    private Button gameStartButton;
    @FXML
    private GridPane opponentGrid;
    @FXML
    private Label opponentGridTitle;
    @FXML
    private  Label yourGridTitle;
    @FXML
    protected Label caseTouchedErrorMessage;

    private Boat selectedBoat = null;
    private Button currentBoatButton;
    private boolean isHorizontal = true;
    protected Case[][] cases;
    private int nbBoatPlaced = 0;

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

        if (currentBoatButton != null) {
            currentBoatButton.setDisable(true);
            currentBoatButton = null;
            this.nbBoatPlaced++;
            nbBoatLabel.setText("Bateaux restants : "+(5-nbBoatPlaced));
        }
        if(nbBoatPlaced == 5){gameStartButton.setDisable(false);}

        this.selectedBoat = null;
        this.selectedBoatLabel.setText("Bateau sélectionné : aucun");

    }

    private boolean isInGrid(int x, int y, int size) {
        if (isHorizontal) {
            return (x + size) <= GRID_SIZE;
        }
        return (y + size) <= GRID_SIZE;
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

    public void selectBoat(ActionEvent actionEvent) {
        this.currentBoatButton = (Button) actionEvent.getSource();

        String boatId = currentBoatButton.getId();
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

    @FXML
    private void initGamePlay(){
        gameStartButton.setVisible(false);
        gameStartButton.setManaged(false);
        PATROUILLEUR.setVisible(false);
        PATROUILLEUR.setManaged(false);
        SOUS_MARIN.setVisible(false);
        SOUS_MARIN.setManaged(false);
        DESTROYER.setVisible(false);
        DESTROYER.setManaged(false);
        CUIRASSE.setVisible(false);
        CUIRASSE.setManaged(false);
        PORTE_AVION.setVisible(false);
        PORTE_AVION.setManaged(false);
        selectedBoatLabel.setVisible(false);
        selectedBoatLabel.setManaged(false);
        orientationButton.setVisible(false);
        orientationButton.setManaged(false);
        nbBoatLabel.setVisible(false);
        nbBoatLabel.setManaged(false);
        opponentGrid.setVisible(true);
        opponentGrid.setManaged(true);
        opponentGridTitle.setVisible(true);
        opponentGridTitle.setManaged(true);
        yourGridTitle.setVisible(true);
        yourGridTitle.setManaged(true);
    }

}
