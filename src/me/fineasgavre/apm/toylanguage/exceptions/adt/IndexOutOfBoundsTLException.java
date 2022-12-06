package me.fineasgavre.apm.toylanguage.exceptions.adt;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class IndexOutOfBoundsTLException extends TLException {
    public IndexOutOfBoundsTLException(int index, int size) {
        super("Index " + index + " is out of bounds. Collection size = " + size + ".");
    }
}
