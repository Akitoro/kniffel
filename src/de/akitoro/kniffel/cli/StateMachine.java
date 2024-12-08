package de.akitoro.kniffel.cli;

public final class StateMachine {
    private State currentState;

    private boolean running = true;

    public void transition(State next) {
        if (currentState != null) {
            this.currentState.exit();
        }
        this.currentState = next;
        if (next != null) {
            this.running = true;
            this.currentState.enter();
        }
    }

    public void stop() {
        this.transition(null);
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    public State getCurrentState() {
        return currentState;
    }
}
