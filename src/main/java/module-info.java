module com.example.chatgui32 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires json.simple;

    opens com.example.chatgui32 to javafx.fxml;
    exports com.example.chatgui32;
}