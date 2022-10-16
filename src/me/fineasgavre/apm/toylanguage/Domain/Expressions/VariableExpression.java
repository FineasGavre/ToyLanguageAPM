package me.fineasgavre.apm.toylanguage.Domain.Expressions;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;
import me.fineasgavre.apm.toylanguage.Exceptions.Expression.UnknownVariableInExpressionTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class VariableExpression implements IExpression {
    private String variableId;

    public VariableExpression(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public IValue evaluate(ITLMap<String, IValue> symbolTable) throws TLException {
        if (!symbolTable.containsKey(this.variableId)) {
            throw new UnknownVariableInExpressionTLException(this.variableId);
        }

        return symbolTable.get(this.variableId);
    }

    @Override
    public IExpression clone() {
        try {
            var clone = (VariableExpression) super.clone();
            clone.variableId = this.variableId + "";
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "VariableExpression{" +
                "variableId='" + variableId + '\'' +
                '}';
    }
}
