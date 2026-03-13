package school.coda.remy_axel_ethan.projet_java.base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.game.InitGame;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;

import java.io.IOException;

public class BaseController {

    @FXML
    private TextField sizeInput;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        updateGridSize();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        InitGame placement = new InitGame();
        placement.initPlacement(stage);
    }

    private void updateGridSize() {
        if (!sizeInput.getText().isEmpty()) {
            Grille.GRID_SIZE = Integer.parseInt(sizeInput.getText());
        }
    }
}