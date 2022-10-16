package me.fineasgavre.apm.toylanguage.View;

import me.fineasgavre.apm.toylanguage.Controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.ArithmeticExpression;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.ValueExpression;
import me.fineasgavre.apm.toylanguage.Domain.Expressions.VariableExpression;
import me.fineasgavre.apm.toylanguage.Domain.Statements.*;
import me.fineasgavre.apm.toylanguage.Domain.Types.BooleanType;
import me.fineasgavre.apm.toylanguage.Domain.Types.IntegerType;
import me.fineasgavre.apm.toylanguage.Domain.Values.BooleanValue;
import me.fineasgavre.apm.toylanguage.Domain.Values.IntegerValue;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;

import java.util.Arrays;
import java.util.Scanner;

public class CommandLineView {
    private final Scanner scanner = new Scanner(System.in);

    private final ProgramStateController programStateController = new ProgramStateController();

    public void execute() {
        while (true) {
            printListOfOptions();

            var selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption) {
                case 1 -> executeProgram1();
                case 2 -> executeProgram2();
                case 3 -> executeProgram3();
                case 4 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Unknown option. Please try again!");
            }
        }
    }

    private void printListOfOptions() {
        var lines = new String[]{"", "List of options for TL:", "1. Program 1", "2. Program 2", "3. Program 3", "4. Exit", "", "Option? "};
        Arrays.stream(lines).forEach(System.out::println);
    }

    private void executeProgram1() {
        var statement = new CompoundStatement(
            new VariableDeclarationStatement("v", new IntegerType()),
            new CompoundStatement(
                    new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                    new PrintStatement(new VariableExpression("v"))
            )
        );

        System.out.println("Executing statement:\n" + statement);

        this.programStateController.setCurrentProgramFromStatement(statement);

        try {
            this.programStateController.executeCurrentUntilEmpty();
        } catch (TLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executeProgram2() {
        var statement = new CompoundStatement(
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

        System.out.println("Executing statement:\n" + statement);

        this.programStateController.setCurrentProgramFromStatement(statement);

        try {
            this.programStateController.executeCurrentUntilEmpty();
        } catch (TLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void executeProgram3() {
        var statement = new CompoundStatement(
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

        System.out.println("Executing statement:\n" + statement);

        this.programStateController.setCurrentProgramFromStatement(statement);

        try {
            this.programStateController.executeCurrentUntilEmpty();
        } catch (TLException e) {
            System.out.println(e.getMessage());
        }
    }
}
