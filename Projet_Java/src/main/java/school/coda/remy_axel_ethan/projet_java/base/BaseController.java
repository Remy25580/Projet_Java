package school.coda.remy_axel_ethan.projet_java.base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.game.InitGame;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;

import java.io.IOException;

public class BaseController {

    @FXML
    private TextField sizeInput;

    @FXML
    protected void launchGame(ActionEvent event) throws IOException {
        if (!updateGridSize()) return;

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        InitGame placement = new InitGame();
        placement.initPlacement(stage);
    }

    private boolean updateGridSize() {
        if (sizeInput.getText().isEmpty()) return true;

        return parseAndValidateSize();
    }

    private boolean parseAndValidateSize() {
        try {
            int size = Integer.parseInt(sizeInput.getText());
            return applySizeConstraints(size);
        } catch (NumberFormatException e) {
            showError("Veuillez entrer un nombre entier valide.");
            return false;
        }
    }

    private boolean applySizeConstraints(int size) {
        if (size > 5 && size <= 26) {
            Grille.GRID_SIZE = size;
            return true;
        }
        showError("La taille doit être entre 6 et 26 (impossible de mettre 5).");
        return false;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de taille");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}