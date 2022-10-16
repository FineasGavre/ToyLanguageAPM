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
        var stringBuilder = new StringBuilder();
        stringBuilder.append("TLMap (");
        stringBuilder.append(map.size());
        stringBuilder.append(" entries)");

        var iterator = map.entrySet().iterator();
        if (iterator.hasNext()) {
            stringBuilder.append("\n");
        }

        while (iterator.hasNext()) {
            var value = iterator.next();

            stringBuilder.append("• ");
            stringBuilder.append(value.getKey().toString());
            stringBuilder.append(" -> ");
            stringBuilder.append(value.getValue().toString());
            stringBuilder.append(iterator.hasNext() ? "\n" : "");
        }

        return stringBuilder.toString();
    }
}
