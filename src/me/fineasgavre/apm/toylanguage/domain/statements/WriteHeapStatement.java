package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.RefType;
import me.fineasgavre.apm.toylanguage.domain.values.RefValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.UnknownVariableInExpressionTLException;
import me.fineasgavre.apm.toylanguage.exceptions.heap.IncompatibleTypeForHeapAllocationTLException;

public class WriteHeapStatement implements IStatement {
    private final String variableId;
    private final IExpression expression;

    public WriteHeapStatement(String variableId, IExpression expression) {
        this.variableId = variableId;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        if (!symbolTable.containsKey(variableId)) {
            throw new UnknownVariableInExpressionTLException(variableId);
        }

        var value = expression.evaluate(symbolTable, heap);

        if (!symbolTable.get(variableId).getType().equals(new RefType(value.getType()))) {
            throw new IncompatibleTypeForHeapAllocationTLException(new RefType(value.getType()), symbolTable.get(variableId).getType());
        }

        var refValue = (RefValue) symbolTable.get(variableId);

        heap.writeToAddress(refValue.getAddress(), value);

        return null;
    }

    @Override
    public String toString() {
        return "(heap) " + variableId + " <- " + expression;
    }

    @Override
    public IStatement clone() {
        try {
            return (IStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
