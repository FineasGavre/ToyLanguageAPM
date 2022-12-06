import me.fineasgavre.apm.toylanguage.controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.utils.BuiltInPrograms;
import me.fineasgavre.apm.toylanguage.view.CommandLineMenu;
import me.fineasgavre.apm.toylanguage.view.commands.ExitCommand;
import me.fineasgavre.apm.toylanguage.view.commands.RunExampleCommand;

public class Main {
    public static void main(String[] args) {
        var debugMode = false;
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

        var exitCommand = new ExitCommand("0");
        menu.addCommand(exitCommand);

        menu.show();
    }
}
