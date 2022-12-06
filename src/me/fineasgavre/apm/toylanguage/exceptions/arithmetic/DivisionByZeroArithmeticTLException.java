package me.fineasgavre.apm.toylanguage.exceptions.arithmetic;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class DivisionByZeroArithmeticTLException extends TLException {
    public DivisionByZeroArithmeticTLException() {
        super("Division by zero encountered while performing arithmetic computations.");
    }
}
