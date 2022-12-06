package me.fineasgavre.apm.toylanguage.domain.expressions.interfaces;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public interface IExpression extends Cloneable {
    IValue evaluate(ITLMap<String, IValue> symbolTable) throws TLException;

    IExpression clone();
}
