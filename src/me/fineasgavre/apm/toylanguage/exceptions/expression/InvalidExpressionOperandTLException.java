package me.fineasgavre.apm.toylanguage.exceptions.expression;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class InvalidExpressionOperandTLException extends TLException {
    public InvalidExpressionOperandTLException(IType expectedType, IType receivedType) {
        super("Invalid expression operand encountered during execution. Received " + receivedType.toString() + " while expecting " + expectedType.toString() + ".");
    }
}
