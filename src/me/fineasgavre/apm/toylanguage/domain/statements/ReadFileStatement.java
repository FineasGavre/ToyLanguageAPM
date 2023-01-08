package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.StringType;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.file.FileNotOpenedException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

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

        var reader = programState.getFileTable().get(filePathStringValue);
        int fileValue;
        try {
            fileValue = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            fileValue = 0;
        }

        symbolTable.put(variableName, new IntegerValue(fileValue));
        return null;
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
