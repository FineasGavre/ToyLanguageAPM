package me.fineasgavre.apm.toylanguage.Domain.Expressions;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.Interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.Domain.Types.IntegerType;
import me.fineasgavre.apm.toylanguage.Domain.Values.IntegerValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;
import me.fineasgavre.apm.toylanguage.Exceptions.Arithmetic.DivisionByZeroArithmeticTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.Expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public class ArithmeticExpression implements IExpression {
    public enum ArithmeticOperation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION
    }

    private IExpression expression1;
    private IExpression expression2;
    private final ArithmeticOperation arithmeticOperation;

    public ArithmeticExpression(IExpression expression1, IExpression expression2, ArithmeticOperation arithmeticOperation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.arithmeticOperation = arithmeticOperation;
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

        switch (this.arithmeticOperation) {
            case ADDITION -> {
                return new IntegerValue(unboxedValue1 + unboxedValue2);
            }
            case SUBTRACTION -> {
                return new IntegerValue(unboxedValue1 - unboxedValue2);
            }
            case MULTIPLICATION -> {
                return new IntegerValue(unboxedValue1 * unboxedValue2);
            }
            case DIVISION -> {
                if (unboxedValue2 == 0) {
                    throw new DivisionByZeroArithmeticTLException();
                }

                return new IntegerValue(unboxedValue1 / unboxedValue2);
            }
            default -> {
                return new IntegerValue(0);
            }
        }
    }

    @Override
    public IExpression clone() {
        try {
            var clone = (ArithmeticExpression) super.clone();
            clone.expression1 = this.expression1.clone();
            clone.expression2 = this.expression2.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" +
                "expression1=" + expression1 +
                ", expression2=" + expression2 +
                ", arithmeticOperation=" + arithmeticOperation +
                '}';
    }
}
