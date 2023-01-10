package me.fineasgavre.apm.toylanguage.domain.state.snapshot;

import java.util.List;
import java.util.Map;

public class ProgramStateSnapshot {
    private final int programStateId;
    private final List<String> executionStackSnapshot;
    private final List<PairItem<String>> symbolTableSnapshot;

    public ProgramStateSnapshot(int programStateId, List<String> executionStackSnapshot, List<PairItem<String>> symbolTableSnapshot) {
        this.programStateId = programStateId;
        this.executionStackSnapshot = executionStackSnapshot;
        this.symbolTableSnapshot = symbolTableSnapshot;
    }

    public int getProgramStateId() {
        return programStateId;
    }

    public List<String> getExecutionStackSnapshot() {
        return executionStackSnapshot;
    }

    public List<PairItem<String>> getSymbolTableSnapshot() {
        return symbolTableSnapshot;
    }
}
