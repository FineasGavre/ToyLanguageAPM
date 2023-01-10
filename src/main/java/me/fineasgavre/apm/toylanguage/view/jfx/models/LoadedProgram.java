package me.fineasgavre.apm.toylanguage.view.jfx.models;

import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

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

    public boolean getTypeCheckResult() {
        try {
            statement.staticTypeCheck(new TLMap<>());
            return true;
        } catch (TLException e) {
            return false;
        }
    }

    public String getTypeCheckResultAsString() {
        return getTypeCheckResult() ? "Success" : "Failed";
    }
}
