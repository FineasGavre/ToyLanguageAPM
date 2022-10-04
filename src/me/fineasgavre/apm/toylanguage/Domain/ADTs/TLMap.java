package me.fineasgavre.apm.toylanguage.Domain.ADTs;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;

import java.util.HashMap;
import java.util.Map;

public class TLMap<K, V> implements ITLMap<K, V> {
    private final Map<K, V> map = new HashMap<>();

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void clear() {
        map.clear();
    }
}
