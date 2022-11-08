package me.fineasgavre.apm.toylanguage.Utils;

import me.fineasgavre.apm.toylanguage.Domain.Expressions.ArithmeticExpression;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.ValueExpression;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.VariableExpression;
import me.fineasgavre.apm.toylanguage.Domain.Statements.*;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Domain.Types.BooleanType;
import me.fineasgavre.apm.toylanguage.Domain.Types.IntegerType;
import me.fineasgavre.apm.toylanguage.Domain.Types.StringType;
import me.fineasgavre.apm.toylanguage.Domain.Values.BooleanValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.IntegerValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.StringValue;

public class BuiltInPrograms {
    public static IStatement PROGRAM1 = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntegerType()),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
    );

    public static IStatement PROGRAM2 = new CompoundStatement(
            new VariableDeclarationStatement("a", new IntegerType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("b", new IntegerType()),
                    new CompoundStatement(
                            new AssignmentStatement(
                                    "a",
                                    new ArithmeticExpression(
                                            new ValueExpression(new IntegerValue(2)),
                                            new ArithmeticExpression(
                                                    new ValueExpression(new IntegerValue(3)),
                                                    new ValueExpression(new IntegerValue(5)),
                                                    ArithmeticExpression.ArithmeticOperation.MULTIPLICATION
                                            ),
                                            ArithmeticExpression.ArithmeticOperation.ADDITION
                                    )
                            ),
                            new CompoundStatement(
                                    new AssignmentStatement(
                                            "b",
                                            new ArithmeticExpression(
                                                    new VariableExpression("a"),
                                                    new ValueExpression(new IntegerValue(1)),
                                                    ArithmeticExpression.ArithmeticOperation.ADDITION
                                            )
                                    ),
                                    new PrintStatement(new VariableExpression("b"))
                            )
                    )
            )
    );

    public static IStatement PROGRAM3 = new CompoundStatement(
            new VariableDeclarationStatement("a", new BooleanType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("v", new IntegerType()),
                    new CompoundStatement(
                            new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                            new CompoundStatement(
                                    new IfStatement(
                                            new VariableExpression("a"),
                                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))
                                    ),
                                    new PrintStatement(new VariableExpression("v"))
                            )
                    )
            )
    );

    public static IStatement PROGRAM4 = new CompoundStatement(
            new VariableDeclarationStatement("filePath", new StringType()),
            new CompoundStatement(
                    new AssignmentStatement("filePath", new ValueExpression(new StringValue("test.in"))),
                    new CompoundStatement(
                            new OpenReadFileStatement(new VariableExpression("filePath")),
                            new CompoundStatement(
                                    new VariableDeclarationStatement("fileData", new IntegerType()),
                                    new CompoundStatement(
                                            new ReadFileStatement(new VariableExpression("filePath"), "fileData"),
                                            new CompoundStatement(
                                                    new PrintStatement(new VariableExpression("fileData")),
                                                    new CompoundStatement(
                                                            new ReadFileStatement(new VariableExpression("filePath"), "fileData"),
                                                            new CompoundStatement(
                                                                    new PrintStatement(new VariableExpression("fileData")),
                                                                    new CloseReadFileStatement(new VariableExpression("filePath"))
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );
}
