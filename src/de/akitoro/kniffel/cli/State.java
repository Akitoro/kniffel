package de.akitoro.kniffel.cli;

public abstract class State {

    protected final StateMachine machine;

    public State(StateMachine parent) {
        this.machine = parent;
    }
    public void enter() {

    }

    public abstract void run();

    public void exit() {

    }
}
