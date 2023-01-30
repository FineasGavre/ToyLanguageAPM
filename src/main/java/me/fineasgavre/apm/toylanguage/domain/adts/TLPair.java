package me.fineasgavre.apm.toylanguage.domain.adts;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLPair;

public class TLPair<P1, P2> implements ITLPair<P1, P2> {
    private final P1 first;
    private final P2 second;

    public TLPair(P1 first, P2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public P1 getFirst() {
        return first;
    }

    @Override
    public P2 getSecond() {
        return second;
    }
}
