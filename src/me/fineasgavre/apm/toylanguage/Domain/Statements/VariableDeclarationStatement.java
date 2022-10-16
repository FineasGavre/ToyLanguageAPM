package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Domain.Types.Interfaces.IType;
import me.fineasgavre.apm.toylanguage.Exceptions.Statement.VariableAlreadyDeclaredTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class VariableDeclarationStatement implements IStatement {
    private String variableId;
    private IType variableType;

    public VariableDeclarationStatement(String variableId, IType variableType) {
        this.variableId = variableId;
        this.variableType = variableType;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();

        if (symbolTable.containsKey(this.variableId)) {
            throw new VariableAlreadyDeclaredTLException(this.variableId);
        }

        symbolTable.put(this.variableId, variableType.getInitialValue());

        return programState;
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (VariableDeclarationStatement) super.clone();
            clone.variableId = this.variableId + "";
            clone.variableType = this.variableType;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "VariableDeclarationStatement{" +
                "variableId='" + variableId + '\'' +
                ", variableType=" + variableType +
                '}';
    }
}
