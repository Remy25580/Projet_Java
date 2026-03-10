package school.coda.remy_axel_ethan.projet_java.in_game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.placement.BoatPlacement;

import java.io.IOException;

public class InGame {

    public void initGame(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoatPlacement.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Bataille Javale");
        stage.setScene(scene);
    }

}
