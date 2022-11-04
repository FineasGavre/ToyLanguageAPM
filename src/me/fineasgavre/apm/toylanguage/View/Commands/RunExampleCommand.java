package me.fineasgavre.apm.toylanguage.View.Commands;

import me.fineasgavre.apm.toylanguage.Controller.ProgramStateController;
import me.fineasgavre.apm.toylanguage.Exceptions.TLException;
import me.fineasgavre.apm.toylanguage.Utils.PrintUtils;

public class RunExampleCommand extends Command {
    private final ProgramStateController controller;

    public RunExampleCommand(String key, ProgramStateController controller) {
        super(key, "Run Program " + key + "\n\n" + controller.getCurrentProgramState().getOriginalStatement().toString() + "\n");
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.executeCurrentUntilEmpty();

            var output = controller.getCurrentProgramState().getOutput();
            System.out.println("Output: " + output);
        } catch (TLException e) {
            PrintUtils.printTLException(e);
        }
    }
}
