package me.fineasgavre.apm.toylanguage;

import me.fineasgavre.apm.toylanguage.controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.state.ProgramState;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.utils.BuiltInPrograms;
import me.fineasgavre.apm.toylanguage.utils.PrintUtils;
import me.fineasgavre.apm.toylanguage.view.CommandLineMenu;
import me.fineasgavre.apm.toylanguage.view.commands.ExitCommand;
import me.fineasgavre.apm.toylanguage.view.commands.RunExampleCommand;

public class Main {
    public static void main(String[] args) {
        var debugMode = true;
        var menu = new CommandLineMenu();

        createRunCommand(menu, BuiltInPrograms.PROGRAM1, "1");
        createRunCommand(menu, BuiltInPrograms.PROGRAM2, "2");
        createRunCommand(menu, BuiltInPrograms.PROGRAM3, "3");
        createRunCommand(menu, BuiltInPrograms.PROGRAM4, "4");
        createRunCommand(menu, BuiltInPrograms.PROGRAM5, "5");
        createRunCommand(menu, BuiltInPrograms.PROGRAM6, "6");
        createRunCommand(menu, BuiltInPrograms.PROGRAM7, "7");
        createRunCommand(menu, BuiltInPrograms.PROGRAM8, "8");
        createRunCommand(menu, BuiltInPrograms.PROGRAM9, "9");
        createRunCommand(menu, BuiltInPrograms.PROGRAM10, "10");

        var exitCommand = new ExitCommand("0");
        menu.addCommand(exitCommand);

        menu.show();
    }

    private static void createRunCommand(CommandLineMenu menu, IStatement program, String key) {
        try {
            var controller = new ProgramStateController();
            program.staticTypeCheck(new TLMap<>());
            controller.setCurrentProgramFromStatement(program);
            controller.setDebugMode(true);
            var runCommand = new RunExampleCommand(key, controller);
            menu.addCommand(runCommand);
        } catch (TLException e) {
            PrintUtils.printTLException(e);
        }
    }
}
