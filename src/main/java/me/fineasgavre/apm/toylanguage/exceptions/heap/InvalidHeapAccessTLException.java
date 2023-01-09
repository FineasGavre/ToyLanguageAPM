package me.fineasgavre.apm.toylanguage.exceptions.heap;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class InvalidHeapAccessTLException extends TLException {
    public InvalidHeapAccessTLException() {
        super("Attempted to access an invalid heap address.");
    }
}
