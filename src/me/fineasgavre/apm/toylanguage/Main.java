package me.fineasgavre.apm.toylanguage;

import me.fineasgavre.apm.toylanguage.controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.utils.BuiltInPrograms;
import me.fineasgavre.apm.toylanguage.view.CommandLineMenu;
import me.fineasgavre.apm.toylanguage.view.commands.ExitCommand;
import me.fineasgavre.apm.toylanguage.view.commands.RunExampleCommand;

public class Main {
    public static void main(String[] args) {
        var debugMode = true;
        var menu = new CommandLineMenu();

        var controller1 = new ProgramStateController();
        controller1.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM1);
        controller1.setDebugMode(debugMode);
        var runCommand1 = new RunExampleCommand("1", controller1);
        menu.addCommand(runCommand1);

        var controller2 = new ProgramStateController();
        controller2.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM2);
        controller2.setDebugMode(debugMode);
        var runCommand2 = new RunExampleCommand("2", controller2);
        menu.addCommand(runCommand2);

        var controller3 = new ProgramStateController();
        controller3.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM3);
        controller3.setDebugMode(debugMode);
        var runCommand3 = new RunExampleCommand("3", controller3);
        menu.addCommand(runCommand3);

        var controller4 = new ProgramStateController();
        controller4.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM4);
        controller4.setDebugMode(debugMode);
        var runCommand4 = new RunExampleCommand("4", controller4);
        menu.addCommand(runCommand4);

        var controller5 = new ProgramStateController();
        controller5.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM5);
        controller5.setDebugMode(debugMode);
        var runCommand5 = new RunExampleCommand("5", controller5);
        menu.addCommand(runCommand5);

        var controller6 = new ProgramStateController();
        controller6.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM6);
        controller6.setDebugMode(debugMode);
        var runCommand6 = new RunExampleCommand("6", controller6);
        menu.addCommand(runCommand6);

        var controller7 = new ProgramStateController();
        controller7.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM7);
        controller7.setDebugMode(debugMode);
        var runCommand7 = new RunExampleCommand("7", controller7);
        menu.addCommand(runCommand7);

        var controller8 = new ProgramStateController();
        controller8.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM8);
        controller8.setDebugMode(debugMode);
        var runCommand8 = new RunExampleCommand("8", controller8);
        menu.addCommand(runCommand8);

        var controller9 = new ProgramStateController();
        controller9.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM9);
        controller9.setDebugMode(debugMode);
        var runCommand9 = new RunExampleCommand("9", controller9);
        menu.addCommand(runCommand9);

        var controller10 = new ProgramStateController();
        controller10.setCurrentProgramFromStatement(BuiltInPrograms.PROGRAM10);
        controller10.setDebugMode(debugMode);
        var runCommand10 = new RunExampleCommand("10", controller10);
        menu.addCommand(runCommand10);

        var exitCommand = new ExitCommand("0");
        menu.addCommand(exitCommand);

        menu.show();
    }
}
