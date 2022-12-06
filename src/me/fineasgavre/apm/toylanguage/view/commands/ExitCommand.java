package me.fineasgavre.apm.toylanguage.view.commands;

public class ExitCommand extends Command {
    public ExitCommand(String key) {
        super(key, "Exit this application.");
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
