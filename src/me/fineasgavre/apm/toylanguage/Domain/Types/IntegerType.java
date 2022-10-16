package me.fineasgavre.apm.toylanguage.Domain.Types;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Values.IntegerValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

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
