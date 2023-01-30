package me.fineasgavre.apm.toylanguage.utils;

import me.fineasgavre.apm.toylanguage.domain.adts.TLPair;
import me.fineasgavre.apm.toylanguage.domain.expressions.*;
import me.fineasgavre.apm.toylanguage.domain.statements.*;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.types.BooleanType;
import me.fineasgavre.apm.toylanguage.domain.types.IntegerType;
import me.fineasgavre.apm.toylanguage.domain.types.RefType;
import me.fineasgavre.apm.toylanguage.domain.types.StringType;
import me.fineasgavre.apm.toylanguage.domain.values.BooleanValue;
import me.fineasgavre.apm.toylanguage.domain.values.IntegerValue;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;

import java.util.ArrayList;
import java.util.List;

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

    public static IStatement PROGRAM8 = new CompoundStatement(
            new VariableDeclarationStatement("v", new RefType(new IntegerType())),
            new CompoundStatement(
                    new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(20))),
                    new CompoundStatement(
                            new VariableDeclarationStatement("a", new RefType(new RefType(new IntegerType()))),
                            new CompoundStatement(
                                    new AllocateHeapStatement("a", new VariableExpression("v")),
                                    new CompoundStatement(
                                            new AllocateHeapStatement("v", new ValueExpression(new IntegerValue(30))),
                                            new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                    )
                            )
                    )
            )
    );

    public static IStatement PROGRAM9 = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntegerType()),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(10))),
                    new CompoundStatement(
                            new WhileStatement(
                                    new RelationalExpression(
                                            new VariableExpression("v"),
                                            new ValueExpression(new IntegerValue(0)),
                                            RelationalExpression.RelationalOperation.GREATER_THAN
                                    ),
                                    new CompoundStatement(
                                            new PrintStatement(new VariableExpression("v")),
                                            new AssignmentStatement("v", new ArithmeticExpression(
                                                    new VariableExpression("v"),
                                                    new ValueExpression(new IntegerValue(1)),
                                                    ArithmeticExpression.ArithmeticOperation.SUBTRACTION
                                            ))
                                    )
                            ),
                            new PrintStatement(new VariableExpression("v"))
                    )
            )
    );

    public static IStatement PROGRAM10 = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntegerType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("a", new RefType(new IntegerType())),
                    new CompoundStatement(
                            new AssignmentStatement("v", new ValueExpression(new IntegerValue(10))),
                            new CompoundStatement(
                                    new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(22))),
                                    new CompoundStatement(
                                            new ForkStatement(
                                                    new CompoundStatement(
                                                            new WriteHeapStatement("a", new ValueExpression(new IntegerValue(30))),
                                                            new CompoundStatement(
                                                                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(32))),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VariableExpression("v")),
                                                                            new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                    )
                                                            )
                                                    )
                                            ),
                                            new CompoundStatement(
                                                    new PrintStatement(new VariableExpression("v")),
                                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                            )
                                    )
                            )
                    )
            )
    );

    public static IStatement PROGRAM11 = new GroupStatement(
            new VariableDeclarationStatement("a", new IntegerType()),
            new AssignmentStatement("a", new ValueExpression(new IntegerValue(1))),
            new VariableDeclarationStatement("b", new IntegerType()),
            new AssignmentStatement("b", new ValueExpression(new IntegerValue(2))),
            new VariableDeclarationStatement("c", new IntegerType()),
            new AssignmentStatement("c", new ValueExpression(new IntegerValue(5))),
            new SwitchStatement(
                    new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntegerValue(10)), ArithmeticExpression.ArithmeticOperation.MULTIPLICATION),
                    new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("c"), ArithmeticExpression.ArithmeticOperation.MULTIPLICATION),
                    new GroupStatement(
                            new PrintStatement(new VariableExpression("a")),
                            new PrintStatement(new VariableExpression("b"))
                    ),
                    new ValueExpression(new IntegerValue(10)),
                    new GroupStatement(
                            new PrintStatement(new ValueExpression(new IntegerValue(100))),
                            new PrintStatement(new ValueExpression(new IntegerValue(200)))
                    ),
                    new PrintStatement(new ValueExpression(new IntegerValue(300)))
            ),
            new PrintStatement(new ValueExpression(new IntegerValue(300)))
    );

    public static IStatement PROGRAM12 = new GroupStatement(
            new VariableDeclarationStatement("v", new IntegerType()),
            new AssignmentStatement("v", new ValueExpression(new IntegerValue(10))),
            new ForkStatement(
                    new GroupStatement(
                            new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(1)), ArithmeticExpression.ArithmeticOperation.SUBTRACTION)),
                            new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(1)), ArithmeticExpression.ArithmeticOperation.SUBTRACTION)),
                            new PrintStatement(new VariableExpression("v"))
                    )
            ),
            new SleepStatement(new ValueExpression(new IntegerValue(10))),
            new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntegerValue(10)), ArithmeticExpression.ArithmeticOperation.MULTIPLICATION))
    );

    public static IStatement PROGRAM13 = new GroupStatement(
            new VariableDeclarationStatement("a", new RefType(new IntegerType())),
            new VariableDeclarationStatement("b", new RefType(new IntegerType())),
            new VariableDeclarationStatement("v", new IntegerType()),
            new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(0))),
            new AllocateHeapStatement("b", new ValueExpression(new IntegerValue(0))),
            new WriteHeapStatement("a", new ValueExpression(new IntegerValue(1))),
            new WriteHeapStatement("b", new ValueExpression(new IntegerValue(2))),
            new ConditionalAssignmentStatement(
                    "v",
                    new RelationalExpression(
                            new ReadHeapExpression(new VariableExpression("a")),
                            new ReadHeapExpression(new VariableExpression("b")),
                            RelationalExpression.RelationalOperation.LESS_THAN
                    ),
                    new ValueExpression(new IntegerValue(100)),
                    new ValueExpression(new IntegerValue(200))
            ),
            new PrintStatement(new VariableExpression("v")),
            new ConditionalAssignmentStatement(
                    "v",
                    new RelationalExpression(
                            new ArithmeticExpression(
                                    new ReadHeapExpression(new VariableExpression("b")),
                                    new ValueExpression(new IntegerValue(2)),
                                    ArithmeticExpression.ArithmeticOperation.SUBTRACTION
                            ),
                            new ReadHeapExpression(new VariableExpression("a")),
                            RelationalExpression.RelationalOperation.GREATER_THAN
                    ),
                    new ValueExpression(new IntegerValue(100)),
                    new ValueExpression(new IntegerValue(200))
            ),
            new PrintStatement(new VariableExpression("v"))
    );

    public static IStatement PROGRAM14 = new GroupStatement(
            new VariableDeclarationStatement("a", new RefType(new IntegerType())),
            new AllocateHeapStatement("a", new ValueExpression(new IntegerValue(20))),
            new ForStatement(
                    "v",
                    new ValueExpression(new IntegerValue(0)),
                    new ValueExpression(new IntegerValue(3)),
                    new ArithmeticExpression(
                            new VariableExpression("v"),
                            new ValueExpression(new IntegerValue(1)),
                            ArithmeticExpression.ArithmeticOperation.ADDITION
                    ),
                    new ForkStatement(new GroupStatement(
                            new PrintStatement(new VariableExpression("v")),
                            new AssignmentStatement(
                                    "v",
                                    new ArithmeticExpression(
                                            new VariableExpression("v"),
                                            new ReadHeapExpression(new VariableExpression("a")),
                                            ArithmeticExpression.ArithmeticOperation.MULTIPLICATION
                                    )
                            )
                    ))
            ),
            new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
    );

    private static IStatement PROGRAM15 = new GroupStatement(
            new DefineProcedureStatement(
                    "sum",
                    List.of(
                            new TLPair<>("a", new IntegerType()),
                            new TLPair<>("b", new IntegerType())
                    ),
                    new GroupStatement(
                            new VariableDeclarationStatement("v", new IntegerType()),
                            new AssignmentStatement(
                                    "v",
                                    new ArithmeticExpression(
                                            new VariableExpression("a"),
                                            new VariableExpression("b"),
                                            ArithmeticExpression.ArithmeticOperation.ADDITION
                                    )
                            ),
                            new PrintStatement(new VariableExpression("v"))
                    )
            ),
            new DefineProcedureStatement(
                    "product",
                    List.of(
                            new TLPair<>("a", new IntegerType()),
                            new TLPair<>("b", new IntegerType())
                    ),
                    new GroupStatement(
                            new VariableDeclarationStatement("v", new IntegerType()),
                            new AssignmentStatement(
                                    "v",
                                    new ArithmeticExpression(
                                            new VariableExpression("a"),
                                            new VariableExpression("b"),
                                            ArithmeticExpression.ArithmeticOperation.MULTIPLICATION
                                    )
                            ),
                            new PrintStatement(new VariableExpression("v"))
                    )
            ),
            new VariableDeclarationStatement("v", new IntegerType()),
            new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
            new VariableDeclarationStatement("w", new IntegerType()),
            new AssignmentStatement("w", new ValueExpression(new IntegerValue(5))),
            new CallProcedureStatement(
                    "sum",
                    List.of(
                            new ArithmeticExpression(
                                    new VariableExpression("v"),
                                    new ValueExpression(new IntegerValue(10)),
                                    ArithmeticExpression.ArithmeticOperation.MULTIPLICATION
                            ),
                            new VariableExpression("w")
                    )
            ),
            new PrintStatement(new VariableExpression("v")),
            new ForkStatement(
                    new GroupStatement(
                            new CallProcedureStatement(
                                    "product",
                                    List.of(
                                            new VariableExpression("v"),
                                            new VariableExpression("w")
                                    )
                            ),
                            new ForkStatement(
                                    new CallProcedureStatement(
                                            "sum",
                                            List.of(
                                                    new VariableExpression("v"),
                                                    new VariableExpression("w")
                                            )
                                    )
                            )
                    )
            )
    );

    public static List<IStatement> programList = new ArrayList<>(List.of(PROGRAM1, PROGRAM2, PROGRAM3, PROGRAM4, PROGRAM5, PROGRAM6, PROGRAM7, PROGRAM8, PROGRAM9, PROGRAM10, PROGRAM11, PROGRAM12, PROGRAM13, PROGRAM14, PROGRAM15));
}
