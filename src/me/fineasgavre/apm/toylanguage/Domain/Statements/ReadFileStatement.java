package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Domain.Types.StringType;
import me.fineasgavre.apm.toylanguage.Domain.Values.IntegerValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.StringValue;
import me.fineasgavre.apm.toylanguage.Exceptions.Expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.File.FileNotOpenedException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class ReadFileStatement implements IStatement {
    private IExpression filePathExpression;
    private String variableName;

    public ReadFileStatement(IExpression filePathExpression, String variableName) {
        this.filePathExpression = filePathExpression;
        this.variableName = variableName;
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

        var reader = programState.getFileTable().get(filePathStringValue);
        int fileValue;
        try {
            fileValue = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            fileValue = 0;
        }

        symbolTable.put(variableName, new IntegerValue(fileValue));
        return programState;
    }

    @Override
    public String toString() {
        return "readFile(from: " + filePathExpression + ", into: [" + variableName + "])";
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (ReadFileStatement) super.clone();
            clone.filePathExpression = this.filePathExpression.clone();
            clone.variableName = "" + this.variableName;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}