package me.fineasgavre.apm.toylanguage.Exceptions.ADT;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class EmptyStackPopTLException extends TLException {
    public EmptyStackPopTLException() {
        super("Attempted to pop from an empty stack.");
    }
}
