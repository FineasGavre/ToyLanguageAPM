package me.fineasgavre.apm.toylanguage.Domain.ADTs;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.Exceptions.ADT.UnknownKeyMapTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

import java.util.HashMap;
import java.util.Map;

public class TLMap<K, V> implements ITLMap<K, V> {
    private final Map<K, V> map = new HashMap<>();

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) throws TLException {
        if (!this.containsKey(key)) {
            throw new UnknownKeyMapTLException(key.toString());
        }

        return map.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        return "TLMap{" +
                "map=" + map +
                '}';
    }
}
