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
