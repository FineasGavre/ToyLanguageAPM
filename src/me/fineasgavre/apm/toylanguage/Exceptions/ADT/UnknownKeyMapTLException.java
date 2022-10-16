package me.fineasgavre.apm.toylanguage.Exceptions.ADT;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class UnknownKeyMapTLException extends TLException {
    public UnknownKeyMapTLException(String key) {
        super("Unknown key (" + key + ") access attempted.");
    }
}
