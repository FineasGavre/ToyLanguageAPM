package me.fineasgavre.apm.toylanguage.utils;

import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;

public class PrintUtils {
    public static void printTLException(TLException exception) {
        System.out.println();
        PrintUtils.printHeadlineAndContent(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD, " EXCEPTION! ", ConsoleColors.RED, exception.getMessage());
        System.out.println();
    }

    public static void printProgramState(ProgramState programState, boolean printInitialStatement) {
        System.out.println();
        PrintUtils.printHeadlineAndContent(ConsoleColors.CYAN_BACKGROUND + ConsoleColors.BLACK_BOLD, "   PROGRAM STATE   ", "", "");
        PrintUtils.printHeadlineAndContent(ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.BLACK_BOLD_BRIGHT, "  EXECUTION STACK  ", ConsoleColors.YELLOW, programState.getExecutionStack().toString());
        PrintUtils.printHeadlineAndContent(ConsoleColors.PURPLE_BACKGROUND + ConsoleColors.BLACK_BOLD_BRIGHT, "   SYMBOL TABLE    ", ConsoleColors.PURPLE, programState.getSymbolTable().toString());
        PrintUtils.printHeadlineAndContent(ConsoleColors.PURPLE_BACKGROUND + ConsoleColors.BLACK_BOLD_BRIGHT, "   HEAP    ", ConsoleColors.PURPLE, programState.getHeap().toString());
        PrintUtils.printHeadlineAndContent(ConsoleColors.PURPLE_BACKGROUND + ConsoleColors.BLACK_BOLD_BRIGHT, "   FILE TABLE    ", ConsoleColors.PURPLE, programState.getFileTable().toString());
        PrintUtils.printHeadlineAndContent(ConsoleColors.GREEN_BACKGROUND + ConsoleColors.BLACK_BOLD_BRIGHT, "    OUTPUT LIST    ", ConsoleColors.GREEN, programState.getOutput().toString());

        if (printInitialStatement) {
            PrintUtils.printHeadlineAndContent(ConsoleColors.BLUE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD_BRIGHT, " INITIAL STATEMENT ", ConsoleColors.BLUE_BRIGHT, "\n" + programState.getOriginalStatement().toString());
        }

        System.out.println();
    }

    public static void printStandaloneStatement(IStatement statement, String extraInformation) {
        System.out.println();
        PrintUtils.printHeadlineAndContent(ConsoleColors.BLUE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD, " STATEMENT ", ConsoleColors.BLUE_BRIGHT, extraInformation + "\n" + statement);
        System.out.println();
    }

    public static void printHeadlineAndContent(String headlineColor, String headline, String contentColor, String content) {
        System.out.println(headlineColor + headline + ConsoleColors.RESET + " " + contentColor + content + ConsoleColors.RESET);
    }
}
