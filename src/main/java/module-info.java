module es.iesfranciscodelosrios.com.juancarlos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;

    opens es.iesfranciscodelosrios.com.juancarlos to javafx.fxml;
    exports es.iesfranciscodelosrios.com.juancarlos;
}
