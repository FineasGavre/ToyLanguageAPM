package me.fineasgavre.apm.toylanguage.Exceptions.File;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class FileAlreadyOpenedException extends TLException {
    public FileAlreadyOpenedException() {
        super("Attempted to open an already opened file.");
    }
}
