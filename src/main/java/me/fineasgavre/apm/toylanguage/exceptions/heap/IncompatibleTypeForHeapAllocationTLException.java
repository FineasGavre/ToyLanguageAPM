package me.fineasgavre.apm.toylanguage.exceptions.heap;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class IncompatibleTypeForHeapAllocationTLException extends TLException {
    public IncompatibleTypeForHeapAllocationTLException(IType expected, IType actual) {
        super("Attempted to allocate value to heap because of type mismatch. (Expected: " + expected + "; Actual: " + actual + ")");
    }
}
