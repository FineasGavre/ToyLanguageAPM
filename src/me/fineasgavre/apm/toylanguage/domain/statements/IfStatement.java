package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.BooleanType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.exceptions.statement.InvalidTypeStatementTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class IfStatement implements IStatement {
    private IExpression conditionExpression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression conditionExpression, IStatement thenStatement, IStatement elseStatement) {
        this.conditionExpression = conditionExpression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var executionStack = programState.getExecutionStack();
        var heap = programState.getHeap();
        var symbolTable = programState.getSymbolTable();

        var value = this.conditionExpression.evaluate(symbolTable, heap);

        if (!value.getType().equals(new BooleanType())) {
            throw new InvalidTypeStatementTLException(new BooleanType(), value.getType());
        }

        var unboxedValue = ((BooleanValue) value).getValue();
        executionStack.push(unboxedValue ? this.thenStatement : this.elseStatement);

        return programState;
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (IfStatement) super.clone();
            clone.conditionExpression = this.conditionExpression.clone();
            clone.thenStatement = this.thenStatement.clone();
            clone.elseStatement = this.elseStatement.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "if (" + conditionExpression + ")\n   then: " + thenStatement + "\n   else: " + elseStatement + "\nendif";
    }
}
