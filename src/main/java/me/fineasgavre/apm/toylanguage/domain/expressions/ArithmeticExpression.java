package me.fineasgavre.apm.toylanguage.domain.expressions;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.types.IntegerType;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.arithmetic.DivisionByZeroArithmeticTLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class ArithmeticExpression implements IExpression {
    public enum ArithmeticOperation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION;

        @Override
        public String toString() {
            switch (this) {
                case ADDITION -> {
                    return "+";
                }
                case SUBTRACTION -> {
                    return "-";
                }
                case MULTIPLICATION -> {
                    return "*";
                }
                case DIVISION -> {
                    return "/";
                }
                default -> {
                    return "?";
                }
            }
        }
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
    public IValue evaluate(ITLMap<String, IValue> symbolTable, ITLHeap<IValue> heap) throws TLException {
        var value1 = this.expression1.evaluate(symbolTable, heap);

        if (!value1.getType().equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), value1.getType());
        }

        var value2 = this.expression2.evaluate(symbolTable, heap);

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
    public IType staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        var lhsType = expression1.staticTypeCheck(typeEnvironment);
        var rhsType = expression2.staticTypeCheck(typeEnvironment);

        if (!lhsType.equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), lhsType);
        }

        if (!rhsType.equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), rhsType);
        }

        return new IntegerType();
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
        return "(" + this.expression1 + ") " + this.arithmeticOperation + " (" + this.expression2 + ")";
    }
}
