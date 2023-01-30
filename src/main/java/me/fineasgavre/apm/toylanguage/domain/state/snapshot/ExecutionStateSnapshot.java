package me.fineasgavre.apm.toylanguage.domain.state.snapshot;

import java.util.List;

public class ExecutionStateSnapshot {
    private final List<PairItem<String>> heapSnapshot;
    private final List<String> outputSnapshot;
    private final List<String> fileTableSnapshot;
    private final List<ProgramStateSnapshot> programStateSnapshots;
    private final List<PairItem<String>> procedureTableSnapshot;

    public ExecutionStateSnapshot(List<PairItem<String>> heapSnapshot, List<String> outputSnapshot, List<String> fileTableSnapshot, List<ProgramStateSnapshot> programStateSnapshots, List<PairItem<String>> procedureTableSnapshot) {
        this.heapSnapshot = heapSnapshot;
        this.outputSnapshot = outputSnapshot;
        this.fileTableSnapshot = fileTableSnapshot;
        this.programStateSnapshots = programStateSnapshots;
        this.procedureTableSnapshot = procedureTableSnapshot;
    }

    public List<PairItem<String>> getHeapSnapshot() {
        return heapSnapshot;
    }

    public List<String> getOutputSnapshot() {
        return outputSnapshot;
    }

    public List<String> getFileTableSnapshot() {
        return fileTableSnapshot;
    }

    public List<ProgramStateSnapshot> getProgramStateSnapshots() {
        return programStateSnapshots;
    }

    public List<PairItem<String>> getProcedureTableSnapshot() {
        return procedureTableSnapshot;
    }
}
