package me.fineasgavre.apm.toylanguage.domain.adts;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLPair;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLProcedureTable;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.adt.UnknownKeyMapTLException;
import me.fineasgavre.apm.toylanguage.exceptions.statement.VariableAlreadyDeclaredTLException;

import java.util.HashMap;
import java.util.Map;

public class TLProcedureTable<K, P, S> implements ITLProcedureTable<K, P, S> {
    private final Map<K, Map.Entry<P, S>> procedureTable = new HashMap<>();

    @Override
    public void registerProcedure(K key, P parameters, S statement) throws TLException {
        if (this.containsProcedure(key)) {
            throw new VariableAlreadyDeclaredTLException(key.toString());
        }

        procedureTable.put(key, Map.entry(parameters, statement));
    }

    @Override
    public ITLPair<P, S> getProcedure(K key) throws TLException {
        if (!this.containsProcedure(key)) {
            throw new UnknownKeyMapTLException(key.toString());
        }

        var entry = procedureTable.get(key);
        return new TLPair<>(entry.getKey(), entry.getValue());
    }

    @Override
    public boolean containsProcedure(K key) {
        return procedureTable.containsKey(key);
    }

    @Override
    public Map<K, Map.Entry<P, S>> getMap() {
        return procedureTable;
    }
}
