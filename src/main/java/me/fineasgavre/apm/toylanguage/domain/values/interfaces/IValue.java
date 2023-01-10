package me.fineasgavre.apm.toylanguage.domain.values.interfaces;

import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;

public interface IValue extends Cloneable {
    IType getType();

    IValue clone();
}
