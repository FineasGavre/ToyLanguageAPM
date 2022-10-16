package me.fineasgavre.apm.toylanguage.Exceptions.Statement;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class AssigmentToUndeclaredVariableTLException extends TLException {
    public AssigmentToUndeclaredVariableTLException(String variableId) {
        super("Attempted to assign value to undefined variable (" + variableId + ").");
    }
}
