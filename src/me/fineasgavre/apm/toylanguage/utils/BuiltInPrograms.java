package me.fineasgavre.apm.toylanguage.utils;

import me.fineasgavre.apm.toylanguage.domain.expressions.ArithmeticExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.ReadHeapExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.ValueExpression;
import me.fineasgavre.apm.toylanguage.domain.expressions.VariableExpression;
import me.fineasgavre.apm.toylanguage.domain.statements.*;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.BooleanType;
import me.fineasgavre.apm.toylanguage.domain.types.IntegerType;
import me.fineasgavre.apm.toylanguage.domain.types.RefType;
import me.fineasgavre.apm.toylanguage.domain.types.StringType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;

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

    public static IStatement PROGRAM5 = new CompoundStatement(
            new VariableDeclarationStatement("v", new RefType(new IntegerType())),
            new CompoundStatement(
                    new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                            new CompoundStatement(
                                    new AllocateHeapStatement("a", new VariableExpression("v")),
                                    new CompoundStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new PrintStatement(new VariableExpression("a"))
                                    )
                            )
                    )
            )
    );

    public static IStatement PROGRAM6 = new CompoundStatement(
            new VariableDeclarationStatement("v", new RefType(new IntegerType())),
            new CompoundStatement(
                    new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                            new CompoundStatement(
                                    new AllocateHeapStatement("a", new VariableExpression("v")),
                                    new CompoundStatement(
                                            new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                            new PrintStatement(new ArithmeticExpression(
                                                    new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                                    new ValueExpression(new IntegerValue(5)),
                                                    ArithmeticExpression.ArithmeticOperation.ADDITION
                                            ))
                                    )
                            )
                    )
            )
    );

    public static IStatement PROGRAM7 = new CompoundStatement(
            new VariableDeclarationStatement("v", new RefType(new IntegerType())),
            new CompoundStatement(
                    new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                            new CompoundStatement(
                                    new WriteHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                    new PrintStatement(new ArithmeticExpression(
                                            new ReadHeapExpression(new VariableExpression("v")),
                                            new ValueExpression(new IntegerValue(5)),
                                            ArithmeticExpression.ArithmeticOperation.ADDITION
                                    ))
                            )
                    )
            )
    );
}
