package me.fineasgavre.apm.toylanguage.Repository.Interfaces;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;

public interface IProgramStateRepository {
    ProgramState getCurrentProgramState();

    void setCurrentProgramState(ProgramState programState);

    boolean hasCurrentProgramState();
}
