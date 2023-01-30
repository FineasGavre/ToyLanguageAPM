package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.RelationalExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.VariableExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.IntegerType;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;
import me.fineasgavre.apm.toylanguage.exceptions.statement.VariableAlreadyDeclaredTLException;

public class ForStatement implements IStatement {
    private final String variableId;
    private final IExpression variableInitializerExpression;
    private final IExpression targetValueExpression;
    private final IExpression endOfLoopAssignmentExpression;
    private final IStatement statement;

    public ForStatement(String variableId, IExpression variableInitializerExpression, IExpression targetValueExpression, IExpression endOfLoopAssignmentExpression, IStatement statement) {
        this.variableId = variableId;
        this.variableInitializerExpression = variableInitializerExpression;
        this.targetValueExpression = targetValueExpression;
        this.endOfLoopAssignmentExpression = endOfLoopAssignmentExpression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var composedStatement = new GroupStatement(
            new VariableDeclarationStatement(variableId, new IntegerType()),
            new AssignmentStatement(variableId, variableInitializerExpression),
            new WhileStatement(
                    new RelationalExpression(
                            new VariableExpression(variableId),
                            targetValueExpression,
                            RelationalExpression.RelationalOperation.LESS_THAN
                    ),
                    new GroupStatement(
                            statement,
                            new AssignmentStatement(variableId, endOfLoopAssignmentExpression)
                    )
            )
        );

        programState.getExecutionStack().push(composedStatement);
        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        if (typeEnvironment.containsKey(variableId)) {
            throw new VariableAlreadyDeclaredTLException(variableId);
        }

        typeEnvironment.put(variableId, new IntegerType());

        var variableInitializerExpressionType = variableInitializerExpression.staticTypeCheck(typeEnvironment);

        if (!variableInitializerExpressionType.equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), variableInitializerExpressionType);
        }

        var targetValueExpressionType = targetValueExpression.staticTypeCheck(typeEnvironment);

        if (!targetValueExpressionType.equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), targetValueExpressionType);
        }

        var endOfLoopAssignmentExpressionType = endOfLoopAssignmentExpression.staticTypeCheck(typeEnvironment);

        if (!endOfLoopAssignmentExpressionType.equals(new IntegerType())) {
            throw new InvalidExpressionOperandTLException(new IntegerType(), endOfLoopAssignmentExpressionType);
        }

        statement.staticTypeCheck(typeEnvironment);

        return typeEnvironment;
    }

    @Override
    public IStatement clone() {
        try {
            return (ForStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "for (" + variableId + " = " + variableInitializerExpression + "; " + variableId + " < " + targetValueExpression + "; " + variableId + " <- " + endOfLoopAssignmentExpression + ") {\n" + statement + "\n}";
    }
}
