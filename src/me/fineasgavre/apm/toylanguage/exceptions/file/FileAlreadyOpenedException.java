package me.fineasgavre.apm.toylanguage.exceptions.file;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class FileAlreadyOpenedException extends TLException {
    public FileAlreadyOpenedException() {
        super("Attempted to open an already opened file.");
    }
}
