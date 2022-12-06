package me.fineasgavre.apm.toylanguage.controller;

import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.values.RefValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.execution.CurrentProgramStateNotConfiguredTLException;
import me.fineasgavre.apm.toylanguage.exceptions.execution.EmptyExecutionStackTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.repository.interfaces.IProgramStateRepository;
import me.fineasgavre.apm.toylanguage.repository.SingleThreadProgramStateRepository;
import me.fineasgavre.apm.toylanguage.utils.PrintUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramStateController {
    private final IProgramStateRepository programStateRepository;
    private boolean isDebugMode = false;

    public ProgramStateController() {
        this.programStateRepository = new SingleThreadProgramStateRepository();
    }

    public void setCurrentProgramState(ProgramState programState) {
        this.programStateRepository.setCurrentProgramState(programState);
    }

    public void setCurrentProgramFromStatement(IStatement statement) {
        var programState = new ProgramState(statement);
        this.programStateRepository.setCurrentProgramState(programState);
    }

    public void executeCurrentUntilEmpty() throws TLException {
        if (!this.programStateRepository.hasCurrentProgramState()){
            throw new CurrentProgramStateNotConfiguredTLException();
        }

        var currentProgramState = programStateRepository.getCurrentProgramState();
        programStateRepository.logProgramStateExecution(currentProgramState);

        while (!currentProgramState.getExecutionStack().isEmpty()) {
            currentProgramState = executeOneStep(currentProgramState);

            programStateRepository.logProgramStateExecution(currentProgramState);
            currentProgramState.getHeap().setContent(conservativeGarbageCollection(getHeapAddressesFromSymbolTable(currentProgramState.getSymbolTable()), currentProgramState.getHeap()));
            programStateRepository.logProgramStateExecution(currentProgramState);

            displayProgramState(currentProgramState);
        }
    }

    public void executeOneStepForCurrent() throws TLException {
        if (!this.programStateRepository.hasCurrentProgramState()){
            throw new CurrentProgramStateNotConfiguredTLException();
        }

        var currentProgramState = programStateRepository.getCurrentProgramState();
        currentProgramState = executeOneStep(currentProgramState);
        displayProgramState(currentProgramState);
    }

    private void displayProgramState(ProgramState programState) {
        if (this.isDebugMode) {
            PrintUtils.printProgramState(programState, false);
        }
    }

    private ProgramState executeOneStep(ProgramState programState) throws TLException {
        var executionStack = programState.getExecutionStack();

        if (executionStack.isEmpty()) {
            throw new EmptyExecutionStackTLException();
        }

        var currentStatement = executionStack.pop();
        return currentStatement.execute(programState);
    }

    public ProgramState getCurrentProgramState() {
        return this.programStateRepository.getCurrentProgramState();
    }

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }

    private ITLMap<Integer, IValue> conservativeGarbageCollection(List<Integer> symbolTableAddresses, ITLHeap<IValue> heap) {
        var heapReferencedAddresses = heap.getContent().getMap().values().stream()
                .filter(e -> e instanceof RefValue)
                .map(e -> ((RefValue) e).getAddress())
                .collect(Collectors.toList());
        var cleanedHeapContent =  heap.getContent().getMap().entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()) || heapReferencedAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var map = new TLMap<Integer, IValue>();
        map.setMap(cleanedHeapContent);
        return map;
    }

    private List<Integer> getHeapAddressesFromSymbolTable(ITLMap<String, IValue> symbolTable) {
        return symbolTable.getMap().values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }
}
