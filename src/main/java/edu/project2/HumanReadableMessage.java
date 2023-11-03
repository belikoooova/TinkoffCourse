package edu.project2;

public enum HumanReadableMessage {
    HELLO("Hello. This is maze generator and solver."),
    CHOOSE_GENERATOR_TYPE("Choose algorithm for generating. Type DFS or BFS: "),
    CHOOSE_SOLVER_TYPE("Choose algorithm for solving. Type DFS or BFS: "),
    EXIT("Do you want to exit? Write YES if you want, otherwise, the program will start from the beginning."),
    INPUT_HEIGHT("Input height of maze: "),
    INPUT_WIDTH("Input width of maze: "),
    INPUT_START_X("Input start (row coordinate): "),
    INPUT_START_Y("Input start (col coordinate): "),
    INPUT_END_X("Input end (row coordinate): "),
    INPUT_END_Y("Input end (col coordinate): "),
    ERROR_COORDS("Coords must be: 0 <= row < %d, 0 <= col < %d");

    private final String message;

    HumanReadableMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
