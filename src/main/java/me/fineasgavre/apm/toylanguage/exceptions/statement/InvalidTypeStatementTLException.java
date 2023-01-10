package me.fineasgavre.apm.toylanguage.exceptions.statement;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class InvalidTypeStatementTLException extends TLException {
    public InvalidTypeStatementTLException(IType expectedType, IType actualType) {
        super("Invalid type encountered while executing statement; expected type " + expectedType + ", received " + actualType + ".");
    }
}
