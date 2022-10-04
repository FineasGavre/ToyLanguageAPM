package me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces;

import me.fineasgavre.apm.toylanguage.Domain.State.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState programState);
}
