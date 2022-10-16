package me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

public interface IStatement extends Cloneable {
    ProgramState execute(ProgramState programState) throws TLException;

    IStatement clone();
}
