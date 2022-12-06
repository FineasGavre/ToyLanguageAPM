package me.fineasgavre.apm.toylanguage.exceptions.expression;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class UnknownVariableInExpressionTLException extends TLException {
    public UnknownVariableInExpressionTLException(String variableId) {
        super("Unknown variable (" + variableId + ") attempted to be evaluated in expression.");
    }
}
