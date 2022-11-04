package me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public interface ITLList<T> {
    void add(T element);
    void remove(int index) throws TLException;
    T get(int index) throws TLException;
    int size();
}
