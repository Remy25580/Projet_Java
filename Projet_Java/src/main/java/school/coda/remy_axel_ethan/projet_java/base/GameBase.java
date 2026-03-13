package school.coda.remy_axel_ethan.projet_java.base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.events.DataBase;

import java.io.IOException;

public class GameBase extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataBase db = new DataBase();
        db.createDb();

        FXMLLoader fxmlLoader = new FXMLLoader(GameBase.class.getResource("base-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Bataille Javale");
        stage.setScene(scene);
        stage.show();
    }
}
