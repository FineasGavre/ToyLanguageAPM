package me.fineasgavre.apm.toylanguage.exceptions.execution;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class EmptyExecutionStackTLException extends TLException {
    public EmptyExecutionStackTLException() {
        super("Attempted to execute from ProgramState while its stack is empty.");
    }
}
