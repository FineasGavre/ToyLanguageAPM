package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.ValueExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.IntegerType;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;

public class SleepStatement implements IStatement {
    private final IExpression expression;

    public SleepStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var executionStack = programState.getExecutionStack();
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        var value = expression.evaluate(symbolTable, heap);

        if (!value.getType().equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), value.getType());
        }

        var unboxedValue = (IntegerValue) value;

        if (unboxedValue.getValue() > 0) {
            executionStack.push(new SleepStatement(new ValueExpression(new IntegerValue(unboxedValue.getValue() - 1))));
        }

        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        var expressionType = expression.staticTypeCheck(typeEnvironment);

        if (!expressionType.equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), expressionType);
        }

        return typeEnvironment;
    }

    @Override
    public IStatement clone() {
        try {
            return (SleepStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "sleep(" + expression + ")";
    }
}
