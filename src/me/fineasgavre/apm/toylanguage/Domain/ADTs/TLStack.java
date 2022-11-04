package me.fineasgavre.apm.toylanguage.Domain.ADTs;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLStack;
import me.fineasgavre.apm.toylanguage.Exceptions.ADT.EmptyStackPopTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

import java.util.Stack;

public class TLStack<T> implements ITLStack<T> {
    private final Stack<T> stack = new Stack<>();

    @Override
    public T pop() throws TLException {
        if (this.isEmpty()) {
            throw new EmptyStackPopTLException();
        }

        return stack.pop();
    }

    @Override
    public void push(T item) {
        stack.push(item);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("TLStack (");
        stringBuilder.append(stack.size());
        stringBuilder.append(" entries)");

        var iterator = stack.listIterator();
        if (iterator.hasNext()) {
            stringBuilder.append("\n");
        }

        while (iterator.hasNext()) {
            var value = iterator.next().toString();

            stringBuilder.append(iterator.hasNext() ? "• " : "> ");
            stringBuilder.append(value);
            stringBuilder.append(iterator.hasNext() ? "\n" : "");
        }

        return stringBuilder.toString();
    }
}
