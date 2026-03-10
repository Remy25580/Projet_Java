package school.coda.remy_axel_ethan.projet_java.placement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import school.coda.remy_axel_ethan.projet_java.tools.Case;

import java.io.IOException;

public class BoatPlacement{
    public void placement(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoatPlacement.class.getResource("placement-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Bataille Javale");

        GridPane grid = new GridPane();
        Case[][] grille = new Case[10][10];
        for(int x=1; x < 11; x++){
            for(int y=1; y<11; y++){
                Button btn = new Button();
                grid.add(btn, x, y);
                grille[x][y] = new Case(x, y);
                int finalX = x;
                int finalY = y;
                btn.setOnMouseClicked(_ -> {
                    PlacementController controller = new PlacementController();
                    Case target = controller.getACase(finalX, finalY);
                    controller.test(target);
                });
            }
        }
        scene.



        stage.setScene(scene);
    }
}
