package me.fineasgavre.apm.toylanguage.Domain.Values;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Domain.Types.StringType;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

import java.util.Objects;

public class StringValue implements IValue {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public IType getType() {
        return new StringType();
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
