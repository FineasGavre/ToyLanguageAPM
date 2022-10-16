package me.fineasgavre.apm.toylanguage.Domain.Values;

import me.fineasgavre.apm.toylanguage.Domain.Types.IntegerType;
import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

public class IntegerValue implements IValue {
    private int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new IntegerType();
    }

    @Override
    public String toString() {
        return "IntegerValue{" +
                "value=" + value +
                '}';
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
