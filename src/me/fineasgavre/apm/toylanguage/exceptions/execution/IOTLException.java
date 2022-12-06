package me.fineasgavre.apm.toylanguage.exceptions.execution;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class IOTLException extends TLException {
    public IOTLException() {
        super("An IO exception has occurred.");
    }
}
