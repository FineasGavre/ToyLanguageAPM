package me.fineasgavre.apm.toylanguage.Exceptions.Statement;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class InvalidTypeStatementTLException extends TLException {
    public InvalidTypeStatementTLException(IType expectedType, IType actualType) {
        super("Invalid type encountered while executing statement; expected type " + expectedType + ", received " + actualType + ".");
    }
}
