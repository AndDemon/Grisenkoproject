package com.rx.javajxpr;

import java.util.Stack;

public class CommandManager {
    private static CommandManager instance;
    private Stack<Command> commandHistory;

    private CommandManager() {
        this.commandHistory = new Stack<>();
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("Немає доступних операцій для скасування.");
        }
    }
}
