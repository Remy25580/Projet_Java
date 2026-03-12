package school.coda.remy_axel_ethan.projet_java.base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.game.placement.BoatPlacement;

import java.io.IOException;

public class BaseController {

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        BoatPlacement placement = new BoatPlacement();
        placement.initPlacement(stage);
    }
}
