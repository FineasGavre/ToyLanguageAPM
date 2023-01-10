package me.fineasgavre.apm.toylanguage.domain.types;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.RefValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;

public class RefType implements IType {
    private final IType referencedType;

    public RefType(IType referencedType) {
        this.referencedType = referencedType;
    }

    public IType getReferencedType() {
        return referencedType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RefType) {
            return ((RefType) obj).referencedType.equals(referencedType);
        }

        return false;
    }

    @Override
    public IValue getInitialValue() {
        return new RefValue(0, referencedType);
    }

    @Override
    public String toString() {
        return "ref(" + referencedType.toString() + ")";
    }
}
