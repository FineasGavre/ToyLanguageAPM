package me.fineasgavre.apm.toylanguage.domain.statements.interfaces;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public interface IStatement extends Cloneable {
    ProgramState execute(ProgramState programState) throws TLException;

    IStatement clone();
}
