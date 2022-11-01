module com.example.cs3560a2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.cs3560a2 to javafx.fxml;
    exports com.example.cs3560a2;
}