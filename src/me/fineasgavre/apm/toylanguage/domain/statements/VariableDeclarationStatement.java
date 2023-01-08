package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.statement.VariableAlreadyDeclaredTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

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

        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        typeEnvironment.put(variableId, variableType);
        return typeEnvironment;
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
        return variableType + " " + variableId;
    }
}
