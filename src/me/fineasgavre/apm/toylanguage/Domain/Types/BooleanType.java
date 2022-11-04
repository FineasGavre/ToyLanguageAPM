package me.fineasgavre.apm.toylanguage.Domain.Types;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Values.BooleanValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

public class BooleanType implements IType {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

    @Override
    public IValue getInitialValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "boolean";
    }
}
