package me.fineasgavre.apm.toylanguage.Domain.Types;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.StringValue;

public class StringType implements IType {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public IValue getInitialValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }
}
