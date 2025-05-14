module es.iesfranciscodelosrios.com.juancarlos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;

    requires jakarta.xml.bind;
    requires java.xml.bind;

    opens es.iesfranciscodelosrios.com.juancarlos to javafx.fxml;
    opens es.iesfranciscodelosrios.com.juancarlos.connection to java.xml.bind;
    exports es.iesfranciscodelosrios.com.juancarlos.model;
    exports es.iesfranciscodelosrios.com.juancarlos;
    opens es.iesfranciscodelosrios.com.juancarlos.view;
    exports es.iesfranciscodelosrios.com.juancarlos.view;
    exports es.iesfranciscodelosrios.com.juancarlos.test;
    opens es.iesfranciscodelosrios.com.juancarlos.test to javafx.fxml;
}
