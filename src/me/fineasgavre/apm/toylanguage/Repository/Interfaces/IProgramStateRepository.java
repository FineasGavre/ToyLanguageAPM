package me.fineasgavre.apm.toylanguage.Repository.Interfaces;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Exceptions.Execution.IOTLException;

public interface IProgramStateRepository {
    ProgramState getCurrentProgramState();

    void setCurrentProgramState(ProgramState programState);

    boolean hasCurrentProgramState();

    void logProgramStateExecution(ProgramState programState) throws IOTLException;
}
