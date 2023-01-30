package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.BooleanType;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.UnknownVariableInExpressionTLException;

public class ConditionalAssignmentStatement implements IStatement {
    private final String variableId;
    private final IExpression conditionalExpression;
    private final IExpression thenExpression;
    private final IExpression elseExpression;

    public ConditionalAssignmentStatement(String variableId, IExpression conditionalExpression, IExpression thenExpression, IExpression elseExpression) {
        this.variableId = variableId;
        this.conditionalExpression = conditionalExpression;
        this.thenExpression = thenExpression;
        this.elseExpression = elseExpression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var composedStatement = new IfStatement(
                conditionalExpression,
                new AssignmentStatement(variableId, thenExpression),
                new AssignmentStatement(variableId, elseExpression)
        );

        programState.getExecutionStack().push(composedStatement);
        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        if (!typeEnvironment.containsKey(variableId)) {
            throw new UnknownVariableInExpressionTLException(variableId);
        }

        var conditionalExpressionType = conditionalExpression.staticTypeCheck(typeEnvironment);

        if (!conditionalExpressionType.equals(new BooleanType())) {
            throw new InvalidExpressionOperandTLException(new BooleanType(), conditionalExpressionType);
        }

        var variableType = typeEnvironment.get(variableId);
        var thenExpressionType = thenExpression.staticTypeCheck(typeEnvironment);

        if (!thenExpressionType.equals(variableType)) {
            throw new InvalidExpressionOperandTLException(variableType, thenExpressionType);
        }

        var elseExpressionType = elseExpression.staticTypeCheck(typeEnvironment);

        if (!elseExpressionType.equals(variableType)) {
            throw new InvalidExpressionOperandTLException(variableType, elseExpressionType);
        }

        return typeEnvironment;
    }

    @Override
    public IStatement clone() {
        try {
            return (ConditionalAssignmentStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[" + variableId + "]" + " <- " + conditionalExpression + " ? " + thenExpression + " : " + elseExpression;
    }
}
