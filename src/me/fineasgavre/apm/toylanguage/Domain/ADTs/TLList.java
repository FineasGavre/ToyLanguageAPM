package me.fineasgavre.apm.toylanguage.Domain.ADTs;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.Exceptions.ADT.IndexOutOfBoundsTLException;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

import java.util.ArrayList;
import java.util.List;

public class TLList<T> implements ITLList<T> {
    private final List<T> list = new ArrayList<>();

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public void remove(int index) throws TLException {
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsTLException(index, list.size());
        }

        list.remove(index);
    }

    @Override
    public T get(int index) throws TLException {
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsTLException(index, list.size());
        }

        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("TLList (");
        stringBuilder.append(list.size());
        stringBuilder.append(" entries)");

        var iterator = list.iterator();
        if (iterator.hasNext()) {
            stringBuilder.append("\n");
        }

        while (iterator.hasNext()) {
            var value = iterator.next().toString();

            stringBuilder.append("• ");
            stringBuilder.append(value);
            stringBuilder.append(iterator.hasNext() ? "\n" : "");
        }

        return stringBuilder.toString();
    }
}
