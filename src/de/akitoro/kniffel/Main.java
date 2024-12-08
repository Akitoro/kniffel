package de.akitoro.kniffel;

import de.akitoro.kniffel.cli.StateMachine;
import de.akitoro.kniffel.cli.states.GameState;

import java.util.Scanner;

/**
 * Main.
 */
public final class Main {

    /**
     * Scanner.
     */
    public static final Scanner SCANNER = new Scanner(System.in);

    private Main() {

    }

    /**
     * Main-Methode, die eine {@link StateMachine anstößt}
     * @param args Programmargumente
     */
    public static void main(String[] args) {
        StateMachine stateMachine = new StateMachine();
        stateMachine.transition(new GameState(stateMachine));

        while (stateMachine.isRunning()) {
            stateMachine.getCurrentState().run();
        }
        SCANNER.close();
    }
}
