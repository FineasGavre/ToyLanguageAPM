package me.fineasgavre.apm.toylanguage.Exceptions.File;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class FileNotOpenedException extends TLException {
    public FileNotOpenedException() {
        super("Attempted to close a file that is not open.");
    }
}
