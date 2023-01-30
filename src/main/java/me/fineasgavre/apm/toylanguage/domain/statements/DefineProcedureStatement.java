package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLPair;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

import java.util.List;

public class DefineProcedureStatement implements IStatement {
    private final String procedureName;
    private final List<ITLPair<String, IType>> parameters;
    private final IStatement statement;

    public DefineProcedureStatement(String procedureName, List<ITLPair<String, IType>> parameters, IStatement statement) {
        this.procedureName = procedureName;
        this.parameters = parameters;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var procedureTable = programState.getProcedureTable();

        procedureTable.registerProcedure(procedureName, parameters, statement);

        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        var procedureEnvironment = new TLMap<String, IType>();

        for (var entry : parameters) {
            procedureEnvironment.put(entry.getFirst(), entry.getSecond());
        }

        statement.staticTypeCheck(procedureEnvironment);

        return typeEnvironment;
    }

    @Override
    public IStatement clone() {
        try {
            return (IStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder parametersList = new StringBuilder();
        for (var parameter : parameters) {
            parametersList.append(parameter.getSecond()).append(" ").append(parameter.getFirst()).append(", ");
        }


        return "procedure " + procedureName + " (" + parametersList + ") {\n" + statement + "}\n";
    }
}
