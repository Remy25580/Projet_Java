module school.coda.remy_axel.projet_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens school.coda.remy_axel.projet_java to javafx.fxml;
    exports school.coda.remy_axel.projet_java;
}