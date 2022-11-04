package me.fineasgavre.apm.toylanguage.Controller;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Exceptions.Execution.CurrentProgramStateNotConfiguredTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.Execution.EmptyExecutionStackTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;
import me.fineasgavre.apm.toylanguage.Repository.Interfaces.IProgramStateRepository;
import me.fineasgavre.apm.toylanguage.Repository.SingleThreadProgramStateRepository;
import me.fineasgavre.apm.toylanguage.Utils.PrintUtils;

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

        while (!currentProgramState.getExecutionStack().isEmpty()) {
            currentProgramState = executeOneStep(currentProgramState);
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

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }
}
