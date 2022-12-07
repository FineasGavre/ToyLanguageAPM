package me.fineasgavre.apm.toylanguage.domain.expressions;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.types.BooleanType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class LogicExpression implements IExpression {
    public enum LogicOperation {
        EQUALS,
        NOT_EQUALS,
        AND,
        OR;

        @Override
        public String toString() {
            switch (this) {
                case EQUALS -> {
                    return "==";
                }
                case NOT_EQUALS -> {
                    return "!=";
                }
                case AND -> {
                    return "&&";
                }
                case OR -> {
                    return "||";
                }
                default -> {
                    return "?";
                }
            }
        }
    }

    private IExpression expression1;
    private IExpression expression2;
    private final LogicOperation logicOperation;

    public LogicExpression(IExpression expression1, IExpression expression2, LogicOperation logicOperation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.logicOperation = logicOperation;
    }

    @Override
    public IValue evaluate(ITLMap<String, IValue> symbolTable, ITLHeap<IValue> heap) throws TLException {
        var value1 = this.expression1.evaluate(symbolTable, heap);

        if (!value1.getType().equals(new BooleanType())) {
            throw new InvalidExpressionOperandTLException(new BooleanType(), value1.getType());
        }

        var value2 = this.expression2.evaluate(symbolTable, heap);

        if (!value2.getType().equals(new BooleanType())) {
            throw new InvalidExpressionOperandTLException(new BooleanType(), value2.getType());
        }

        var unboxedValue1 = ((BooleanValue) value1).getValue();
        var unboxedValue2 = ((BooleanValue) value2).getValue();

        switch (this.logicOperation) {
            case EQUALS -> {
                return new BooleanValue(unboxedValue1 == unboxedValue2);
            }
            case NOT_EQUALS -> {
                return new BooleanValue(unboxedValue1 != unboxedValue2);
            }
            case AND -> {
                return new BooleanValue(unboxedValue1 && unboxedValue2);
            }
            case OR -> {
                return new BooleanValue(unboxedValue1 || unboxedValue2);
            }
            default -> {
                return new BooleanValue(false);
            }
        }
    }

    @Override
    public IExpression clone() {
        try {
            var clone = (LogicExpression) super.clone();
            clone.expression1 = this.expression1.clone();
            clone.expression2 = this.expression2.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "(" + expression1 + ") " + logicOperation.toString() + " (" + expression2 + ")";
    }
}
