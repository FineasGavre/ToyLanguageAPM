package me.fineasgavre.apm.toylanguage.Exceptions.Arithmetic;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class DivisionByZeroArithmeticTLException extends TLException {
    public DivisionByZeroArithmeticTLException() {
        super("Division by zero encountered while performing arithmetic computations.");
    }
}
