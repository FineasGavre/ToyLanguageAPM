package me.fineasgavre.apm.toylanguage.domain.types;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;

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
