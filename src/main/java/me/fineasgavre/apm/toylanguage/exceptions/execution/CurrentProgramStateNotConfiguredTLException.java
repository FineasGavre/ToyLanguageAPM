package me.fineasgavre.apm.toylanguage.exceptions.execution;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class CurrentProgramStateNotConfiguredTLException extends TLException {
    public CurrentProgramStateNotConfiguredTLException() {
        super("Attempted to execute current ProgramState while no current ProgramState was configured.");
    }
}
