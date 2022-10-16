package me.fineasgavre.apm.toylanguage.Domain.Expressions;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

public class ValueExpression implements IExpression {
    private IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(ITLMap<String, IValue> symbolTable) {
        return value;
    }

    @Override
    public IExpression clone() {
        try {
            var clone = (ValueExpression) super.clone();
            clone.value = this.value.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "(" + this.value + ")";
    }
}
