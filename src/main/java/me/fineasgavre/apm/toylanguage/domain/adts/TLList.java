package me.fineasgavre.apm.toylanguage.domain.adts;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.exceptions.adt.IndexOutOfBoundsTLException;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.ArrayList;
import java.util.List;

public class TLList<T> implements ITLList<T> {
    private List<T> list = new ArrayList<>();

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
    public List<T> getList() {
        return list;
    }

    @Override
    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasValue(T value) {
        return list.contains(value);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public ITLList<T> clone() {
        var list = new TLList<T>();
        list.setList(new ArrayList<>(this.list));

        return list;
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
