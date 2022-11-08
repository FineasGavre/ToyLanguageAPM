package me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces;

import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public interface ITLMap<K, V> {
    void put(K key, V value);
    V get(K key) throws TLException;
    boolean containsKey(K key);
    void clear();

    void clearKey(K key) throws TLException;
}
