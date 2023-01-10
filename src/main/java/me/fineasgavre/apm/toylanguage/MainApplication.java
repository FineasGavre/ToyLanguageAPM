package me.fineasgavre.apm.toylanguage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.fineasgavre.apm.toylanguage.view.jfx.ProgramListController;

import java.io.IOException;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        var resource = MainApplication.class.getResource("program-select.fxml");
        var fxmlLoader = new FXMLLoader(resource);

        var root = (Parent) fxmlLoader.load();
        var controller = (ProgramListController) fxmlLoader.getController();
        controller.setStage(stage);

        var scene = new Scene(root, 600, 400);
        stage.setTitle("ToyLanguage APM");
        stage.setScene(scene);
        stage.show();
    }
}
