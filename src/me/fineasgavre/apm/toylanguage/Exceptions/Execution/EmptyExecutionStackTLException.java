package me.fineasgavre.apm.toylanguage.Exceptions.Execution;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class EmptyExecutionStackTLException extends TLException {
    public EmptyExecutionStackTLException() {
        super("Attempted to execute from ProgramState while its stack is empty.");
    }
}
