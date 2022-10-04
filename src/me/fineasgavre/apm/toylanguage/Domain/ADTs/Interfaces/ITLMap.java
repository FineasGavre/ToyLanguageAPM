package me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces;

public interface ITLMap<K, V> {
    void put(K key, V value);
    V get(K key);
    void clear();
}
