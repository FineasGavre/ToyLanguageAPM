package me.fineasgavre.apm.toylanguage.controller;

import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.values.RefValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;
import me.fineasgavre.apm.toylanguage.repository.ProgramStateRepository;
import me.fineasgavre.apm.toylanguage.repository.interfaces.IProgramStateRepository;
import me.fineasgavre.apm.toylanguage.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ProgramStateController {
    private ExecutorService executorService;
    private final IProgramStateRepository programStateRepository;
    private boolean isDebugMode = false;

    public ProgramStateController() {
        this.programStateRepository = new ProgramStateRepository();
    }

    public void setCurrentProgramFromStatement(IStatement statement) {
        var programState = new ProgramState(statement);
        this.programStateRepository.addProgramState(programState);
    }

    public void allStep() {
        executorService = Executors.newFixedThreadPool(2);

        var programStates = runningProgramStates(programStateRepository.getProgramStates());
        while (programStates.size() > 0) {
            var referencedAddresses = getHeapAddressesFromSymbolTables(programStates.stream().map(e -> e.getSymbolTable()).toList());
            var firstHeap = programStates.stream().findFirst().get().getHeap();

            firstHeap.setContent(conservativeGarbageCollection(referencedAddresses, firstHeap));

            try {
                oneStepForAllProgramStates(programStates);
            } catch (InterruptedException e) {
                PrintUtils.printTLException(new TLException("All Step execution error."));
            }
            programStates = runningProgramStates(programStateRepository.getProgramStates());
        }

        executorService.shutdownNow();
        programStateRepository.setProgramStates(programStates);
    }

    private void oneStepForAllProgramStates(List<ProgramState> programStates) throws InterruptedException {
        programStates.forEach(p -> {
            try {
                programStateRepository.logProgramStateExecution(p);
                displayProgramState(p);
            } catch (IOTLException e) {
                PrintUtils.printTLException(e);
            }
        });

        var callables = programStates.stream()
                .map(p -> (Callable<ProgramState>)(() -> {
                    try {
                       return p.executeOneStep();
                    } catch (TLException e) {
                        PrintUtils.printTLException(e);
                    }

                    return null;
                }))
                .toList();

        var newProgramStates = executorService.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        PrintUtils.printTLException(new TLException("Future execution exception."));
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        var combinedStates = new ArrayList<ProgramState>();
        combinedStates.addAll(programStates);
        combinedStates.addAll(newProgramStates);

        programStateRepository.setProgramStates(combinedStates);

        combinedStates.forEach(p -> {
            try {
                programStateRepository.logProgramStateExecution(p);
                displayProgramState(p);
            } catch (IOTLException e) {
                PrintUtils.printTLException(e);
            }
        });
    }

    private List<ProgramState> runningProgramStates(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .toList();
    }

    private void displayProgramState(ProgramState programState) {
        if (this.isDebugMode) {
            PrintUtils.printProgramState(programState, false);
        }
    }

    public List<ProgramState> getProgramStates() {
        return this.programStateRepository.getProgramStates();
    }

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }

    private ITLMap<Integer, IValue> conservativeGarbageCollection(List<Integer> symbolTableAddresses, ITLHeap<IValue> heap) {
        var heapReferencedAddresses = heap.getContent().getMap().values().stream()
                .filter(e -> e instanceof RefValue)
                .map(e -> ((RefValue) e).getAddress())
                .toList();
        var cleanedHeapContent =  heap.getContent().getMap().entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()) || heapReferencedAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var map = new TLMap<Integer, IValue>();
        map.setMap(cleanedHeapContent);
        return map;
    }

    private List<Integer> getHeapAddressesFromSymbolTable(ITLMap<String, IValue> symbolTable) {
        return symbolTable.getMap().values().stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .toList();
    }

    private List<Integer> getHeapAddressesFromSymbolTables(List<ITLMap<String, IValue>> symbolTables) {
        var addresses = new ArrayList<Integer>();

        symbolTables.forEach(st -> addresses.addAll(getHeapAddressesFromSymbolTable(st)));

        return addresses;
    }
}
