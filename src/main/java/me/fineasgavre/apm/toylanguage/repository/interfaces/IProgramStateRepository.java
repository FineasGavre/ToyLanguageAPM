package me.fineasgavre.apm.toylanguage.repository.interfaces;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;

import java.util.List;

public interface IProgramStateRepository {
    void addProgramState(ProgramState programState);

    List<ProgramState> getProgramStates();

    void setProgramStates(List<ProgramState> programStates);

    void logProgramStateExecution(ProgramState programState) throws IOTLException;
}
