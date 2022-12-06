package me.fineasgavre.apm.toylanguage.exceptions.statement;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class AssigmentToUndeclaredVariableTLException extends TLException {
    public AssigmentToUndeclaredVariableTLException(String variableId) {
        super("Attempted to assign value to undefined variable (" + variableId + ").");
    }
}
