package me.fineasgavre.apm.toylanguage.Repository;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Repository.Interfaces.IProgramStateRepository;

public class SingleThreadProgramStateRepository implements IProgramStateRepository {
    private ProgramState programState;

    @Override
    public ProgramState getCurrentProgramState() {
        return this.programState;
    }

    @Override
    public void setCurrentProgramState(ProgramState programState) {
        this.programState = programState;
    }

    @Override
    public boolean hasCurrentProgramState() {
        return this.programState != null;
    }
}
