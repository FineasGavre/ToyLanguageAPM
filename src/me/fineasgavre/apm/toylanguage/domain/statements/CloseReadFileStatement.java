package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.StringType;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.file.FileNotOpenedException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.io.IOException;

public class CloseReadFileStatement implements IStatement {
    private IExpression filePathExpression;

    public CloseReadFileStatement(IExpression filePathExpression) {
        this.filePathExpression = filePathExpression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();
        var fileTable = programState.getFileTable();

        var filePathValue = filePathExpression.evaluate(symbolTable, heap);

        if (!filePathValue.getType().equals(new StringType())) {
            throw new InvalidExpressionOperandTLException(new StringType(), filePathValue.getType());
        }

        var filePathStringValue = (StringValue) filePathValue;

        if (!fileTable.containsKey(filePathStringValue)) {
            throw new FileNotOpenedException();
        }

        try {
            fileTable.get(filePathStringValue).close();
            fileTable.clearKey(filePathStringValue);
        } catch (IOException e) {
            throw new IOTLException();
        }

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
