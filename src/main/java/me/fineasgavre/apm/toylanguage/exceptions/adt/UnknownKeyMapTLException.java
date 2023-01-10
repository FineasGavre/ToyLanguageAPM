package me.fineasgavre.apm.toylanguage.exceptions.adt;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class UnknownKeyMapTLException extends TLException {
    public UnknownKeyMapTLException(String key) {
        super("Unknown key (" + key + ") access attempted.");
    }
}
