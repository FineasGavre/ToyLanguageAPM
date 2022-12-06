package me.fineasgavre.apm.toylanguage.exceptions.statement;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class AssignmentToDifferentVariableTypeTLException extends TLException {
    public AssignmentToDifferentVariableTypeTLException(IType expectedType, IType actualType) {
        super("Attempted to assign value with type " + expectedType + " to variable with type " + actualType + ".");
    }
}
