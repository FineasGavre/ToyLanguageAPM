package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GroupStatement implements IStatement {
    private List<IStatement> statements;

    public GroupStatement(IStatement... statements) {
        this.statements = Arrays.stream(statements).toList();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var reversedStatements = new ArrayList<>(statements);
        Collections.reverse(reversedStatements);

        for (var statement : reversedStatements) {
            programState.getExecutionStack().push(statement);
        }

        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        var currentEnvironment = typeEnvironment;

        for (var statement : statements) {
            currentEnvironment = statement.staticTypeCheck(currentEnvironment);
        }

        return currentEnvironment;
    }

    @Override
    public IStatement clone() {
        try {
            var clone = (GroupStatement) super.clone();
            clone.statements = statements.stream().map(IStatement::clone).toList();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (var statement : statements) {
            stringBuilder.append(statement.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
