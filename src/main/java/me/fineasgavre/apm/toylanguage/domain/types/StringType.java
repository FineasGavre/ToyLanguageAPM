package me.fineasgavre.apm.toylanguage.domain.types;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;

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
