package me.fineasgavre.apm.toylanguage.view.commands;

import me.fineasgavre.apm.toylanguage.controller.ProgramStateController;

public class RunExampleCommand extends Command {
    private final ProgramStateController controller;

    public RunExampleCommand(String key, ProgramStateController controller) {
        super(key, "Run Program " + key + "\n\n" + controller.getProgramStates().stream().findFirst().get().getOriginalStatement().toString() + "\n");
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.allStep();
    }
}
