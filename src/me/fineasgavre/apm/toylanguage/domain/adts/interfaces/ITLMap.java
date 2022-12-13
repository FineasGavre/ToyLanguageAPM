package me.fineasgavre.apm.toylanguage.domain.adts.interfaces;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.Map;

public interface ITLMap<K, V> {
    void put(K key, V value);
    V get(K key) throws TLException;
    boolean containsKey(K key);
    void clear();

    void clearKey(K key) throws TLException;

    Map<K, V> getMap();

    void setMap(Map<K, V> map);

    ITLMap<K, V> clone();
}
