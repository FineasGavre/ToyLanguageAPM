package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;

public class CompoundStatement implements IStatement {
    private IStatement firstStatement;
    private IStatement secondStatement;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var executionStack = programState.getExecutionStack();

        executionStack.push(secondStatement);
        executionStack.push(firstStatement);

        return programState;
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (CompoundStatement) super.clone();
            clone.firstStatement = this.firstStatement.clone();
            clone.secondStatement = this.secondStatement.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return firstStatement + ";\n" + secondStatement;
    }
}
