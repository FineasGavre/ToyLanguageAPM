package me.fineasgavre.apm.toylanguage.repository;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;
import me.fineasgavre.apm.toylanguage.repository.interfaces.IProgramStateRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProgramStateRepository implements IProgramStateRepository {
    private List<ProgramState> programStates = new ArrayList<>();
    private boolean isFirstFileLog = true;

    @Override
    public void addProgramState(ProgramState programState) {
        this.programStates.add(programState);
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) throws IOTLException {
        try {
            String logFilePath = "current.log";

            if (this.isFirstFileLog) {
                PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
                logFile.write("Starting Toy Interpreter Log\n");
                logFile.close();
                this.isFirstFileLog = false;
            }

            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write("\n\n== (#" + programState.getId() + ") STATE LOG \n");
            logFile.write("ExeStack:\n" + programState.getExecutionStack());
            logFile.write("\n\nSymTable:\n" + programState.getSymbolTable());
            logFile.write("\n\nHeap:\n" + programState.getHeap());
            logFile.write("\n\nFileTable:\n" + programState.getFileTable());
            logFile.write("\n\nOutput:\n" + programState.getOutput());
            logFile.close();
        } catch (IOException exception) {
            throw new IOTLException();
        }
    }
}
