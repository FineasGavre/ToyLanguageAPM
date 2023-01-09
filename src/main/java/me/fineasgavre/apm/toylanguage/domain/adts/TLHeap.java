package me.fineasgavre.apm.toylanguage.domain.adts;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.adt.UnknownKeyMapTLException;

import java.util.Map;

public class TLHeap<T> implements ITLHeap<T> {
    private int nextAddress = 1;
    private ITLMap<Integer, T> map = new TLMap<>();

    @Override
    public Map.Entry<Integer, T> allocateNewForValue(T value) {
        map.put(nextAddress, value);
        var entry = Map.entry(nextAddress, value);
        nextAddress++;
        return entry;
    }

    @Override
    public boolean hasValueAllocated(int address) {
        return map.containsKey(address);
    }

    @Override
    public T getValueForAddress(int address) throws UnknownKeyMapTLException {
        try {
            return map.get(address);
        } catch (Exception e) {
            throw new UnknownKeyMapTLException(Integer.toString(address));
        }
    }

    @Override
    public void writeToAddress(int address, T value) throws UnknownKeyMapTLException {
        if (!hasValueAllocated(address)) {
            throw new UnknownKeyMapTLException(Integer.toString(address));
        }

        map.put(address, value);
    }

    @Override
    public ITLMap<Integer, T> getContent() {
        return map;
    }

    @Override
    public void setContent(ITLMap<Integer, T> content) {
        this.map = content;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
