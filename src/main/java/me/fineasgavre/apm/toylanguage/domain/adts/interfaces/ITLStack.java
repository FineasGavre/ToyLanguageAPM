package me.fineasgavre.apm.toylanguage.domain.adts.interfaces;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public interface ITLStack<T> {
    T pop() throws TLException;
    void push(T v);
    boolean isEmpty();
}
