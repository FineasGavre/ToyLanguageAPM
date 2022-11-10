package me.fineasgavre.apm.toylanguage.Domain.Values;

import me.fineasgavre.apm.toylanguage.Domain.Types.BooleanType;
import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

import java.util.Objects;

public class BooleanValue implements IValue {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanValue that = (BooleanValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public IType getType() {
        return new BooleanType();
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
