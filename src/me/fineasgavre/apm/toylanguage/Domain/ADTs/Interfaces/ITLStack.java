package me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces;

public interface ITLStack<T> {
    T pop();
    void push(T v);
}
