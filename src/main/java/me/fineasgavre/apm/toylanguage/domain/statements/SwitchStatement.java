package me.fineasgavre.apm.toylanguage.domain.statements;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.expressions.RelationalExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.interfaces.IExpression;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.interfaces.IType;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.expression.InvalidExpressionOperandTLException;

public class SwitchStatement implements IStatement {
    private final IExpression expression;

    private final IExpression firstBranchExpression;
    private final IStatement firstBranchStatement;

    private final IExpression secondBranchExpression;
    private final IStatement secondBranchStatement;

    private final IStatement defaultBranchStatement;

    public SwitchStatement(IExpression expression, IExpression firstBranchExpression, IStatement firstBranchStatement, IExpression secondBranchExpression, IStatement secondBranchStatement, IStatement defaultBranchStatement) {
        this.expression = expression;
        this.firstBranchExpression = firstBranchExpression;
        this.firstBranchStatement = firstBranchStatement;
        this.secondBranchExpression = secondBranchExpression;
        this.secondBranchStatement = secondBranchStatement;
        this.defaultBranchStatement = defaultBranchStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws TLException {
        var composedStatement = new IfStatement(
                new RelationalExpression(expression, firstBranchExpression, RelationalExpression.RelationalOperation.EQUALS),
                firstBranchStatement,
                new IfStatement(
                        new RelationalExpression(expression, secondBranchExpression, RelationalExpression.RelationalOperation.EQUALS),
                        secondBranchStatement,
                        defaultBranchStatement
                )
        );

        programState.getExecutionStack().push(composedStatement);
        return null;
    }

    @Override
    public ITLMap<String, IType> staticTypeCheck(ITLMap<String, IType> typeEnvironment) throws TLException {
        var mainExpressionType = expression.staticTypeCheck(typeEnvironment);

        var firstBranchExpressionType = firstBranchExpression.staticTypeCheck(typeEnvironment);
        var secondBranchExpressionType = secondBranchExpression.staticTypeCheck(typeEnvironment);

        if (!mainExpressionType.equals(firstBranchExpressionType)) {
            throw new InvalidExpressionOperandTLException(mainExpressionType, firstBranchExpressionType);
        }

        if (!mainExpressionType.equals(secondBranchExpressionType)) {
            throw new InvalidExpressionOperandTLException(mainExpressionType, secondBranchExpressionType);
        }

        firstBranchStatement.staticTypeCheck(typeEnvironment);
        secondBranchStatement.staticTypeCheck(typeEnvironment);
        defaultBranchStatement.staticTypeCheck(typeEnvironment);

        return typeEnvironment;
    }

    @Override
    public IStatement clone() {
        try {
            return (SwitchStatement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "switch(" + expression + ") {\n(case " + firstBranchExpression + " {\n" + firstBranchStatement + "})\n(case " + secondBranchExpression + " {\n" + secondBranchStatement + "})\n(default " + defaultBranchStatement + ")}";
    }
}
