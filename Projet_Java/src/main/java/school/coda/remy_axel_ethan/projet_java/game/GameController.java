package school.coda.remy_axel_ethan.projet_java.game;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.boat.BoatType;
import school.coda.remy_axel_ethan.projet_java.game.placement.BoardRules;
import school.coda.remy_axel_ethan.projet_java.game.placement.CreationBoat;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static school.coda.remy_axel_ethan.projet_java.tools.Grille.*;

public class GameController implements Initializable {
    @FXML
    private GridPane grid;
    @FXML
    private Label selectedBoatLabel;
    @FXML
    private Button orientationButton;
    @FXML
    private Button PATROUILLEUR, SOUS_MARIN, DESTROYER, CUIRASSE, PORTE_AVION;
    @FXML
    private Button gameStartButton;
    @FXML
    private GridPane opponentGrid;
    @FXML
    private Label opponentGridTitle;
    @FXML
    private  Label yourGridTitle;
    @FXML
    private Label caseTouchedErrorMessage;
    @FXML
    protected Button resetButton;


    BoardRules rules = new BoardRules();
    CreationBoat creation;
    private Boat selectedBoat = null;
    private Button currentBoatButton;

    private int nbBoatPlaced = 0;

    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rules.cases = createGrid(grid);
        for (Node child : grid.getChildren()) {
            Button button = (Button) child;
            button.setOnMouseClicked(e -> {
                if(selectedBoat != null) {
                    Button targetButton = (Button)e.getTarget();
                    int x = (int) targetButton.getProperties().get(CASE_X_POSITION);
                    int y = (int) targetButton.getProperties().get(CASE_Y_POSITION);
                    Case target = rules.cases[x][y];

                    creation = new CreationBoat(selectedBoat, rules, grid);

                    if (creation.tryPlacement(target)) {
                        currentBoatButton.setDisable(true);
                        selectedBoatLabel.setText("Bateau sélectionné : aucun");
                        nbBoatPlaced++;
                        selectedBoat = null;
                        currentBoatButton = null;

                        if(nbBoatPlaced == 5) {
                            gameStartButton.setDisable(false);
                        }
                    }
                }
            });
        }
    }

    public void resultShoot(String message) {
        caseTouchedErrorMessage.setText(message);
        pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
        pause.play();
    }

    public void updateColorCase(boolean touchedBoat, Case target){
        if(touchedBoat){
            creation.getButtonFromACase(target).setStyle("-fx-background-color: red;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 0;");
            return;
        }
        creation.getButtonFromACase(target).setStyle("-fx-background-color: gray;" +
                "-fx-border-color: black;" +
                "-fx-border-radius: 0;");
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
        rules.isHorizontal = !rules.isHorizontal;
        if (rules.isHorizontal) {
            orientationButton.setText("Orientation: Horizontale");
            return;
        }
        orientationButton.setText("Orientation: Verticale");
    }

    @FXML
    public void resetGame() {
        for (Case[] row : rules.cases) {
            for (Case c : row) {
                c.setOccupiedBy(null);
            }
        }

        grid.getChildren().forEach(node -> node.setStyle("-fx-background-color: blue;-fx-border-color: black;-fx-border-radius: 0;"));

        List.of(PATROUILLEUR, SOUS_MARIN, DESTROYER, CUIRASSE, PORTE_AVION).forEach(btn -> btn.setDisable(false));

        nbBoatPlaced = 0;
        selectedBoat = null;
        currentBoatButton = null;
        selectedBoatLabel.setText("Bateau sélectionné : aucun");
        gameStartButton.setDisable(true);
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
        opponentGrid.setVisible(true);
        opponentGrid.setManaged(true);
        opponentGridTitle.setVisible(true);
        opponentGridTitle.setManaged(true);
        yourGridTitle.setVisible(true);
        yourGridTitle.setManaged(true);
    }
}
