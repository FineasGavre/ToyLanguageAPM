package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

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

        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        return secondStatement.staticTypeCheck(firstStatement.staticTypeCheck(typeEnvironment));
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
