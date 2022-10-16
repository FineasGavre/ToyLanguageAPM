package me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces;

import me.fineasgavre.apm.toylanguage.Exceptions.ADT.EmptyStackPopTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public interface ITLStack<T> {
    T pop() throws TLException;
    void push(T v);
    boolean isEmpty();
}
