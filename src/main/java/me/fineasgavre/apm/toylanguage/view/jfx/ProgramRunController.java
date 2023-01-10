package me.fineasgavre.apm.toylanguage.view.jfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import me.fineasgavre.apm.toylanguage.controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.domain.state.snapshot.ExecutionStateSnapshot;
import me.fineasgavre.apm.toylanguage.domain.state.snapshot.PairItem;
import me.fineasgavre.apm.toylanguage.domain.state.snapshot.ProgramStateSnapshot;
import me.fineasgavre.apm.toylanguage.view.jfx.models.LoadedProgram;

import java.util.List;

public class ProgramRunController {
    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private TableView<PairItem<String>> heapTable;

    @FXML
    private TableColumn<PairItem<String>, String> heapTableAddressColumn;

    @FXML
    private TableColumn<PairItem<String>, String> heapTableValueColumn;

    @FXML
    private ListView<String> outputTable;

    @FXML
    private ListView<String> fileTable;

    @FXML
    private ListView<String> programStates;

    @FXML
    private ListView<String> executionStack;

    @FXML
    private TableView<PairItem<String>> symbolTable;

    @FXML
    private TableColumn<PairItem<String>, String> symbolTableIdColumn;

    @FXML
    private TableColumn<PairItem<String>, String> symbolTableValueColumn;

    @FXML
    private Button executeStepButton;

    private LoadedProgram program;
    private ProgramStateController programStateController = new ProgramStateController();

    private ExecutionStateSnapshot latestExecutionStateSnapshot = null;
    private Integer selectedProgramStateId = null;

    @FXML
    private void initialize() {
        heapTableAddressColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        heapTableValueColumn.setCellValueFactory(new PropertyValueFactory<>("second"));
        symbolTableIdColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        symbolTableValueColumn.setCellValueFactory(new PropertyValueFactory<>("second"));

        updateProgramCount(0);
        setupProgramStateIdSelection();
    }

    public void setProgram(LoadedProgram program) {
        this.program = program;

        programStateController.setDebugMode(true);
        programStateController.setCurrentProgramFromStatement(program.getStatement());
    }

    @FXML
    private void executeOneStep() {
        var snapshot = programStateController.oneStepForAllProgramStatesWithGarbageCollection();

        updateState(snapshot);
    }

    private void setupProgramStateIdSelection() {
        programStates.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == null) {
                clearSpecificProgramState();
                return;
            }

            var newProgramStateId = Integer.parseInt(newValue);

            if (selectedProgramStateId == null || newProgramStateId != selectedProgramStateId) {
                selectedProgramStateId = newProgramStateId;
                updateState(latestExecutionStateSnapshot);
            }
        });
    }

    private void updateState(ExecutionStateSnapshot snapshot) {
        latestExecutionStateSnapshot = snapshot;
        var isProgramStatesEmpty = !programStateController.hasRunnableProgramStates();

        if (isProgramStatesEmpty) {
            executeStepButton.setDisable(true);
        }

        updateProgramCount(snapshot.getProgramStateSnapshots().size());
        updateHeapTable(snapshot.getHeapSnapshot());
        updateOutputTable(snapshot.getOutputSnapshot());
        updateFileTable(snapshot.getFileTableSnapshot());
        updateProgramStates(snapshot.getProgramStateSnapshots().stream().map(e -> Integer.toString(e.getProgramStateId())).toList());

        if (selectedProgramStateId != null) {
            var selectedProgramState = snapshot.getProgramStateSnapshots().stream().filter(e -> e.getProgramStateId() == selectedProgramStateId).findFirst();
            selectedProgramState.ifPresent(this::updateSpecificProgramState);
        } else {
            clearSpecificProgramState();
        }
    }

    private void updateProgramCount(int programCount) {
        numberOfProgramStates.setText(Integer.toString(programCount));
    }

    private void updateHeapTable(List<PairItem<String>> heapSnapshot) {
        heapTable.getItems().clear();
        heapTable.getItems().addAll(heapSnapshot);
    }

    private void updateOutputTable(List<String> outputSnapshot) {
        outputTable.getItems().clear();
        outputTable.getItems().addAll(outputSnapshot);
    }

    private void updateFileTable(List<String> fileTableSnapshot) {
        fileTable.getItems().clear();
        fileTable.getItems().addAll(fileTableSnapshot);
    }

    private void updateProgramStates(List<String> programStateIds) {
        programStates.getItems().clear();
        programStates.getItems().addAll(programStateIds);

        if (selectedProgramStateId != null) {
            programStates.getSelectionModel().select(selectedProgramStateId.toString());
        }
    }

    private void updateSpecificProgramState(ProgramStateSnapshot programStateSnapshot) {
        clearSpecificProgramState();

        executionStack.getItems().addAll(programStateSnapshot.getExecutionStackSnapshot());
        symbolTable.getItems().addAll(programStateSnapshot.getSymbolTableSnapshot());
    }

    private void reselectProgramState() {
        if (selectedProgramStateId == null) {
            return;
        }

        for (int i = 0; i < programStates.getItems().size(); i++) {
            if (programStates.getItems().get(i).equals(selectedProgramStateId.toString())) {
                programStates.getSelectionModel().clearAndSelect(i);
            }
        }
    }

    private void clearSpecificProgramState() {
        executionStack.getItems().clear();
        symbolTable.getItems().clear();
    }
}
