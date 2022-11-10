package me.fineasgavre.apm.toylanguage.Domain.Values;

import me.fineasgavre.apm.toylanguage.Domain.Types.IntegerType;
import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

import java.util.Objects;

public class IntegerValue implements IValue {
    private int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerValue that = (IntegerValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public IType getType() {
        return new IntegerType();
    }

    @Override
    public String toString() {
        return "" + value;
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
