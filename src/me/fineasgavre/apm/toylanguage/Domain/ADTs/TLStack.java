package me.fineasgavre.apm.toylanguage.Domain.ADTs;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLStack;

import java.util.Stack;

public class TLStack<T> implements ITLStack<T> {
    private final Stack<T> stack = new Stack<>();

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T item) {
        stack.push(item);
    }
}
