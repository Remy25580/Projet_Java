package school.coda.remy_axel_ethan.projet_java.game;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import school.coda.remy_axel_ethan.projet_java.base.GameBase;
import school.coda.remy_axel_ethan.projet_java.boat.Boat;
import school.coda.remy_axel_ethan.projet_java.boat.BoatType;
import school.coda.remy_axel_ethan.projet_java.events.Achievements;
import school.coda.remy_axel_ethan.projet_java.events.DataBase;
import school.coda.remy_axel_ethan.projet_java.game.ingame.AiGrid;
import school.coda.remy_axel_ethan.projet_java.game.placement.PlayerPlacement;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import school.coda.remy_axel_ethan.projet_java.tools.SoundManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
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
    @FXML
    private Label endMessage;
    @FXML
    private Button restart;

    private final DataBase db = new DataBase();
    private final Achievements achievements = new Achievements();


    private Case[][] cases;
    private boolean isHorizontal = true;

    private PlayerPlacement playerPlacement;
    private Boat selectedBoat = null;
    private Button currentBoatButton;
    private int nbBoatPlaced = 0;
    private AiGrid aiGrid;
    private Case[][] aiCases;

    private int yourBoats = 5;
    private int aiBoats = 5;

    private int nbPlayerShots = 0;
    private int nbIaShots = 0;

    PauseTransition pause = new PauseTransition(Duration.seconds(1));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cases = createGrid(grid, "player");
        playerPlacement = new PlayerPlacement(cases);
        aiGrid = new AiGrid(opponentGrid, grid,this, cases);

        for (Node child : grid.getChildren()) {
            Button button = (Button) child;
            button.setOnMouseClicked(_ -> handleGridClick(button));
        }
    }
    private void handleGridClick(Button targetButton) {
        if (selectedBoat == null) return;

        int x = (int) targetButton.getProperties().get(CASE_X_POSITION);
        int y = (int) targetButton.getProperties().get(CASE_Y_POSITION);
        Case target = cases[x][y];

        boolean isPlaced = playerPlacement.attemptPlacement(selectedBoat, isHorizontal, target);

        if (isPlaced) {
            refreshGridUI();
            updateUiAfterPlacement();
        }
    }

    private void refreshGridUI() {
        for (Node node : grid.getChildren()) {
            Button btn = (Button) node;
            int x = (int) btn.getProperties().get(CASE_X_POSITION);
            int y = (int) btn.getProperties().get(CASE_Y_POSITION);

            if (cases[x][y].getOccupiedBy() != null) {
                btn.setStyle("-fx-background-color: red;-fx-border-color: black;-fx-border-radius: 0;");
            }
        }
    }

    private void updateUiAfterPlacement() {
        currentBoatButton.setDisable(true);
        selectedBoatLabel.setText("Bateau sélectionné : aucun");
        nbBoatPlaced++;
        selectedBoat = null;
        currentBoatButton = null;

        if (nbBoatPlaced == 5) {
            gameStartButton.setDisable(false);
        }
    }

    public void resultShoot(String message, boolean isSank) {
        if(isSank){message = message + " Coulé !!";}
        caseTouchedErrorMessage.setText(message);
        pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
        pause.play();
    }

    public void addPlayerShot(){nbPlayerShots++;}
    public void addIaShots(){nbIaShots++;}

    public Button getButtonFromACase(Case target, GridPane targetGrid) {
        int xTarget = target.getPos()[0];
        int yTarget = target.getPos()[1];

        for (Node node : targetGrid.getChildren()) {
            Button button = (Button) node;
            int xButton = (int) button.getProperties().get(CASE_X_POSITION);
            int yButton = (int) button.getProperties().get(CASE_Y_POSITION);

            if (xButton == xTarget && yButton == yTarget) {
                return button;
            }
        }
        return null;
    }

    public void updateColorCase(boolean touchedBoat, Case target, GridPane targetGrid){
        if(touchedBoat){
            getButtonFromACase(target, targetGrid).setStyle("-fx-background-color: purple;" +
                    "-fx-border-color: black;" +
                    "-fx-border-radius: 0;");
            return;
        }
        getButtonFromACase(target, targetGrid).setStyle("-fx-background-color: gray;" +
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
        isHorizontal = !isHorizontal;
        if (isHorizontal) {
            orientationButton.setText("Orientation: Horizontale");
            return;
        }
        orientationButton.setText("Orientation: Verticale");
    }

    @FXML
    public void resetGame() {
        for (Case[] row : cases) {
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
    private void initGamePlay() {
        hidePlacementUI();
        aiCases = aiGrid.createAiGrid();
        showInGameUI();
    }

    private void hidePlacementUI() {
        List<Node> nodesToHide = List.of(
                gameStartButton, PATROUILLEUR, SOUS_MARIN, DESTROYER,
                CUIRASSE, PORTE_AVION, resetButton, selectedBoatLabel, orientationButton
        );

        for (Node node : nodesToHide) {
            node.setVisible(false);
            node.setManaged(false);
        }
    }

    private void showInGameUI() {
        List<Node> nodesToShow = List.of(opponentGrid, opponentGridTitle, yourGridTitle);

        for (Node node : nodesToShow) {
            node.setVisible(true);
            node.setManaged(true);
        }
    }

    public void showAchievement(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Récompense Spéciale");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateNumberOfBoats(String owner){
        if(Objects.equals(owner, "player")){
            yourBoats--;
            win();
        }
        aiBoats--;
        win();
    }

    private void win(){
        if(aiBoats == 0){
            SoundManager.playVictory();
            endMessage.setText("Vous avez gagné, bravo!");
            db.putAResult("player", nbPlayerShots, nbIaShots);
            achievements.achievements(nbPlayerShots);
            stopGame();
        } else if (yourBoats == 0) {
            endMessage.setText("Vous avez échoué . . .");
            db.putAResult("IA", nbPlayerShots, nbIaShots);
            stopGame();
        }

    }

    private void stopGame(){
        List<Node> nodesToHide = List.of(grid, opponentGrid, opponentGridTitle, yourGridTitle);
        for (Node node : nodesToHide) {
            node.setVisible(false);
            node.setManaged(false);
        }
        restart.setVisible(true);
        restart.setManaged(true);
        endMessage.setVisible(true);
        endMessage.setManaged(true);
    }

    @FXML
    private void restart(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        GameBase game = new GameBase();
        game.start(stage);
    }
}
