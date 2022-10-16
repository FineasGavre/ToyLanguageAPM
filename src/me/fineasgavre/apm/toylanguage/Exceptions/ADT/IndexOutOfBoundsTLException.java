package me.fineasgavre.apm.toylanguage.Exceptions.ADT;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class IndexOutOfBoundsTLException extends TLException {
    public IndexOutOfBoundsTLException(int index, int size) {
        super("Index " + index + " is out of bounds. Collection size = " + size + ".");
    }
}
