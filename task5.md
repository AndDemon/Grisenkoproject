# Практика ООП з Java
Завдання 5 - Обробка колекцій
Вам потрібно виконати наступне:
Реалізувати можливість скасування (undo) операцій (команд).
Продемонструвати поняття "макрокоманда"
При розробці програми використовувати шаблон Singletone.
Забезпечити діалоговий інтерфейс із користувачем.
Розробити клас для тестування функціональності програми.

Додані класи:
```java
package org.example;

public interface Command {
    void execute();
    void undo();
}

// Реалізація конкретної команди для обчислення
class SolveCommand implements Command {
    private Solv solver;

    public SolveCommand(Solv solver) {
        this.solver = solver;
    }

    @Override
    public void execute() {
        solver.solve();
    }

    @Override
    public void undo() {
        // Скасування операції solve необхідно для скасування результату
        solver.getData().setResult(Double.NaN);
    }
}

// Реалізація конкретної команди для зміни параметрів обчислення
class ChangeParamsCommand implements Command {
    private Solv solver;
    private double oldX;
    private double oldY;

    public ChangeParamsCommand(Solv solver, double newX, double newY) {
        this.solver = solver;
        this.oldX = solver.x;
        this.oldY = solver.y;
        solver.x = newX;
        solver.y = newY;
    }

    @Override
    public void execute() {
        solver.solve();
    }

    @Override
    public void undo() {
        solver.x = oldX;
        solver.y = oldY;
        solver.getData().setResult(Double.NaN);
    }
}

```

```java
package org.example;

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

```
```java
package org.example;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {
    private List<Command> commands;

    public MacroCommand() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        // Скасування макрокоманди викликає скасування всіх вкладених команд
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
```
