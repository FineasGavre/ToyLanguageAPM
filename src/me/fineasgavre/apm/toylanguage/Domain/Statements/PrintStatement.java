package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();

        var value = this.expression.evaluate(symbolTable);
        System.out.println(value);

        return programState;
    }

    @Override
    public IStatement clone() {
        try {
            return (PrintStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "PrintStatement{" +
                "expression=" + expression +
                '}';
    }
}
