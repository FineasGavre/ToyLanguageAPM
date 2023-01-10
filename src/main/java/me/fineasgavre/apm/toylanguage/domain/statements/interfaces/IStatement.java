package me.fineasgavre.apm.toylanguage.domain.statements.interfaces;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public interface IStatement extends Cloneable {
    ProgramState execute(ProgramState programState) throws TLException;

    ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException;

    IStatement clone();
}
