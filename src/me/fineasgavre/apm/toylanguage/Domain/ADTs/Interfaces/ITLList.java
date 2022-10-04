package me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces;

public interface ITLList<T> {
    void add(T element);
    void remove(int index);
    T get(int index);
    int size();
}
