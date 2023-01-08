package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.RefType;
import me.fineasgavre.apm.toylanguage.domain.values.RefValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.heap.IncompatibleTypeForHeapAllocationTLException;
import me.fineasgavre.apm.toylanguage.exceptions.statement.AssigmentToUndeclaredVariableTLException;

public class AllocateHeapStatement implements IStatement {
    private final String variableId;
    private final IExpression expression;

    public AllocateHeapStatement(String variableId, IExpression expression) {
        this.variableId = variableId;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        if (!symbolTable.containsKey(this.variableId)) {
            throw new AssigmentToUndeclaredVariableTLException(this.variableId);
        }

        var newValue = this.expression.evaluate(symbolTable, heap);

        if (!symbolTable.get(this.variableId).getType().equals(new RefType(newValue.getType()))) {
            throw new IncompatibleTypeForHeapAllocationTLException(symbolTable.get(this.variableId).getType(), new RefType(newValue.getType()));
        }

        var newHeapEntry = heap.allocateNewForValue(newValue);
        symbolTable.put(this.variableId, new RefValue(newHeapEntry.getKey(), newHeapEntry.getValue().getType()));

        return null;
    }

    @Override
    public String toString() {
        return "alloc(" + variableId + ", " + expression + ")";
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
