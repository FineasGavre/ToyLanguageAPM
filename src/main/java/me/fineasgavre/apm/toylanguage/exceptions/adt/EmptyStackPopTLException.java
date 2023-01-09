package me.fineasgavre.apm.toylanguage.exceptions.adt;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class EmptyStackPopTLException extends TLException {
    public EmptyStackPopTLException() {
        super("Attempted to pop from an empty stack.");
    }
}
