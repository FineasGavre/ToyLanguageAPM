package me.fineasgavre.apm.toylanguage.domain.state.snapshot;

public class PairItem<T> {
    private final T first;
    private final T second;

    public PairItem(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}
