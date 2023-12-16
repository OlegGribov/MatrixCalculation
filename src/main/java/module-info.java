module com.example.matrixcalculation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    // Подключаем необходимую зависимость, чтобы
    // библиотека log4j была доступна в проекте
    requires org.apache.logging.log4j;

    opens com.example.matrixcalculation to javafx.fxml;
    exports com.example.matrixcalculation;
}