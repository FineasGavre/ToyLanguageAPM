package me.fineasgavre.apm.toylanguage.repository.interfaces;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;

public interface IProgramStateRepository {
    ProgramState getCurrentProgramState();

    void setCurrentProgramState(ProgramState programState);

    boolean hasCurrentProgramState();

    void logProgramStateExecution(ProgramState programState) throws IOTLException;
}
