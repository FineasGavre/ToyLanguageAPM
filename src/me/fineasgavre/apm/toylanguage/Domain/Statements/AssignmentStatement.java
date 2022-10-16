package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Exceptions.Statement.AssigmentToUndeclaredVariableTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.Statement.AssignmentToDifferentVariableTypeTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class AssignmentStatement implements IStatement {
    private String variableId;
    private IExpression expression;

    public AssignmentStatement(String variableId, IExpression expression) {
        this.variableId = variableId;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();

        if (!symbolTable.containsKey(this.variableId)) {
            throw new AssigmentToUndeclaredVariableTLException(this.variableId);
        }

        var newValue = this.expression.evaluate(symbolTable);
        var variableType = symbolTable.get(this.variableId).getType();

        if (!newValue.getType().equals(variableType)) {
            throw new AssignmentToDifferentVariableTypeTLException(variableType, newValue.getType());
        }

        symbolTable.put(this.variableId, newValue);
        return programState;
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (AssignmentStatement) super.clone();
            clone.variableId = this.variableId + "";
            clone.expression = this.expression.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "AssignmentStatement{" +
                "variableId='" + variableId + '\'' +
                ", expression=" + expression +
                '}';
    }
}
