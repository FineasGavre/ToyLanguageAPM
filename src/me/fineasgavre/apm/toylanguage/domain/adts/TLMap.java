package me.fineasgavre.apm.toylanguage.domain.adts;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.exceptions.adt.UnknownKeyMapTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.HashMap;
import java.util.Map;

public class TLMap<K, V> implements ITLMap<K, V> {
    private Map<K, V> map = new HashMap<>();

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
    public void clearKey(K key) throws TLException {
        if (!this.containsKey(key)) {
            throw new UnknownKeyMapTLException(key.toString());
        }

        map.remove(key);
    }

    @Override
    public Map<K, V> getMap() {
        return map;
    }

    @Override
    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public ITLMap<K, V> clone() {
        var map = new TLMap<K, V>();
        map.setMap(new HashMap<>(this.map));

        return map;
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
