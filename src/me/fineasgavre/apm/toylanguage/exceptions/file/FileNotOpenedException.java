package me.fineasgavre.apm.toylanguage.exceptions.file;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class FileNotOpenedException extends TLException {
    public FileNotOpenedException() {
        super("Attempted to close a file that is not open.");
    }
}
