package me.fineasgavre.apm.toylanguage.domain.adts.interfaces;

import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.Map;

public interface ITLProcedureTable<K, P, S> {
    void registerProcedure(K key, P parameters, S statement) throws TLException;
    ITLPair<P, S> getProcedure(K key) throws TLException;
    boolean containsProcedure(K key);
    Map<K, Map.Entry<P, S>> getMap();
}
