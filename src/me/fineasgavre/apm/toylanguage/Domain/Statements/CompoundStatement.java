package me.fineasgavre.apm.toylanguage.Domain.Statements;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;

public class CompoundStatement implements IStatement {
    private final IStatement firstStatement;
    private final IStatement secondStatement;

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
    public String toString() {
        return "CompoundStatement{" +
                "firstStatement=" + firstStatement +
                ", secondStatement=" + secondStatement +
                '}';
    }
}
