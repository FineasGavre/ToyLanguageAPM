package me.fineasgavre.apm.toylanguage.controller;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.exceptions.execution.CurrentProgramStateNotConfiguredTLException;
import me.fineasgavre.apm.toylanguage.exceptions.execution.EmptyExecutionStackTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.repository.interfaces.IProgramStateRepository;
import me.fineasgavre.apm.toylanguage.repository.SingleThreadProgramStateRepository;
import me.fineasgavre.apm.toylanguage.utils.PrintUtils;

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
}
