package me.fineasgavre.apm.toylanguage.domain.expressions;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.types.IntegerType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class RelationalExpression implements IExpression {
    public enum RelationalOperation {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        GREATER_OR_EQUAL_THAN,
        LESS_THAN,
        LESS_OR_EQUAL_THAN;

        @Override
        public String toString() {
            switch (this) {
                case EQUALS -> {
                    return "==";
                }
                case NOT_EQUALS -> {
                    return "!=";
                }
                case GREATER_THAN -> {
                    return ">";
                }
                case GREATER_OR_EQUAL_THAN -> {
                    return ">=";
                }
                case LESS_THAN -> {
                    return "<";
                }
                case LESS_OR_EQUAL_THAN -> {
                    return "<=";
                }
                default -> {
                    return "?";
                }
            }
        }

    }
    private IExpression expression1;

    private IExpression expression2;
    private RelationalOperation relationalOperation;

    public RelationalExpression(IExpression expression1, IExpression expression2, RelationalOperation relationalOperation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.relationalOperation = relationalOperation;
    }

    @Override
    public IValue evaluate(ITLMap<String, IValue> symbolTable) throws TLException {
        var value1 = this.expression1.evaluate(symbolTable);

        if (!value1.getType().equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), value1.getType());
        }

        var value2 = this.expression2.evaluate(symbolTable);

        if (!value2.getType().equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), value2.getType());
        }

        var unboxedValue1 = ((IntegerValue) value1).getValue();
        var unboxedValue2 = ((IntegerValue) value2).getValue();

        switch (this.relationalOperation) {
            case EQUALS -> {
                return new BooleanValue(unboxedValue1 == unboxedValue2);
            }
            case NOT_EQUALS -> {
                return new BooleanValue(unboxedValue1 != unboxedValue2);
            }
            case GREATER_THAN -> {
                return new BooleanValue(unboxedValue1 > unboxedValue2);
            }
            case GREATER_OR_EQUAL_THAN -> {
                return new BooleanValue(unboxedValue1 >= unboxedValue2);
            }
            case LESS_THAN -> {
                return new BooleanValue(unboxedValue1 < unboxedValue2);
            }
            case LESS_OR_EQUAL_THAN -> {
                return new BooleanValue(unboxedValue1 <= unboxedValue2);
            }
            default -> {
                return new BooleanValue(false);
            }
        }
    }

    @Override
    public String toString() {
        return "(" + expression1 + ") " + relationalOperation.toString() + " (" + expression2 + ")";
    }

    @Override
    public IExpression clone() {
        try {
            var clone = (RelationalExpression) super.clone();
            clone.expression1 = this.expression1.clone();
            clone.expression2 = this.expression2.clone();
            clone.relationalOperation = this.relationalOperation;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
