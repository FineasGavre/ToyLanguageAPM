package me.fineasgavre.apm.toylanguage.Exceptions.Execution;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class CurrentProgramStateNotConfiguredTLException extends TLException {
    public CurrentProgramStateNotConfiguredTLException() {
        super("Attempted to execute current ProgramState while no current ProgramState was configured.");
    }
}
