module school.coda.remy_axel.projet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens school.coda.remy_axel_ethan.projet_java to javafx.fxml;
    exports school.coda.remy_axel_ethan.projet_java;
    exports school.coda.remy_axel_ethan.projet_java.base;
    opens school.coda.remy_axel_ethan.projet_java.base to javafx.fxml;
    exports school.coda.remy_axel_ethan.projet_java.game.placement;
    opens school.coda.remy_axel_ethan.projet_java.game.placement to javafx.fxml;
    exports school.coda.remy_axel_ethan.projet_java.tools;
    exports school.coda.remy_axel_ethan.projet_java.boat;
    exports school.coda.remy_axel_ethan.projet_java.game;
    opens school.coda.remy_axel_ethan.projet_java.game to javafx.fxml;

}