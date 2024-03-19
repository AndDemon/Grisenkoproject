package com.rx.javajxpr;

public interface Command {
    void execute();
    void undo();
}


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

        solver.getData().setResult(Double.NaN);
    }
}


class ChangeParamsCommand implements Command {
    private Solv solver;
    private double oldSideLength;

    public ChangeParamsCommand(Solv solver, double newSideLength) {
        this.solver = solver;
        this.oldSideLength = solver.sideLength;
        solver.sideLength = newSideLength;
    }

    @Override
    public void execute() {
        solver.solve();
    }

    @Override
    public void undo() {
        solver.sideLength = oldSideLength;
        solver.getData().setResult(Double.NaN);
    }
}
