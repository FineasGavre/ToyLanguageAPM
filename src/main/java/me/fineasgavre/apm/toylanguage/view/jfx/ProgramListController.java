package me.fineasgavre.apm.toylanguage.view.jfx;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.fineasgavre.apm.toylanguage.MainApplication;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.utils.BuiltInPrograms;
import me.fineasgavre.apm.toylanguage.view.jfx.models.LoadedProgram;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgramListController {
    @FXML
    protected TableView<LoadedProgram> programList;

    @FXML
    protected Button startButton;

    @FXML
    protected void initialize() {
        setupTableColumns();
        setupTableData();
        setupTableSelectionListener();
    }

    private void setupTableColumns() {
        var column1 = new TableColumn<LoadedProgram, String>("Program ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("programId"));

        var column2 = new TableColumn<LoadedProgram, String>("Program Code");
        column2.setCellValueFactory(new PropertyValueFactory<>("programCode"));

        var column3 = new TableColumn<LoadedProgram, String>("Type Check");
        column3.setCellValueFactory(new PropertyValueFactory<>("typeCheckResultAsString"));

        programList.getColumns().addAll(column1, column2, column3);
    }

    private void setupTableData() {
        var id = new AtomicInteger(1);

        BuiltInPrograms.programList.forEach(program -> {
            programList.getItems().add(mapStatementToProgram(String.valueOf(id.getAndIncrement()), program));
        });
    }

    private void setupTableSelectionListener() {
        programList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<LoadedProgram>) change -> startButton.setDisable(change.getList().filtered(LoadedProgram::getTypeCheckResult).isEmpty()));
    }

    @FXML
    private void startSelectedApplication() throws IOException {
        var loadedProgram = (LoadedProgram) programList.getSelectionModel().getSelectedItem();

        if (loadedProgram == null) {
            return;
        }

        var resource = MainApplication.class.getResource("program-run.fxml");
        var fxmlLoader = new FXMLLoader(resource);
        var root = (Parent) fxmlLoader.load();

        var controller = (ProgramRunController) fxmlLoader.getController();
        controller.setProgram(loadedProgram);

        var stage = new Stage();
        var scene = new Scene(root);
        stage.setTitle("Running Program " + loadedProgram.getProgramId());
        stage.setScene(scene);
        stage.show();
    }

    private LoadedProgram mapStatementToProgram(String id, IStatement program) {
        return new LoadedProgram(id, program.toString(), program);
    }
}
