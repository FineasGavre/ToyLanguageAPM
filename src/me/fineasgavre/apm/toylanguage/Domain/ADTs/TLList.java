package me.fineasgavre.apm.toylanguage.Domain.ADTs;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLList;

import java.util.ArrayList;
import java.util.List;

public class TLList<T> implements ITLList<T> {
    private final List<T> list = new ArrayList<>();

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
