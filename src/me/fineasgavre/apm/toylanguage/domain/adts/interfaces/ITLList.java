package me.fineasgavre.apm.toylanguage.domain.adts.interfaces;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public interface ITLList<T> {
    void add(T element);
    void remove(int index) throws TLException;
    T get(int index) throws TLException;

    boolean hasValue(T value);
    int size();
}
