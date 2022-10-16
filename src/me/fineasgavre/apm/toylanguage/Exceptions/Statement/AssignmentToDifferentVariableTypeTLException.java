package me.fineasgavre.apm.toylanguage.Exceptions.Statement;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class AssignmentToDifferentVariableTypeTLException extends TLException {
    public AssignmentToDifferentVariableTypeTLException(IType expectedType, IType actualType) {
        super("Attempted to assign value with type " + expectedType + " to variable with type " + actualType + ".");
    }
}
