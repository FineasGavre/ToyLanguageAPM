package me.fineasgavre.apm.toylanguage.View;

import me.fineasgavre.apm.toylanguage.Controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;
import me.fineasgavre.apm.toylanguage.Utils.BuiltInPrograms;
import me.fineasgavre.apm.toylanguage.Utils.PrintUtils;

import java.util.Arrays;
import java.util.Scanner;

public class CommandLineView {
    private final Scanner scanner = new Scanner(System.in);

    private final ProgramStateController programStateController = new ProgramStateController();

    public void execute() {
        programStateController.setDebugMode(true);

        System.out.println("Loaded Programs:");
        PrintUtils.printStandaloneStatement(BuiltInPrograms.PROGRAM1, "Program 1");
        PrintUtils.printStandaloneStatement(BuiltInPrograms.PROGRAM2, "Program 2");
        PrintUtils.printStandaloneStatement(BuiltInPrograms.PROGRAM3, "Program 3");

        while (true) {
            printListOfOptions();

            var selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption) {
                case 1 -> executeProgram(BuiltInPrograms.PROGRAM1, "Program 1");
                case 2 -> executeProgram(BuiltInPrograms.PROGRAM2, "Program 2");
                case 3 -> executeProgram(BuiltInPrograms.PROGRAM3, "Program 3");
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

    private void executeProgram(IStatement programStatement, String programName) {
        PrintUtils.printStandaloneStatement(programStatement, programName);

        this.programStateController.setCurrentProgramFromStatement(programStatement);

        try {
            this.programStateController.executeCurrentUntilEmpty();
        } catch (TLException e) {
            PrintUtils.printTLException(e);
        }
    }
}
