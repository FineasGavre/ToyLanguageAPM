package me.fineasgavre.apm.toylanguage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        var resource = MainApplication.class.getResource("program-select.fxml");
        var fxmlLoader = new FXMLLoader(resource);
        var scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("ToyLanguage APM");
        stage.setScene(scene);
        stage.show();
    }
}
