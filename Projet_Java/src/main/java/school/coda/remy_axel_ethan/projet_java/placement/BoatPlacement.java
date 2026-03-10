package school.coda.remy_axel_ethan.projet_java.placement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.tools.Case;
import school.coda.remy_axel_ethan.projet_java.tools.Grille;

import java.io.IOException;

public class BoatPlacement{


    public void placement(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoatPlacement.class.getResource("placement-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);


        stage.setTitle("Bataille Javale");


        stage.setScene(scene);
    }




}
