package me.fineasgavre.apm.toylanguage.exceptions.statement;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class VariableAlreadyDeclaredTLException extends TLException {
    public VariableAlreadyDeclaredTLException(String variableId) {
        super("Attempted to declare variable (" + variableId + ") which was previously declared.");
    }
}
