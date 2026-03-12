package school.coda.remy_axel_ethan.projet_java.game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InitGame {
    public void initPlacement(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitGame.class.getResource("placement/placement-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);

        stage.setTitle("Bataille Javale");

        stage.setScene(scene);
    }
}