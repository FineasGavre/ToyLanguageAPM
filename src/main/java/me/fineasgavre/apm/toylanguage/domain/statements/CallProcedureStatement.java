package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.UnknownVariableInExpressionTLException;

import java.util.List;

public class CallProcedureStatement implements IStatement {
    private final String procedureName;
    private final List<IExpression> arguments;

    public CallProcedureStatement(String procedureName, List<IExpression> arguments) {
        this.procedureName = procedureName;
        this.arguments = arguments;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var procedureTable = programState.getProcedureTable();
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        if (!procedureTable.containsProcedure(procedureName)) {
            throw new UnknownVariableInExpressionTLException(procedureName);
        }

        var procedureDefinition = procedureTable.getProcedure(procedureName);

        if (procedureDefinition.getFirst().size() != arguments.size()) {
            throw new TLException("Number of defined parameters and number of supplied arguments do not match.");
        }

        var procedureSymbolTable = new TLMap<String, IValue>();

        for (int i = 0; i < arguments.size(); i++) {
            var variableId = procedureDefinition.getFirst().get(i).getFirst();
            var definedType = procedureDefinition.getFirst().get(i).getSecond();
            var evaluatedExpression = arguments.get(i).evaluate(symbolTable, heap);

            if (!evaluatedExpression.getType().equals(definedType)) {
                throw new TLException("Argument of invalid type passed to procedure.");
            }

            procedureSymbolTable.put(variableId, evaluatedExpression);
        }

        programState.getSymbolTableStack().push(procedureSymbolTable);
        programState.getExecutionStack().push(new ProcedureReturnStatement());
        programState.getExecutionStack().push(procedureDefinition.getSecond());

        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
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
        StringBuilder argumentsList = new StringBuilder();

        for (var argument : arguments) {
            argumentsList.append(argument).append(", ");
        }

        return "call " + procedureName + " (" + argumentsList + ")";
    }
}
