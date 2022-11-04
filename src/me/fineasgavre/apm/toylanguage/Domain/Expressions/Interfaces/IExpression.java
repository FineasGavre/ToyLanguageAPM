package me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public interface IExpression extends Cloneable {
    IValue evaluate(ITLMap<String, IValue> symbolTable) throws TLException;

    IExpression clone();
}
