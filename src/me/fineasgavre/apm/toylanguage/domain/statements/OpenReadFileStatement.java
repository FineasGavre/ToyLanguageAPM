package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.StringType;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;
import me.fineasgavre.apm.toylanguage.exceptions.execution.IOTLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.file.FileAlreadyOpenedException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    private IExpression filePathExpression;

    public OpenReadFileStatement(IExpression filePathExpression) {
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

        if (fileTable.containsKey(filePathStringValue)) {
            throw new FileAlreadyOpenedException();
        }

        try {
            var inFile = new FileReader(filePathStringValue.getValue());
            var reader = new BufferedReader(inFile);
            fileTable.put(filePathStringValue, reader);
        } catch (FileNotFoundException e) {
            throw new IOTLException();
        }

        return programState;
    }

    @Override
    public String toString() {
        return "openFile(" + filePathExpression + ")";
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (OpenReadFileStatement) super.clone();
            clone.filePathExpression = this.filePathExpression.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
