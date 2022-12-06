package me.fineasgavre.apm.toylanguage.domain.expressions;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.expression.UnknownVariableInExpressionTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class VariableExpression implements IExpression {
    private String variableId;

    public VariableExpression(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public IValue evaluate(ITLMap<String, IValue> symbolTable, ITLHeap<IValue> heap) throws TLException {
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
        return "[" + variableId + "]";
    }
}
