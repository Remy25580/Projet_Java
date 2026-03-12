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
import school.coda.remy_axel_ethan.projet_java.game.placement.BoardRules;
import school.coda.remy_axel_ethan.projet_java.game.placement.CreationBoat;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import java.net.URL;
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

    BoardRules rules = new BoardRules();
    private Boat selectedBoat = null;
    private Button currentBoatButton;

    private int nbBoatPlaced = 0;

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

                    CreationBoat creation = new CreationBoat(selectedBoat, rules, currentBoatButton, grid, selectedBoatLabel, gameStartButton, nbBoatPlaced);

                    if (creation.tryPlacement(target)) {
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
        opponentGrid.setVisible(true);
        opponentGrid.setManaged(true);
        opponentGridTitle.setVisible(true);
        opponentGridTitle.setManaged(true);
        yourGridTitle.setVisible(true);
        yourGridTitle.setManaged(true);
    }

//    public void shoot(Case target){
//        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
//        if (target.getTouched()){
//            caseTouchedErrorMessage.setText("Cette case a déjà été touchée !!");
//            pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
//            pause.play();
//            return;
//        }
//        target.changeTouched();
//        if(target.getOccupiedBy() != null){
//            target.getOccupiedBy().receiveDamage();
//            getButtonFromACase(target).setStyle("-fx-background-color: red;" +
//                    "-fx-border-color: black;" +
//                    "-fx-border-radius: 0;");
//            caseTouchedErrorMessage.setText(target.getOccupiedBy().getType() + " touché !");
//            pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
//            pause.play();
//        }else{
//            target.getOccupiedBy().receiveDamage();
//            getButtonFromACase(target).setStyle("-fx-background-color: gray;" +
//                    "-fx-border-color: black;" +
//                    "-fx-border-radius: 0;");
//            caseTouchedErrorMessage.setText("Aucune cible touchée . . .");
//            pause.setOnFinished(_ -> caseTouchedErrorMessage.setText(""));
//            pause.play();
//        }
//    }
}
