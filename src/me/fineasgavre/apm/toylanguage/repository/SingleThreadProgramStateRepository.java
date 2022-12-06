package me.fineasgavre.apm.toylanguage.repository;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;
import me.fineasgavre.apm.toylanguage.repository.interfaces.IProgramStateRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SingleThreadProgramStateRepository implements IProgramStateRepository {
    private ProgramState programState;
    private boolean isFirstFileLog = true;

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
            logFile.write("\n\n== STATE LOG \n");
            logFile.write("ExeStack:\n" + programState.getExecutionStack());
            logFile.write("\n\nSymTable:\n" + programState.getSymbolTable());
            logFile.write("\n\nFileTable:\n" + programState.getFileTable());
            logFile.write("\n\nOutput:\n" + programState.getOutput());
            logFile.close();
        } catch (IOException exception) {
            throw new IOTLException();
        }
    }
}
