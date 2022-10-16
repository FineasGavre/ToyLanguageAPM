package me.fineasgavre.apm.toylanguage.Exceptions.Expression;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class InvalidExpressionOperandTLException extends TLException {
    public InvalidExpressionOperandTLException(IType expectedType, IType receivedType) {
        super("Invalid expression operand encountered during execution. Received " + receivedType.toString() + " while expecting " + expectedType.toString() + ".");
    }
}
