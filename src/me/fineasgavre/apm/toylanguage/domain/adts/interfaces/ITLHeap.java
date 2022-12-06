package me.fineasgavre.apm.toylanguage.domain.adts.interfaces;

import me.fineasgavre.apm.toylanguage.exceptions.adt.UnknownKeyMapTLException;

import java.util.Map;

public interface ITLHeap<T> {
    Map.Entry<Integer, T> allocateNewForValue(T value);

    boolean hasValueAllocated(int address);

    T getValueForAddress(int address) throws UnknownKeyMapTLException;

    void writeToAddress(int address, T value) throws UnknownKeyMapTLException;
}
