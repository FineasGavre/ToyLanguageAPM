package me.fineasgavre.apm.toylanguage.domain.values;

import me.fineasgavre.apm.toylanguage.domain.types.RefType;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;

public class RefValue implements IValue {
    private final int address;
    private final IType referencedType;

    public RefValue(int address, IType referencedType) {
        this.address = address;
        this.referencedType = referencedType;
    }

    public int getAddress() {
        return address;
    }

    public IType getReferencedType() {
        return referencedType;
    }

    @Override
    public IType getType() {
        return new RefType(referencedType);
    }

    @Override
    public String toString() {
        return "ref({" + address + "}, " + referencedType + ")";
    }

    @Override
    public IValue clone() {
        try {
            return (IValue) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
