package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Domain.Types.StringType;
import me.fineasgavre.apm.toylanguage.Domain.Values.StringValue;
import me.fineasgavre.apm.toylanguage.Exceptions.Execution.IOTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.Expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.File.FileNotOpenedException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

import java.io.IOException;

public class CloseReadFileStatement implements IStatement {
    private IExpression filePathExpression;

    public CloseReadFileStatement(IExpression filePathExpression) {
        this.filePathExpression = filePathExpression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();
        var fileTable = programState.getFileTable();

        var filePathValue = filePathExpression.evaluate(symbolTable);

        if (!filePathValue.getType().equals(new StringType())) {
            throw new InvalidExpressionOperandTLException(new StringType(), filePathValue.getType());
        }

        var filePathStringValue = (StringValue) filePathValue;

        if (!fileTable.containsKey(filePathStringValue)) {
            throw new FileNotOpenedException();
        }

        try {
            fileTable.get(filePathStringValue).close();
        } catch (IOException e) {
            throw new IOTLException();
        }

        fileTable.clearKey(filePathStringValue);

        return programState;
    }

    @Override
    public String toString() {
        return "closeFile(" + filePathExpression + ")";
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (CloseReadFileStatement) super.clone();
            clone.filePathExpression = this.filePathExpression.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
