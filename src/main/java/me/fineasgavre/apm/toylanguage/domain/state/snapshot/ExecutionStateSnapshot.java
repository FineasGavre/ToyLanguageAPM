package me.fineasgavre.apm.toylanguage.domain.state.snapshot;

import java.util.List;
import java.util.Map;

public class ExecutionStateSnapshot {
    private final List<PairItem<String>> heapSnapshot;
    private final List<String> outputSnapshot;
    private final List<String> fileTableSnapshot;
    private final List<ProgramStateSnapshot> programStateSnapshots;

    public ExecutionStateSnapshot(List<PairItem<String>> heapSnapshot, List<String> outputSnapshot, List<String> fileTableSnapshot, List<ProgramStateSnapshot> programStateSnapshots) {
        this.heapSnapshot = heapSnapshot;
        this.outputSnapshot = outputSnapshot;
        this.fileTableSnapshot = fileTableSnapshot;
        this.programStateSnapshots = programStateSnapshots;
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
}
