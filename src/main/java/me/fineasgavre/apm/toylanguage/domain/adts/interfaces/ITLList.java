package me.fineasgavre.apm.toylanguage.domain.adts.interfaces;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.List;

public interface ITLList<T> {
    void add(T element);
    void remove(int index) throws TLException;
    T get(int index) throws TLException;

    List<T> getList();

    void setList(List<T> list);

    boolean hasValue(T value);
    int size();

    ITLList<T> clone();
}
