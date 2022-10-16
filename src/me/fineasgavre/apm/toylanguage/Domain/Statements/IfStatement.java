package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Domain.Types.BooleanType;
import me.fineasgavre.apm.toylanguage.Domain.Values.BooleanValue;
import me.fineasgavre.apm.toylanguage.Exceptions.Statement.InvalidTypeStatementTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

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
        var symbolTable = programState.getSymbolTable();

        var value = this.conditionExpression.evaluate(symbolTable);

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
