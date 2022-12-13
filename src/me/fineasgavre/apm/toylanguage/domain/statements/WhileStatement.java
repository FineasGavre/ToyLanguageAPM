package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.BooleanType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;

public class WhileStatement implements IStatement {
    private final IExpression expression;
    private final IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        var conditionalValue = expression.evaluate(symbolTable, heap);

        if (!conditionalValue.getType().equals(new BooleanType())) {
            throw new InvalidExpressionOperandTLException(new BooleanType(), conditionalValue.getType());
        }

        var unboxedConditionalValue = (BooleanValue) conditionalValue;

        if (unboxedConditionalValue.getValue()) {
            programState.getExecutionStack().push(clone());
            programState.getExecutionStack().push(statement);
        }

        return null;
    }

    @Override
    public String toString() {
        return "while (" + expression + ") do {\n" + statement + "\n}";
    }

    @Override
    public IStatement clone() {
        try {
            return (IStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
