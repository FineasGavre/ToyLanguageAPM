package me.fineasgavre.apm.toylanguage.domain.expressions;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.types.RefType;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.RefValue;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.heap.IncompatibleTypeForHeapAllocationTLException;
import me.fineasgavre.apm.toylanguage.exceptions.heap.InvalidHeapAccessTLException;

public class ReadHeapExpression implements IExpression {
    private IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(ITLMap<String, IValue> symbolTable, ITLHeap<IValue> heap) throws TLException {
        var value = this.expression.evaluate(symbolTable, heap);

        if (!(value.getType() instanceof RefType)) {
            throw new InvalidHeapAccessTLException();
        }

        var unboxedValue = (RefValue) value;

        if (!heap.hasValueAllocated(unboxedValue.getAddress())) {
            throw new InvalidHeapAccessTLException();
        }

        return heap.getValueForAddress(unboxedValue.getAddress());
    }

    @Override
    public IType staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        var type = expression.staticTypeCheck(typeEnvironment);

        if (!(type instanceof RefType)) {
            throw new InvalidHeapAccessTLException();
        }

        return ((RefType) type).getReferencedType();
    }

    @Override
    public IExpression clone() {
        try {
            var clone = (ReadHeapExpression) super.clone();
            clone.expression = this.expression.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "heap{" + expression + "}";
    }
}
