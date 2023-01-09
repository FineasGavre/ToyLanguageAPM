package me.fineasgavre.apm.toylanguage.view.jfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.fineasgavre.apm.toylanguage.controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.view.jfx.models.HeapItem;
import me.fineasgavre.apm.toylanguage.view.jfx.models.LoadedProgram;
import me.fineasgavre.apm.toylanguage.view.jfx.models.OutputItem;

public class ProgramRunController {
    @FXML
    private TableView heapTable;

    @FXML
    private TableView outputTable;

    @FXML
    private Button executeStepButton;

    private LoadedProgram program;
    private ProgramStateController programStateController = new ProgramStateController();

    public void setProgram(LoadedProgram program) {
        this.program = program;

        programStateController.setDebugMode(true);
        programStateController.setCurrentProgramFromStatement(program.getStatement());
    }

    @FXML
    private void executeOneStep() {
        programStateController.oneStepForAllProgramStatesWithGarbageCollection();

        updateState();
    }

    private void updateState() {
        var isProgramStatesEmpty = !programStateController.hasRunnableProgramStates();

        if (isProgramStatesEmpty) {
            executeStepButton.setDisable(true);
            return;
        }

        var heapTable = programStateController.getProgramStates().stream().findFirst().get().getHeap();
        updateHeapTable(heapTable);

        var outputTable = programStateController.getProgramStates().stream().findFirst().get().getOutput();
        updateOutputTable(outputTable);
    }

    private void updateHeapTable(ITLHeap<IValue> heap) {
        heapTable.getItems().clear();
        heapTable.getColumns().clear();

        var addressColumn = new TableColumn<HeapItem, String>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        var valueColumn = new TableColumn<HeapItem, String>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        var typeColumn = new TableColumn<HeapItem, String>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        heapTable.getColumns().addAll(addressColumn, valueColumn, typeColumn);

        var items = heap.getContent().getMap().entrySet().stream().map(e -> new HeapItem(e.getKey().toString(), e.getValue().toString(), e.getValue().getType().toString())).toList();
        heapTable.getItems().addAll(items);
    }

    private void updateOutputTable(ITLList<IValue> output) {
        outputTable.getItems().clear();
        outputTable.getColumns().clear();

        var valueColumn = new TableColumn<OutputItem, String>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        outputTable.getColumns().add(valueColumn);

        var items = output.getList().stream().map(e -> new OutputItem(e.toString())).toList();
        outputTable.getItems().addAll(items);
    }
}
