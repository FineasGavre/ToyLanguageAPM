package me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces;

import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;

public interface IValue extends Cloneable {
    IType getType();

    IValue clone();
}
