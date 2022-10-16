package me.fineasgavre.apm.toylanguage.Exceptions.Expression;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class UnknownVariableInExpressionTLException extends TLException {
    public UnknownVariableInExpressionTLException(String variableId) {
        super("Unknown variable (" + variableId + ") attempted to be evaluated in expression.");
    }
}
