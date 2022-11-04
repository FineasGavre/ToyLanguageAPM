package me.fineasgavre.apm.toylanguage.Exceptions.Statement;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class VariableAlreadyDeclaredTLException extends TLException {
    public VariableAlreadyDeclaredTLException(String variableId) {
        super("Attempted to declare variable (" + variableId + ") which was previously declared.");
    }
}
