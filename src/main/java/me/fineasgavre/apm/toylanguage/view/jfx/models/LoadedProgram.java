package me.fineasgavre.apm.toylanguage.view.jfx.models;

import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;

public class LoadedProgram {
    private final String programId;
    private final String programCode;

    private final IStatement statement;

    public LoadedProgram(String programId, String programCode, IStatement statement) {
        this.programId = programId;
        this.programCode = programCode;
        this.statement = statement;
    }

    public String getProgramId() {
        return programId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public IStatement getStatement() {
        return statement;
    }
}
