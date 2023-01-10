package me.fineasgavre.apm.toylanguage.domain.types;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;

public class IntegerType implements IType {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }

    @Override
    public IValue getInitialValue() {
        return new IntegerValue(0);
    }

    @Override
    public String toString() {
        return "integer";
    }
}
